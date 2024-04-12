package dev.fabled.fabledcommands.exceptions;

import org.jetbrains.annotations.NotNull;

public class InvalidSubCommandException extends RuntimeException {

    public InvalidSubCommandException() {}

    public InvalidSubCommandException(@NotNull final String message) {
        super(message);
    }

}
