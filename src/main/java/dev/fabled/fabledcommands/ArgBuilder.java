package dev.fabled.fabledcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import dev.fabled.fabledcommands.annotations.*;
import dev.fabled.fabledcommands.exceptions.InvalidSubCommandException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

public class ArgBuilder<S> {

    private LiteralArgumentBuilder<S> literal;
    private RequiredArgumentBuilder<S, ?> required;
    private boolean isLiteral;

    ArgBuilder() {}

    public ArgBuilder(@NotNull final LiteralArgumentBuilder<S> literalArgumentBuilder) {
        literal = literalArgumentBuilder;
        isLiteral = true;
    }

    public ArgBuilder(@NotNull final RequiredArgumentBuilder<S, ?> requiredArgumentBuilder) {
        required = requiredArgumentBuilder;
        isLiteral = false;
    }

    public @NotNull ArgBuilder<S> then(@NotNull final ArgBuilder<S> builder) {
        if (isLiteral) {
            literal.then(builder.build());
        }

        else {
            required.then(builder.build());
        }

        return this;
    }

    public @NotNull ArgBuilder<S> then(@NotNull final LiteralArgumentBuilder<S> builder) {
        if (isLiteral) {
            literal.then(builder);
        }

        else {
            required.then(builder);
        }

        return this;
    }

    public @NotNull ArgBuilder<S> then(@NotNull final RequiredArgumentBuilder<S, ?> builder) {
        if (isLiteral) {
            literal.then(builder);
        }

        else {
            required.then(builder);
        }

        return this;
    }

    public @NotNull ArgBuilder<S> executes(@NotNull final Command<S> command) {
        if (isLiteral) {
            literal.executes(command);
        }

        else {
            required.executes(command);
        }

        return this;
    }

    public @NotNull ArgBuilder<S> suggests(@NotNull final List<String> suggestions) {
        if (isLiteral) {
            return this;
        }

        required.suggests((context, builder) ->
            SharedSuggestionProvider.suggest(suggestions, builder)
        );
        return this;
    }

    public @NotNull CommandNode<S> build() {
        if (isLiteral) {
            return literal.build();
        }

        return required.build();
    }

    public static @NotNull ArgBuilder<CommandSourceStack> fromClass(@NotNull final Class<?> clazz) throws InvalidSubCommandException{
        if (clazz.isAnnotationPresent(RequiredSubCommand.class)) {
            final RequiredSubCommand cmd = clazz.getAnnotation(RequiredSubCommand.class);
            return new ArgBuilder<>(BrigadierUtils.required(cmd.name(), cmd.type().get()));
        }

        if (!clazz.isAnnotationPresent(LiteralSubCommand.class)) {
            throw new InvalidSubCommandException(clazz);
        }

        final LiteralSubCommand cmd = clazz.getAnnotation(LiteralSubCommand.class);
        return new ArgBuilder<>(BrigadierUtils.literal(cmd.name()));
    }

    public static @NotNull ArgBuilder<CommandSourceStack> fromMethod(@NotNull final Method method) throws InvalidSubCommandException{
        if (!(method.isAnnotationPresent(ConsoleSender.class) || method.isAnnotationPresent(PlayerSender.class))) {
            throw new InvalidSubCommandException(method);
        }

        if (method.isAnnotationPresent(RequiredArg.class)) {
            final RequiredArg cmd = method.getAnnotation(RequiredArg.class);
            return new ArgBuilder<>(BrigadierUtils.required(cmd.name(), cmd.type().get()));
        }

        if (!method.isAnnotationPresent(LiteralArg.class)) {
            throw new InvalidSubCommandException(method);
        }

        final LiteralArg cmd = method.getAnnotation(LiteralArg.class);
        return new ArgBuilder<>(BrigadierUtils.literal(cmd.name()));
    }

}
