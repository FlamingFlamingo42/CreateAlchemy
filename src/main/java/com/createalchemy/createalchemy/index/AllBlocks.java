package com.createalchemy.createalchemy.index;

import com.createalchemy.createalchemy.CreateAlchemy;
import com.createalchemy.createalchemy.blocks.AlchemicalBlock;
import com.createalchemy.createalchemy.blocks.CentrifugeBlock;
import com.createalchemy.createalchemy.blocks.LiquifierBlock;
import com.createalchemy.createalchemy.groups.ModGroup;

import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import com.simibubi.create.repack.registrate.Registrate;

import net.minecraft.world.level.block.Block;

import static com.createalchemy.createalchemy.CreateAlchemy.CREATIVE_TAB;


public class AllBlocks {
    private static final CreateRegistrate REGISTRATE = CreateAlchemy.registrate()
            .creativeModeTab(() -> CREATIVE_TAB );



    //Basic Machines
    public static final BlockEntry<LiquifierBlock> LIQUIFIER =
            REGISTRATE.block("liquifier", LiquifierBlock::new)
                    .transform(BlockStressDefaults.setImpact(4.0))
                    .lang("Liquifier")
                    .register();

    public static final BlockEntry<CentrifugeBlock> CENTRIFUGE =
            REGISTRATE.block("centrifuge", CentrifugeBlock::new)
                    .transform(BlockStressDefaults.setImpact(4.0))
                    .lang("Centrifuge")
                    .register();

    public static final BlockEntry<AlchemicalBlock> ALCHEMICAL =
            REGISTRATE.block("alchemical", AlchemicalBlock::new)
                    .transform(BlockStressDefaults.setImpact(8.0))
                    .register();


    //Basic Jars
    public static final BlockEntry<Block> JAR =
            REGISTRATE.block("jar", Block::new)
                    .properties(p -> p.instabreak())
                    .register();

    public static final BlockEntry<Block> FILLED_JAR =
            REGISTRATE.block("filled_jar", Block::new)
                    .properties(p -> p.instabreak())
                    .register();

    //Catalysts

    //Do cool procedural JSON stuff

    public static final BlockEntry<Block> CHAOS_CATALYST =
            REGISTRATE.block("chaos_catalyst", Block::new)
                    .properties(p -> p.instabreak())
                    .register();


    //Put all entries above this
    public static void register() {
    }
}
