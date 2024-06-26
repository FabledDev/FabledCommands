package dev.fabled.fabledcommands.annotations;

import dev.fabled.fabledcommands.ArgType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredArg {

    int position();
    String name();
    ArgType type();

}
