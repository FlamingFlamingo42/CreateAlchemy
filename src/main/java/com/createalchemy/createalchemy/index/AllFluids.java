package com.createalchemy.createalchemy.index;

import com.createalchemy.createalchemy.CreateAlchemy;

import com.simibubi.create.content.contraptions.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.FluidEntry;


public class AllFluids {

    private static final CreateRegistrate REGISTRATE = CreateAlchemy.registrate();

    public static final FluidEntry<VirtualFluid> LIQUID_DIRT =
            REGISTRATE.virtualFluid("liquid_dirt")
            .register();


    //Put all entries above this
    public static void register() {}
}
