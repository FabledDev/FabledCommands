package dev.fabled.fabledcommands;

import com.mojang.brigadier.arguments.*;
import org.jetbrains.annotations.NotNull;

public enum ArgType {

    /**
     * A single word<br>
     * Example:<br>- TheString
     */
    WORD(StringArgumentType.word()),
    /**
     * Multiple words separated by spaces encased in double or single quotes<br>
     * Example:<br>- "This is the entire String!"
     */
    STRING(StringArgumentType.string()),
    /**
     * Multiple words separated by spaces, must be the last argument<br>
     * Example:<br>- This is the entire string!
     */
    GREEDY_STRING(StringArgumentType.greedyString()),
    BOOL(BoolArgumentType.bool()),
    INTEGER(IntegerArgumentType.integer()),
    LONG(LongArgumentType.longArg()),
    FLOAT(FloatArgumentType.floatArg()),
    DOUBLE(DoubleArgumentType.doubleArg());

    private final ArgumentType<?> argumentType;

    ArgType(ArgumentType<?> argumentType) {
        this.argumentType = argumentType;
    }

    public ArgumentType<?> get() {
        return argumentType;
    }

//    /**
//     * Attempts to return the ArgType based on the object!<
//     * @param object Object
//     * @return ArgType (defaults to WORD)
//     */
//    public static @NotNull ArgType fromObject(@NotNull final Object object) {
//        if (object instanceof Boolean) return BOOL;
//        if (object instanceof Integer) return INTEGER;
//        if (object instanceof Long) return LONG;
//        if (object instanceof Float) return FLOAT;
//        if (object instanceof Double) return DOUBLE;
//
//        if (object instanceof String) {
//            final String string = object.toString();
//
//            if (string.contains(" ")) {
//                if (string.startsWith("\"") && string.endsWith("\"")) {
//                    return STRING;
//                }
//
//                return GREEDY_STRING;
//            }
//        }
//
//        return WORD;
//    }

}
