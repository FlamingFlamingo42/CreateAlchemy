package com.createalchemy.createalchemy.recipe;

import com.google.gson.JsonObject;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import com.simibubi.create.content.contraptions.processing.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * NBT-Dependent Recipe Interface Hack for Create Addons
 *
 * @author lcy0x1
 *
 * */
public class SpecialRecipeParams<R extends SpecialRecipeParams<R>> extends ProcessingRecipeBuilder.ProcessingRecipeParams {

    public static class RememberingIngredient<T extends SpecialRecipeParams<T>> extends Ingredient {

        public final T parent;
        private final BiPredicate<RememberingIngredient<T>, ItemStack> predicate;
        private final Ingredient base;
        private ItemStack last_past;

        protected RememberingIngredient(T parent, Ingredient base, BiPredicate<RememberingIngredient<T>, ItemStack> predicate) {
            super(Stream.of(base.getItems()).map(ItemValue::new));
            this.base = base;
            this.predicate = predicate;
            this.parent = parent;
        }

        @Override
        public boolean test(@Nullable ItemStack stack) {
            boolean ans = base.test(stack) && predicate.test(this, stack);
            if (ans) {
                last_past = stack;
            }
            return ans;
        }

        public ItemStack getLast() {
            return last_past;
        }
    }

    public static class RememberingFluidIngredient<T extends SpecialRecipeParams<T>> extends FluidIngredient {

        public final T parent;
        private final BiPredicate<RememberingFluidIngredient<T>, FluidStack> predicate;
        private final FluidIngredient base;
        private FluidStack last_past;

        protected RememberingFluidIngredient(T parent, FluidIngredient base, BiPredicate<RememberingFluidIngredient<T>, FluidStack> predicate) {
            this.base = base;
            this.predicate = predicate;
            this.parent = parent;
            this.amountRequired = base.getRequiredAmount();
        }

        public FluidStack getLast() {
            return last_past;
        }

        @Override
        protected boolean testInternal(FluidStack stack) {
            boolean ans = base.test(stack) && predicate.test(this, stack);
            if (ans) {
                last_past = stack;
            }
            return ans;
        }

        @Override
        protected void readInternal(FriendlyByteBuf friendlyByteBuf) {

        }

        @Override
        protected void writeInternal(FriendlyByteBuf friendlyByteBuf) {

        }

        @Override
        protected void readInternal(JsonObject jsonObject) {

        }

        @Override
        protected void writeInternal(JsonObject jsonObject) {

        }

        @Override
        protected List<FluidStack> determineMatchingFluidStacks() {
            return base.getMatchingFluidStacks();
        }

    }

    public static class VariableProcessingOutput<T extends SpecialRecipeParams<T>> extends ProcessingOutput {

        private final T parent;
        private final Function<T, ItemStack> function;


        public VariableProcessingOutput(T parent, ItemStack display, Function<T, ItemStack> function) {
            super(display, 1);
            this.parent = parent;
            this.function = function;
        }

        @Override
        public ItemStack rollOutput() {
            return function.apply(parent);
        }
    }

    protected final List<RememberingIngredient<R>> item_list = new ArrayList<>();
    protected final List<RememberingFluidIngredient<R>> fluid_list = new ArrayList<>();

    protected SpecialRecipeParams(ResourceLocation id) {
        super(id);
    }


    public void addInput(Ingredient display, BiPredicate<RememberingIngredient<R>, ItemStack> ingredient) {
        RememberingIngredient<R> val = new RememberingIngredient<>(getThis(), display, ingredient);
        this.item_list.add(val);
        this.ingredients.add(val);
    }

    public void addInput(FluidIngredient display, BiPredicate<RememberingFluidIngredient<R>, FluidStack> ingredient){
        RememberingFluidIngredient<R> val = new RememberingFluidIngredient<>(getThis(), display, ingredient);
        this.fluid_list.add(val);
        this.fluidIngredients.add(val);
    }

    @SuppressWarnings("unchecked")
    private R getThis() {
        return (R) this;
    }

    public void addOutput(ItemStack display, Function<R, ItemStack> output) {
        this.results.add(new VariableProcessingOutput<>(getThis(), display, output));
    }

    public void clear() {
        for (RememberingIngredient<R> val : item_list) {
            val.last_past = null;
        }
    }

}
