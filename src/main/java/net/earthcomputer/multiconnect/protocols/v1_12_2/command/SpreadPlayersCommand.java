package net.earthcomputer.multiconnect.protocols.v1_12_2.command;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.DoubleArgumentType.doubleArg;
import static net.earthcomputer.multiconnect.protocols.v1_12_2.command.Commands_1_12_2.argument;
import static net.earthcomputer.multiconnect.protocols.v1_12_2.command.Commands_1_12_2.literal;
import static net.earthcomputer.multiconnect.protocols.v1_12_2.command.arguments.EntityArgumentType_1_12_2.entities;
import static net.minecraft.command.argument.Vec2ArgumentType.vec2;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;

public class SpreadPlayersCommand {

        public static void register(CommandDispatcher<CommandSource> dispatcher) {
                var respectTeams = argument("respectTeams", bool()).executes(ctx -> 0).build();
                var target = argument("target", entities()).executes(ctx -> 0).redirect(respectTeams).build();
                respectTeams.addChild(target);
                dispatcher.register(literal("spreadplayers")
                                .then(argument("center", vec2()).then(argument("spreadDistance", doubleArg(0))
                                                .then(argument("maxRange", doubleArg(1)).then(respectTeams)))));
        }

}
