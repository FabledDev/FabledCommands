package dev.fabled.fabledcommands;

import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.command.VanillaCommandWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FabledCommandManager {

    private final String fallbackPrefix;
    private final Commands commands;

    public FabledCommandManager(@NotNull final String fallbackPrefix) {
        this.fallbackPrefix = fallbackPrefix;
        commands = MinecraftServer.getServer().getCommands();
    }

    public void register(@NotNull final FabledCommand command) {
        if (command.node == null) {
            return;
        }

        String fallbackPrefix = command.getFallbackPrefix();
        if (fallbackPrefix == null) {
            fallbackPrefix = this.fallbackPrefix;
        }

        commands.getDispatcher().getRoot().addChild(command.node);
        final var wrapper = new VanillaCommandWrapper(commands, command.node);

        if (command.aliases != null && command.aliases.length > 0) {
            wrapper.setAliases(List.of(command.aliases));
        }

        if (command.permission != null && command.permission.length() > 0) {
            wrapper.setPermission(command.permission);
        }

        if (command.description != null && command.description.length() > 0) {
            wrapper.setDescription(command.description);
        }

        if (command.usage != null && command.usage.length() > 0) {
            wrapper.setUsage(command.usage);
        }

        Bukkit.getCommandMap().register(fallbackPrefix, wrapper);
    }

}
