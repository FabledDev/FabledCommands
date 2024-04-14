package dev.fabled.fabledcommands;

import com.mojang.brigadier.context.CommandContext;
import dev.fabled.fabledcommands.annotations.LiteralArg;
import dev.fabled.fabledcommands.annotations.RequiredArg;
import dev.fabled.fabledcommands.exceptions.InvalidSubCommandException;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.*;

public abstract class FabledSubCommand {

    protected final ArgBuilder<CommandSourceStack> argBuilder;

    public FabledSubCommand() throws InvalidSubCommandException {
        argBuilder = ArgBuilder.fromClass(this.getClass());
        argBuilder.executes(context -> {
            final CommandSender sender = BrigadierUtils.getSender(context);
            if (!(sender instanceof Player player)) {
                return console(sender, context);
            }

            return player(player, context);
        });

        final Map<Integer, ArgBuilder<CommandSourceStack>> methodMap = new HashMap<>();
        final Method[] methods = this.getClass().getMethods();

        for (final Method method : methods) {
            final ArgBuilder<CommandSourceStack> methodBuilder = ArgBuilder.fromMethod(method);
            int pos;

            if (method.isAnnotationPresent(LiteralArg.class)) {
                final LiteralArg arg = method.getAnnotation(LiteralArg.class);
                pos = arg.position();
            }

            else {
                final RequiredArg arg = method.getAnnotation(RequiredArg.class);
                pos = arg.position();
            }

            methodMap.put(pos, methodBuilder);
        }

        final List<Integer> keys = new ArrayList<>(methodMap.keySet());
        Collections.sort(keys);

        ArgBuilder<CommandSourceStack> argBuilders = null;
        for (int key : keys) {
            final ArgBuilder<CommandSourceStack> arg = methodMap.get(key);
            if (argBuilders == null) {
                argBuilders = arg;
                continue;
            }

            argBuilders.then(arg);
        }

        if (argBuilders == null) {
            return;
        }

        argBuilder.then(argBuilders);
    }

    public abstract int console(@NotNull final CommandSender sender, @NotNull final CommandContext<CommandSourceStack> context);
    public abstract int player(@NotNull final Player player, @NotNull final CommandContext<CommandSourceStack> context);

}
