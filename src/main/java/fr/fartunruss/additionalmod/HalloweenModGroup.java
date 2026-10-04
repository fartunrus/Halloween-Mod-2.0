package fr.fartunruss.additionalmod;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class HalloweenModGroup {
    public static ItemGroup HALLOWEEN_GROUP = new ItemGroup("Additional") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Blocks.CARVED_PUMPKIN);
        }
    };
}
