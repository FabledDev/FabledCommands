package dev.fabled.fabledcommands.example;

import dev.fabled.fabledcommands.FabledCommand;
import dev.fabled.fabledcommands.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command(
        name = "example",
        aliases = {"ex"},
        permission = "fabled.example",
        description = "FabledCommands library example command!",
        usage = "/example help"
)
public class Example extends FabledCommand {

    public Example() {
        super(
                new ExampleHelp()
        );
    }

    @Override
    public int console(@NotNull final CommandSender sender) {

        return 0;
    }

    @Override
    public int player(@NotNull final Player player) {

        return 0;
    }

}
