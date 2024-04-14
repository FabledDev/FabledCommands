package dev.fabled.fabledcommands.annotations;

import dev.fabled.fabledcommands.ArgType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RequiredSubCommand {

    String name();
    String permission() default "";
    ArgType type() default ArgType.STRING;

}
