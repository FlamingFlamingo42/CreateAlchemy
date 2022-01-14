package com.createalchemy.createalchemy.index;

import com.createalchemy.createalchemy.CreateAlchemy;
import com.createalchemy.createalchemy.blocks.*;

import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import static com.createalchemy.createalchemy.CreateAlchemy.CREATIVE_TAB;


public class AllBlocks {
    private static final CreateRegistrate REGISTRATE = CreateAlchemy.registrate()
            .creativeModeTab(() -> CREATIVE_TAB );



    //Basic Machines
    public static final BlockEntry<LiquifierBlock> LIQUIFIER =
            REGISTRATE.block("liquifier", LiquifierBlock::new)
                    .transform(BlockStressDefaults.setImpact(4.0))
                    .defaultLang()
                    .simpleItem()
                    .defaultLoot()
                    .lang("Liquifier")
                    .register();

    public static final BlockEntry<CentrifugeBlock> CENTRIFUGE =
            REGISTRATE.block("centrifuge", CentrifugeBlock::new)
                    .transform(BlockStressDefaults.setImpact(4.0))
                    .defaultLang()
                    .simpleItem()
                    .defaultLoot()
                    .lang("Centrifuge")
                    .register();

    public static final BlockEntry<AlchemicalLaserBlock> ALCHEMICAL_LASER =
            REGISTRATE.block("alchemical_laser", AlchemicalLaserBlock::new)
                    .transform(BlockStressDefaults.setImpact(8.0))
                    .defaultLang()
                    .simpleItem()
                    .defaultLoot()
                    .lang("Alchemical Laser")
                    .register();

    public static final BlockEntry<CopperDepotBlock> COPPER_DEPOT =
            REGISTRATE.block("copper_depot", CopperDepotBlock::new)
                    .defaultLang()
                    .simpleItem()
                    .defaultLoot()
                    .lang("Copper Depot")
                    .register();


    //Basic Jars
    public static final BlockEntry<JarBlock> JAR =
            REGISTRATE.block("jar", JarBlock::new)
                    .properties(p -> p.instabreak())
                    .defaultLang()
                    .simpleItem()
                    .defaultLoot()
                    .lang("Jar")
                    .addLayer(() -> RenderType::cutout)
                    .register();

    public static final BlockEntry<Block> FILLED_JAR =
            REGISTRATE.block("filled_jar", Block::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.instabreak())
                    .defaultLang()
                    .simpleItem()
                    .lang("Filled Jar")
                    .addLayer(() -> RenderType::cutout)
                    .defaultLoot()
                    .register();

    //Catalysts
    public static final BlockEntry<Block> CHAOS_CATALYST =
            REGISTRATE.block("chaos_catalyst", Block::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.instabreak())
                    .defaultLang()
                    .simpleItem()
                    .lang("Chaos Catalyst")
                    .addLayer(() -> RenderType::cutout)
                    .defaultLoot()
                    .register();

    //Do cool procedural JSON stuff




    //Put all entries above this
    public static void register() {
    }
}
