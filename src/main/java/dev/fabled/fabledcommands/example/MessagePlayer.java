package dev.fabled.fabledcommands.example;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.fabled.fabledcommands.ArgType;
import dev.fabled.fabledcommands.FabledSubCommand;
import dev.fabled.fabledcommands.annotations.RequiredArg;
import dev.fabled.fabledcommands.annotations.RequiredSubCommand;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredSubCommand(
        name = "player",
        permission = "fabled.message",
        type = ArgType.WORD
)
public class MessagePlayer extends FabledSubCommand {

    @Override
    public int console(@NotNull final CommandSender sender, @NotNull final CommandContext<CommandSourceStack> context) {
        final String username = StringArgumentType.getString(context, "player");
        final Player target = Bukkit.getPlayer(username);

        if (target == null) {
            sender.sendMessage("Invalid player!");
            return -1;
        }

        return 0;
    }

    @Override
    public int player(@NotNull final Player player, @NotNull final CommandContext<CommandSourceStack> context) {
        final String username = StringArgumentType.getString(context, "player");
        final Player target = Bukkit.getPlayer(username);

        if (target == null) {
            player.sendMessage("Invalid player!");
            return -1;
        }

        return 0;
    }

    @RequiredArg(
            name = "message",
            type = ArgType.GREEDY_STRING
    )
    public int consoleMessage(@NotNull final CommandSender sender, @NotNull final CommandContext<CommandSourceStack> context) {
        final String username = StringArgumentType.getString(context, "player");
        final Player target = Bukkit.getPlayer(username);
        if (target == null) {
            sender.sendMessage("Invalid player!");
            return -1;
        }

        final String message = StringArgumentType.getString(context, "message");
        target.sendMessage("[console -> me] " + message);
        sender.sendMessage("[me -> " + target.getName() + "] " + message);
        return 0;
    }

    @RequiredArg(
            name = "message",
            type = ArgType.GREEDY_STRING
    )
    public int playerMessage(@NotNull final Player player, @NotNull final CommandContext<CommandSourceStack> context) {
        final String username = StringArgumentType.getString(context, "player");
        final Player target = Bukkit.getPlayer(username);
        if (target == null) {
            player.sendMessage("Invalid player!");
            return -1;
        }

        final String message = StringArgumentType.getString(context, "message");
        target.sendMessage("[" + player.getName() + " -> me] " + message);
        player.sendMessage("[me -> " + target.getName() + "] " + message);
        return 0;
    }

}
