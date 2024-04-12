package dev.fabled.fabledcommands;

import dev.fabled.fabledcommands.annotations.*;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command(
        name = "example",
        aliases = {"ex"},
        permission = "fabled.example",
        description = "The example command for the FabledCommands library!",
        usage = "/example"
)
public class ExampleCommand extends FabledCommand {

    @Override
    public void consoleBase() {
        final ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("You ran the example command!");
        sender.sendMessage("Do 'example help' for more commands!");
    }

    @Override
    public void playerBase(@NotNull Player sender) {
        sender.sendMessage("You ran the example command!");
        sender.sendMessage("Do '/example help' for more commands!");
    }

    @SubCommand(
            name = "help",
            aliases = {"?"}
    )
    @ConsoleSender()
    public void help() {
        final ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("More example sub-commands:");
        sender.sendMessage("| example - The base command!");
        sender.sendMessage("| example help - This sub-command!");
        sender.sendMessage("| example test - The example test sub-command!");
    }

    @SubCommand(
            name = "help",
            aliases = {"?"}
    )
    @PlayerSender("fabled.example.help")
    public void help(@NotNull final Player sender) {
        sender.sendMessage("More example sub-commands:");
        sender.sendMessage("| /example - The base command!");
        sender.sendMessage("| /example help - This sub-command!");
        sender.sendMessage("| /example add - Add two numbers together!");
    }

    @SubCommand(
            name = "add"
    )
    @ConsoleSender()
    public void add(
            @Arg(type = ArgType.DOUBLE) final double one,
            @Arg(type = ArgType.DOUBLE) final double two
    ) {
        Bukkit.getConsoleSender().sendMessage("Result: " + (one + two));
    }

    @SubCommand(
            name = "add"
    )
    @PlayerSender("fabled.example.add")
    public void test(
            @NotNull final Player sender,
            @Arg(type = ArgType.DOUBLE, min = 0d, max = 100d) final double one,
            @Arg(type = ArgType.DOUBLE, min = 0d, max = 100d) final double two
    ) {
        sender.sendMessage("Result: " + (one + two));
    }

}
