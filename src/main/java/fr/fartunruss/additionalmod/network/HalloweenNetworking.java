package fr.fartunruss.additionalmod.network;

import fr.fartunruss.additionalmod.HalloweenMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class HalloweenNetworking {
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE;
    public static void init(){
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(HalloweenMod.MODID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );
        int id = -1;
        INSTANCE.registerMessage(
                ++id,
                MagicalWandPacket.class,
                MagicalWandPacket::toBytes,
                MagicalWandPacket::new,
                MagicalWandPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
        INSTANCE.registerMessage(
                ++id,
                ParticlePacket.class,
                ParticlePacket::toBytes,
                ParticlePacket::new,
                ParticlePacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }
}
