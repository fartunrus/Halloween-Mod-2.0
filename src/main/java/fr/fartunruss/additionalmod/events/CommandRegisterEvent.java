/*package fr.fartunruss.halloweenmod.events;

import fr.fartunruss.halloweenmod.HalloweenMod;
import fr.fartunruss.halloweenmod.commands.SetEternalLifeTime;
import fr.fartunruss.halloweenmod.commands.SetEventTime;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = HalloweenMod.MODID)
public class CommandRegisterEvent {

    @SubscribeEvent
    public static void OnCommandsRegister(RegisterCommandsEvent event){
        new SetEventTime(event.getDispatcher());
        new SetEternalLifeTime(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }
}
*/