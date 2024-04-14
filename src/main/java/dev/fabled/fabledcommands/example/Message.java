package dev.fabled.fabledcommands.example;

import dev.fabled.fabledcommands.FabledCommand;
import dev.fabled.fabledcommands.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command(
        name = "message",
        aliases = {"pm", "dm"},
        permission = "fabled.message",
        description = "Directly message another player!",
        usage = "/message <player> <message>"
)
public class Message extends FabledCommand {

    @Override
    public int console(@NotNull CommandSender sender) {
        sender.sendMessage("Please select a player!");
        return -1;
    }

    @Override
    public int player(@NotNull Player player) {
        player.sendMessage("Please select a player!");
        return -1;
    }

}
