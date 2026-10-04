package fr.fartunruss.additionalmod.items.wandAnim;

import fr.fartunruss.additionalmod.items.WandItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class MagicalWandRenderer extends GeoItemRenderer<WandItem> {
    public MagicalWandRenderer(){
        super(new MagicalWandModel());
    }
}
