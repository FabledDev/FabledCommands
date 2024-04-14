package dev.fabled.fabledcommands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class FabledCommand {

    final LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder;

    public FabledCommand(@NotNull final FabledSubCommand... subCommands) {
        literalArgumentBuilder = BrigadierUtils.literal("")
                .executes(context -> {
                    final CommandSender sender = BrigadierUtils.getSender(context);
                    if (!(sender instanceof Player player)) {
                        return console(sender);
                    }

                    return player(player);
                });
    }

    public abstract int console(@NotNull final CommandSender sender);
    public abstract int player(@NotNull final Player player);

}
