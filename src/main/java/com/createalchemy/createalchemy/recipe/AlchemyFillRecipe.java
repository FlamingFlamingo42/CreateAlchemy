package com.createalchemy.createalchemy.recipe;

import com.createalchemy.createalchemy.blocks.FilledJarBlock;
import com.createalchemy.createalchemy.index.AllBlocks;
import com.createalchemy.createalchemy.index.AllFluids;
import com.simibubi.create.content.contraptions.fluids.actors.FillingRecipe;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AlchemyFillRecipe extends FillingRecipe {

    public static final int FLUID_COST = 1000;
    public static final String KEY = "jar_content";
    public static final int MAX = 4;

    public static final RecipeSerializer<AlchemyFillRecipe> SERIALIZER = new SimpleRecipeSerializer<>(AlchemyFillRecipe::new);

    public static class AlchemyParams extends SpecialRecipeParams<AlchemyParams> {

        protected AlchemyParams(ResourceLocation id) {
            super(id);
            addInput(Ingredient.of(AllBlocks.JAR.asStack(), AllBlocks.FILLED_JAR.asStack()), (self, stack) ->
                    stack.getItem() != AllBlocks.FILLED_JAR.asStack().getItem() ||
                            FilledJarBlock.getJarContent(stack).size() < MAX);
            addInput(FluidIngredient.fromFluid(AllFluids.LIQUID_MATERIAL.get(), FLUID_COST), (self, stack) ->
                    FilledJarBlock.testFluid(self.parent.item_list.get(0).getLast(), stack));

            ItemStack display = AllBlocks.FILLED_JAR.asStack();
            FluidStack fluid = new FluidStack(AllFluids.LIQUID_MATERIAL.get(), FLUID_COST);
            fluid.getOrCreateTag().putString(KEY, "dummy");
            FilledJarBlock.appendContent(display, fluid);

            addOutput(display, self -> {
                ItemStack stack = self.item_list.get(0).getLast();
                if (stack.getItem() != AllBlocks.FILLED_JAR.asStack().getItem()) {
                    stack = AllBlocks.FILLED_JAR.asStack();
                }
                stack = stack.copy();
                FilledJarBlock.appendContent(stack, self.fluid_list.get(0).getLast());
                return stack;
            });
        }
    }

    public AlchemyFillRecipe(ResourceLocation id) {
        super(new AlchemyParams(id));
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}
