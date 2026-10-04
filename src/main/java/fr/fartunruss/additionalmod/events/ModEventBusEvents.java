package fr.fartunruss.additionalmod.events;

import fr.fartunruss.additionalmod.HalloweenMod;
import fr.fartunruss.additionalmod.particles.MalusParticle;
import fr.fartunruss.additionalmod.setup.RegistriesParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HalloweenMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particles.registerFactory(RegistriesParticles.MALUS_PARTICLES.get(),
                MalusParticle.Factory::new);
    }
}
