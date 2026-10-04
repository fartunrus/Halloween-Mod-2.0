package fr.fartunruss.additionalmod.network;

import fr.fartunruss.additionalmod.events.ServerEventCCC;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ParticlePacket {
    private final int gg;
    public ParticlePacket(int gg){
        this.gg=gg;
    }

    public ParticlePacket(PacketBuffer buffer){
        this.gg = buffer.readInt();
    }
    public void toBytes(PacketBuffer buffer){
        buffer.writeInt(gg);
    }
    public void handle(Supplier<NetworkEvent.Context> ctxSup){
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(()->{
            ServerPlayerEntity sender = ctx.getSender();
            if(sender!=null){
                ServerEventCCC.SpawnParticles(sender, ParticleTypes.PORTAL, gg, 0.5, 0.5, 0.5, -0.2);
            }
        });
        ctx.setPacketHandled(true);
    }
}
