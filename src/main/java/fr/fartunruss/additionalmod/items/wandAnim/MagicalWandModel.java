package fr.fartunruss.additionalmod.items.wandAnim;

import fr.fartunruss.additionalmod.HalloweenMod;
import fr.fartunruss.additionalmod.items.WandItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MagicalWandModel extends AnimatedGeoModel<WandItem> {
    @Override
    public ResourceLocation getModelLocation(WandItem object) {
        return new ResourceLocation(HalloweenMod.MODID, "geo/magical_wand.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WandItem object) {
        return new ResourceLocation(HalloweenMod.MODID, "textures/item/magical_wand.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WandItem animatable) {
        return new ResourceLocation(HalloweenMod.MODID, "animations/magical_wand.animation.json");
    }
}
