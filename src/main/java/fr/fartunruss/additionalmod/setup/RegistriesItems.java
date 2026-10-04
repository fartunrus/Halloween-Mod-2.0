package fr.fartunruss.additionalmod.setup;

import fr.fartunruss.additionalmod.HalloweenMod;
import fr.fartunruss.additionalmod.HalloweenModGroup;
import fr.fartunruss.additionalmod.items.WandItem;
import fr.fartunruss.additionalmod.items.wandAnim.MagicalWandRenderer;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistriesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HalloweenMod.MODID);
    public static final RegistryObject<Item> CANDY =
            ITEMS.register("candy", () -> new Item(
                    new Item.Properties()
                            .food(new Food.Builder()
                                    .hunger(4)
                                    .saturation(0.4f)
                                    .fastToEat()
                                    .build())
                            .group(HalloweenModGroup.HALLOWEEN_GROUP)
            ));
    /*public static final RegistryObject<Item> CANDY_CRYSTAL =
            ITEMS.register("candy_crystal", () -> new Item(
                    new Item.Properties()
                            .food(new Food.Builder()
                                    .hunger(5)
                                    .saturation(0.6f)
                                    //.effect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 2, true, false), 2f)
                                    .build())
                            .group(HalloweenModGroup.HALLOWEEN_GROUP)
            ));*/
    public static final RegistryObject<Item> MAGICAL_WAND =
            ITEMS.register("magical_wand", () ->
                    new WandItem(ItemTier.IRON, 3, -2.4f,
                            (new Item.Properties()
                                    .group(HalloweenModGroup.HALLOWEEN_GROUP)
                                    .setISTER(()-> MagicalWandRenderer::new))));
    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}
