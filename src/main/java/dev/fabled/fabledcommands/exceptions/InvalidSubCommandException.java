package dev.fabled.fabledcommands.exceptions;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class InvalidSubCommandException extends RuntimeException {

    public InvalidSubCommandException() {}

    public InvalidSubCommandException(@NotNull final Class<?> clazz) {
        super("Sub-command class " + clazz.getSimpleName() + " is not annotated correctly!");
    }

    public InvalidSubCommandException(@NotNull final Method method) {
        super("Sub-command class " + method.getDeclaringClass().getSimpleName() + "'s method " + method.getName() + " is not annotated correctly!");
    }

}
