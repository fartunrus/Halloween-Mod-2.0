/*package fr.fartunruss.halloweenmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.fartunruss.halloweenmod.events.OnEventCCC;
import fr.fartunruss.halloweenmod.events.ServerEventCCC;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class SetEternalLifeTime {

    public SetEternalLifeTime(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("eternallife").requires(source -> {
                    return source.hasPermissionLevel(2);
                })
                .then(Commands.literal("set")
                        .then(Commands.argument("time", IntegerArgumentType.integer()).executes(context -> {
                            return setEternalLife(context.getSource(), IntegerArgumentType.getInteger(context, "time"));
                        }))));
    }

    private int setEternalLife(CommandSource source, int time) throws CommandSyntaxException{
        ServerEventCCC.timeBeforeEternalLife= time;
        OnEventCCC.timeBeforeEternalLife= time;
        source.sendFeedback(new StringTextComponent("time is "+ServerEventCCC.timeBeforeEternalLife), true);
        return 1;
    }
}*/
