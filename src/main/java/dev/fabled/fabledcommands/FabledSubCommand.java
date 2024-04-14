package dev.fabled.fabledcommands;

import com.mojang.brigadier.context.CommandContext;
import dev.fabled.fabledcommands.exceptions.InvalidSubCommandException;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class FabledSubCommand {

    private final ArgBuilder<CommandSourceStack> argBuilder;

    public FabledSubCommand() throws InvalidSubCommandException {
        argBuilder = ArgBuilder.fromClass(this.getClass());
        argBuilder.executes(context -> {
            final CommandSender sender = BrigadierUtils.getSender(context);
            if (!(sender instanceof Player player)) {
                return console(sender, context);
            }

            return player(player, context);
        });
    }

    public abstract int console(@NotNull final CommandSender sender, @NotNull final CommandContext<CommandSourceStack> context);
    public abstract int player(@NotNull final Player player, @NotNull final CommandContext<CommandSourceStack> context);

}
