package fr.fartunruss.additionalmod.entities;

import fr.fartunruss.additionalmod.HalloweenMod;
import fr.fartunruss.additionalmod.setup.RegistriesEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HalloweenMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addEntityAtrributes(EntityAttributeCreationEvent event){
        event.put(RegistriesEntities.GHOST.get(), GhostEntity.registerAttributes().create());
    }
}
