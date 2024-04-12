package dev.fabled.fabledcommands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import dev.fabled.fabledcommands.annotations.Command;
import dev.fabled.fabledcommands.annotations.PlayerSender;
import dev.fabled.fabledcommands.annotations.SubCommand;
import dev.fabled.fabledcommands.exceptions.InvalidCommandException;
import dev.fabled.fabledcommands.exceptions.InvalidSubCommandException;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public abstract class FabledCommand {

    private @NotNull final String name;
    private @NotNull final String[] aliases;
    private @NotNull final String permission;
    private @NotNull final String description;
    private @NotNull final String usage;
    private @NotNull final CommandNode<CommandSourceStack> commandNode;

    public FabledCommand() throws InvalidCommandException, InvalidSubCommandException {
        final Class<?> clazz = getClass();
        if (!clazz.isAnnotationPresent(Command.class)) {
            throw new InvalidCommandException("The " + clazz.getSimpleName() + " was not annotated with the Command annotation!");
        }

        final Command commandInfo = clazz.getAnnotation(Command.class);
        if (commandInfo.name().length() < 1) {
            throw new InvalidCommandException("The " + clazz.getSimpleName() + "'s command name is invalid!");
        }

        final String commandName = commandInfo.name().toLowerCase();
        this.name = commandName;
        commandNode = BrigadierUtils.literal(commandName).build();

        aliases = commandInfo.aliases();
        permission = commandInfo.permission();
        description = commandInfo.description();
        usage = commandInfo.usage();

        final Method[] methods = clazz.getMethods();
        for (final Method method : methods) {
            if (!method.isAnnotationPresent(SubCommand.class)) {
                continue;
            }

            final SubCommand subCommand = method.getAnnotation(SubCommand.class);
            final String subName = subCommand.name().toLowerCase();
            final String[] subAliases = subCommand.aliases();

            final LiteralArgumentBuilder<CommandSourceStack> builder = BrigadierUtils.literal(subName);

            final Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                final Parameter parameter = parameters[i];
                final Class<?> paramType = parameter.getType();

                if (method.isAnnotationPresent(PlayerSender.class) && i == 0 && !paramType.equals(Player.class)) {
                    throw new InvalidSubCommandException("Sub-command " + method.getName() + " is annotated with PlayerSender but does not have Player as the first parameter!");
                }

                // append to builder
            }

            final CommandNode<CommandSourceStack> node = builder.build();

            for (final String subAlias : subAliases) {
                commandNode.addChild(BrigadierUtils.literal(subAlias.toLowerCase()).redirect(node).build());
            }

            commandNode.addChild(node);
        }
    }

    /**
     * This method is called when the command is executed without arguments by the console!
     */
    public abstract void consoleBase();

    /**
     * This method is called when the command is executed without arguments by a Player!
     * @param sender Player
     */
    public abstract void playerBase(@NotNull final Player sender);

    public @NotNull CommandNode<CommandSourceStack> getCommandNode() {
        return commandNode;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String[] getAliases() {
        return aliases;
    }

    public @NotNull String getPermission() {
        return permission;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public @NotNull String getUsage() {
        return usage;
    }

}
