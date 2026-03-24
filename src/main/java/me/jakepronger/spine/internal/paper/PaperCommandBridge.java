package me.jakepronger.spine.internal.paper;

import com.mojang.brigadier.arguments.StringArgumentType;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.jakepronger.spine.api.command.CommandContext;
import me.jakepronger.spine.core.command.CommandDefinition;
import me.jakepronger.spine.core.command.CommandEngine;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperCommandBridge {

    public PaperCommandBridge(JavaPlugin plugin, CommandEngine engine) {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            var registrar = event.registrar();
            for (CommandDefinition def : engine.definitions()) {
                registerCommand(registrar, def);
            }
        });
    }

    // todo: will need extensive testing
    private void registerCommand(Commands registrar, CommandDefinition def) {
        // 1. Setup the Permission check once
        java.util.function.Predicate<CommandSourceStack> hasPerm = stack -> def.permission.has(stack.getSender());

        // 2. The Base Command (/cmd)
        var literal = Commands.literal(def.name).requires(hasPerm);

        // 3. Handle NO arguments (Fastest path)
        literal.executes(ctx -> execute(def, ctx.getSource(), new String[0]));

        // 4. Handle Arguments (/cmd <args>)
        literal.then(Commands.argument("args", StringArgumentType.greedyString())
                .executes(ctx -> {
                    String raw = ctx.getArgument("args", String.class).trim();

                    // PERFORMANCE: If the string is empty after trimming, don't split.
                    if (raw.isEmpty()) {
                        return execute(def, ctx.getSource(), new String[0]);
                    }

                    // PERFORMANCE: Use a pre-compiled regex or simple split
                    // "\\s+" is the standard for "one or more spaces"
                    return execute(def, ctx.getSource(), raw.split("\\s+"));
                }));

        // 5. Build the node once to avoid re-calculating it for aliases
        var mainNode = literal.build();
        registrar.register(mainNode, def.description);

        // 6. Register Aliases (Point them to the existing mainNode)
        for (String alias : def.aliases) {
            registrar.register(
                    Commands.literal(alias)
                            .requires(hasPerm)
                            .redirect(mainNode) // Redirect is extremely efficient; it's a pointer
                            .build(),
                    def.description
            );
        }
    }

    // Note: Use 'CommandSourceStack' from Paper, not the Brigadier 'CommandContext'
    private int execute(CommandDefinition def, CommandSourceStack source, String[] args) {
        def.executor.execute(new CommandContext(source.getSender(), args));
        return 1;
    }

}
