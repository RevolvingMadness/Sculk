package com.revolvingmadness.sculk.dynamicreg;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class DynamicRegistries {
    public static final DynamicRegistry<Block> BLOCK = new DynamicBlockRegistry();
    public static final DynamicRegistry<Item> ITEM = new DynamicItemRegistry();
    //    public static final DynamicRegistry<Potion> POTION = new DynamicRegistry<>(Registries.POTION);
}
