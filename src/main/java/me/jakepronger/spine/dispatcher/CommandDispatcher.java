package me.jakepronger.spine.dispatcher;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.jakepronger.spine.enums.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class CommandDispatcher {

    private final JavaPlugin plugin;

    public CommandDispatcher(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void command(String name,
                        Consumer<CommandSourceStack> action,
                        @Nullable String description,
                        @Nullable Permission permission,
                        @Nullable String... aliases
    ) {
        Permission commandPermission = (permission != null) ? permission : Permission.DEFAULT;
        String[] commandAliases = (aliases != null) ? aliases : new String[0];

        LiteralCommandNode<CommandSourceStack> mainNode = Commands.literal(name)
                .requires(commandPermission::has)
                .executes(ctx -> {
                    action.accept(ctx.getSource());
                    return Command.SINGLE_SUCCESS;
                })
                .build();

        plugin.getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                commands -> {
                    commands.registrar().register(mainNode, description);

                    // Register each alias as a redirection to the main node
                    for (String alias : commandAliases) {
                        LiteralCommandNode<CommandSourceStack> aliasNode = Commands.literal(alias)
                                .requires(commandPermission::has) // Must have same requirement for tab-complete
                                .redirect(mainNode)  // Points all logic to the main node
                                .build();

                        commands.registrar().register(aliasNode, description);
                    }
                }
        );
    }

}
