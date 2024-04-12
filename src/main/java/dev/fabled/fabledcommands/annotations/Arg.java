package dev.fabled.fabledcommands.annotations;

import dev.fabled.fabledcommands.ArgType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Arg {

    ArgType type() default ArgType.STRING;

    /**
     * For number argument types<br>
     * No use defining for the console commands
     * @return double
     */
    double min() default Double.MIN_VALUE;

    /**
     * For number argument types<br>
     * No use defining for the console commands
     * @return double
     */
    double max() default Double.MAX_VALUE;

}
