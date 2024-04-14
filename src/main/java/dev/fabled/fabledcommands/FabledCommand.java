package dev.fabled.fabledcommands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import dev.fabled.fabledcommands.annotations.Command;
import dev.fabled.fabledcommands.exceptions.InvalidCommandException;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FabledCommand {

    final String name;
    final String[] aliases;
    final String permission;
    final String description;
    final String usage;
    final CommandNode<CommandSourceStack> node;
    private String fallbackPrefix;

    public FabledCommand(@NotNull final FabledSubCommand... subCommands) throws InvalidCommandException {
        final Class<?> clazz = this.getClass();
        if (!clazz.isAnnotationPresent(Command.class)) {
            throw new InvalidCommandException(clazz);
        }

        final Command commandInfo = clazz.getAnnotation(Command.class);
        name = commandInfo.name().toLowerCase();
        aliases = commandInfo.aliases();
        permission = commandInfo.permission();
        description = commandInfo.description();
        usage = commandInfo.usage();

        final LiteralArgumentBuilder<CommandSourceStack> builder = BrigadierUtils.literal(name)
                .executes(context -> {
                    final CommandSender sender = BrigadierUtils.getSender(context);
                    if (!(sender instanceof Player player)) {
                        return console(sender);
                    }

                    return player(player);
                });

        for (final FabledSubCommand subCommand : subCommands) {
            builder.then(subCommand.argBuilder.build());
        }

        node = builder.build();
    }

    public void setFallbackPrefix(@NotNull final String fallbackPrefix) {
        this.fallbackPrefix = fallbackPrefix;
    }

    public @Nullable String getFallbackPrefix() {
        return fallbackPrefix;
    }

    public abstract int console(@NotNull final CommandSender sender);
    public abstract int player(@NotNull final Player player);

}
