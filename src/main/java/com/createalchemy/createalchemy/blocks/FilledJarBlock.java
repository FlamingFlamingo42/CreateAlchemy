package com.createalchemy.createalchemy.blocks;

import com.createalchemy.createalchemy.index.AllBlocks;
import com.createalchemy.createalchemy.index.AllShapes;
import com.createalchemy.createalchemy.recipe.AlchemyFillRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class FilledJarBlock{

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return AllShapes.FILLED_JAR;
    }

    public static List<String> getJarContent(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains(AlchemyFillRecipe.KEY)) {
            return List.of();
        }
        ListTag list = tag.getList(AlchemyFillRecipe.KEY, Tag.TAG_STRING);
        List<String> ans = new ArrayList<>();
        for (Tag value : list) {
            ans.add(value.getAsString());
        }
        return ans;
    }

    public static void appendContent(ItemStack stack, FluidStack val) {
        CompoundTag fluid_tag = val.getTag();
        if (fluid_tag == null || !fluid_tag.contains(AlchemyFillRecipe.KEY))
            return;
        String material = fluid_tag.getString(AlchemyFillRecipe.KEY);
        CompoundTag tag = stack.getOrCreateTag();
        ListTag list = tag.getList(AlchemyFillRecipe.KEY, Tag.TAG_STRING);
        if (list.size() < AlchemyFillRecipe.MAX) {
            list.add(StringTag.valueOf(material));
        }
        tag.put(AlchemyFillRecipe.KEY, list);
    }

    public static boolean testFluid(ItemStack last, FluidStack stack) {
        CompoundTag fluid_tag = stack.getTag();
        if (fluid_tag == null || !fluid_tag.contains(AlchemyFillRecipe.KEY))
            return false;
        if (last == null) {// it means that the fluid is tested before item
            LogManager.getLogger().error("fluid stack tested before item stack");
            return false;
        }
        if (last.getItem() == AllBlocks.FILLED_JAR.get().asItem()) {
            // size is already tested
            // TODO use config to disable repeated fluid
        }
        return true;
    }
}
