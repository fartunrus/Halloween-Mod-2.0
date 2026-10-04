/*package fr.fartunruss.halloweenmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.fartunruss.halloweenmod.events.OnEventCCC;
import fr.fartunruss.halloweenmod.events.ServerEventCCC;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class SetEventTime {

    public SetEventTime(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("eventtime").requires(source -> {
            return source.hasPermissionLevel(2);
                })
                .then(Commands.literal("set")
                        .then(Commands.argument("time", IntegerArgumentType.integer()).executes(context -> {
            return SetEventTime(context.getSource(), IntegerArgumentType.getInteger(context, "time"));
        }))));
    }

    private int SetEventTime(CommandSource source, int time) throws CommandSyntaxException {
        ServerEventCCC.eventTime= time;
        OnEventCCC.eventTime= time;
        source.sendFeedback(new StringTextComponent("time is "+ServerEventCCC.eventTime), true);
        return 1;
    }
}
*/