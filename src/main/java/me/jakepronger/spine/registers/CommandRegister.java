package me.jakepronger.spine.registers;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.jakepronger.spine.enums.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class CommandRegister {

    private final JavaPlugin plugin;

    public CommandRegister(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void command(String name,
                        Consumer<CommandSourceStack> action,
                        @Nullable String description,
                        Permission permission,
                        String... aliases
    ) {
        LiteralCommandNode<CommandSourceStack> mainNode = Commands.literal(name)
                .requires(permission::has)
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
                    for (String alias : aliases) {
                        LiteralCommandNode<CommandSourceStack> aliasNode = Commands.literal(alias)
                                .requires(permission::has) // Must have same requirement for tab-complete
                                .redirect(mainNode)  // Points all logic to the main node
                                .build();

                        commands.registrar().register(aliasNode, description);
                    }
                }
        );
    }

}
