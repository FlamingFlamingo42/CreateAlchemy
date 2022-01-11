package com.createalchemy.createalchemy.index;

import com.createalchemy.createalchemy.CreateAlchemy;
import com.createalchemy.createalchemy.groups.ModGroup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.createalchemy.createalchemy.CreateAlchemy.CREATIVE_TAB;

public class AllItems {
    private static final CreateRegistrate REGISTRATE = CreateAlchemy.registrate()
            .creativeModeTab(() -> CREATIVE_TAB);


    public static final ItemEntry<Item> TEST = REGISTRATE.item("test", Item::new).register();


    //Put all entries above this
    public static void register() {}
}
