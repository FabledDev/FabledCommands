package dev.fabled.fabledcommands;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class BrigadierUtils {

    public static LiteralArgumentBuilder<CommandSourceStack> literal(@NotNull final String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    public static <T> RequiredArgumentBuilder<CommandSourceStack, T> required(@NotNull final String name, @NotNull final ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    public static CommandSender getSender(@NotNull final CommandContext<CommandSourceStack> context) {
        return context.getSource().getBukkitSender();
    }

}
