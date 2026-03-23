package me.jakepronger.spine.internal.bukkit;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.jakepronger.spine.api.command.CommandContext;
import me.jakepronger.spine.core.command.CommandDefinition;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitCommandBridge {

    private final JavaPlugin plugin;

    public BukkitCommandBridge(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(CommandDefinition def) {

        plugin.getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                event -> {

                    var registrar = event.registrar();

                    LiteralCommandNode<CommandSourceStack> mainNode =
                            Commands.literal(def.name)
                                    .requires(src -> def.permission.has(src.getSender()))
                                    .executes(ctx -> {

                                        def.executor.execute(
                                                new CommandContext(
                                                        ctx.getSource().getSender(),
                                                        new String[]{}
                                                )
                                        );

                                        return Command.SINGLE_SUCCESS;
                                    })
                                    .build();

                    registrar.register(mainNode, def.description);

                    for (String alias : def.aliases) {
                        registrar.register(
                                Commands.literal(alias)
                                        .requires(src -> def.permission.has(src.getSender()))
                                        .redirect(mainNode)
                                        .build(),
                                def.description
                        );
                    }
                }
        );
    }
}
