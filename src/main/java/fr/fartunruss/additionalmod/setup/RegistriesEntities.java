package fr.fartunruss.additionalmod.setup;

import fr.fartunruss.additionalmod.entities.GhostEntity;
import fr.fartunruss.additionalmod.HalloweenMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistriesEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, HalloweenMod.MODID);

    public static final RegistryObject<EntityType<GhostEntity>> GHOST =
            ENTITY_TYPES.register("ghost",
                    () -> EntityType.Builder.create(GhostEntity::new,
                            EntityClassification.MONSTER).size(1f, 2f)
                            .build(new ResourceLocation(HalloweenMod.MODID, "ghost").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
