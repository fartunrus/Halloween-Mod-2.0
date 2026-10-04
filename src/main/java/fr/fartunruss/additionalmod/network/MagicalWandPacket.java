package fr.fartunruss.additionalmod.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MagicalWandPacket {
    private final double x;
    private final double y;
    private final double z;
    public MagicalWandPacket(double x, double y, double z){
        this.x=x;this.y=y;this.z=z;
    }

    public MagicalWandPacket(PacketBuffer buffer){
        this.x = buffer.readDouble();
        this.y = buffer.readDouble();
        this.z = buffer.readDouble();
    }
    public void toBytes(PacketBuffer buffer){
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
    }
    public void handle(Supplier<NetworkEvent.Context> ctxSup){
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(()->{
            ServerPlayerEntity sender = ctx.getSender();
            if(sender!=null){
                Entity entity = sender.getEntity();
                if(entity instanceof PlayerEntity){
                    PlayerEntity player = (PlayerEntity) sender.getEntity();
                    player.setMotion(x, y, z);
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
