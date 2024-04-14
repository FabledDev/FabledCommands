package dev.fabled.fabledcommands.exceptions;

import org.jetbrains.annotations.NotNull;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException() {}

    public InvalidCommandException(@NotNull final Class<?> clazz) {
        super("The command class " + clazz.getSimpleName() + " is not annotated properly!");
    }

}
