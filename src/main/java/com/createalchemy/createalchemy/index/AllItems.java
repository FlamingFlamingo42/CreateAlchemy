package com.createalchemy.createalchemy.index;

import com.createalchemy.createalchemy.CreateAlchemy;
import com.simibubi.create.foundation.data.CreateRegistrate;

import static com.createalchemy.createalchemy.CreateAlchemy.CREATIVE_TAB;

public class AllItems {
    private static final CreateRegistrate REGISTRATE = CreateAlchemy.registrate()
            .creativeModeTab(() -> CREATIVE_TAB);



    //Put all entries above this
    public static void register() {}
}
