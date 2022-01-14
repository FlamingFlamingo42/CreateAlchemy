package com.createalchemy.createalchemy.index;

import com.createalchemy.createalchemy.CreateAlchemy;
import com.createalchemy.createalchemy.recipe.AlchemyFillRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;

public class AllRecipeTypes {

    public static void register(RegistryEvent.Register<RecipeSerializer<?>> event) {
        event.getRegistry().register(AlchemyFillRecipe.SERIALIZER.setRegistryName(new ResourceLocation(CreateAlchemy.MODID, "alchemy_filling")));
    }

}
