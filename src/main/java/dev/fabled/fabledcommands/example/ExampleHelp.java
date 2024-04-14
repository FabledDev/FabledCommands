package dev.fabled.fabledcommands.example;

import com.mojang.brigadier.context.CommandContext;
import dev.fabled.fabledcommands.FabledSubCommand;
import dev.fabled.fabledcommands.annotations.LiteralSubCommand;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@LiteralSubCommand(
        name = "help",
        aliases = {"?"},
        permission = "fabled.example.help",
        description = "Lists the example sub-commands!",
        usage = "/example help"
)
public class ExampleHelp extends FabledSubCommand {

    public ExampleHelp() {

    }

    @Override
    public int console(@NotNull final CommandSender sender, @NotNull final CommandContext<CommandSourceStack> context) {
        sender.sendMessage("Example Help:");
        sender.sendMessage("| example - The example command!");
        sender.sendMessage("| example help - Shows this help information!");
        return 0;
    }

    @Override
    public int player(@NotNull Player player, @NotNull final CommandContext<CommandSourceStack> context) {
        player.sendMessage("Example Help:");
        player.sendMessage("| example - The example command!");
        player.sendMessage("| example help - Shows this help information!");
        return 0;
    }

}
