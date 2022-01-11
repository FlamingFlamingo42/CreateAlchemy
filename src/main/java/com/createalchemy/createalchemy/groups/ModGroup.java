package com.createalchemy.createalchemy.groups;

import com.createalchemy.createalchemy.CreateAlchemy;

import com.createalchemy.createalchemy.index.AllBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;


public class ModGroup extends CreativeModeTab{
    public static ModGroup MAIN;;

    public ModGroup() {
        super(CreateAlchemy.MODID);
        MAIN = this;
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(AllBlocks.CENTRIFUGE.get());
    }
}
