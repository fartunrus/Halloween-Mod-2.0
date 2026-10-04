package fr.fartunruss.additionalmod.setup;

import fr.fartunruss.additionalmod.HalloweenMod;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistriesParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HalloweenMod.MODID);

    public static final RegistryObject<BasicParticleType> MALUS_PARTICLES =
            PARTICLE_TYPES.register("malus_particles", ()->
                    new BasicParticleType(true));

    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
