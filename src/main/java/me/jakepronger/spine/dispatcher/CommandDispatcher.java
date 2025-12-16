package me.jakepronger.spine.dispatcher;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.jakepronger.spine.enums.Permission;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class CommandDispatcher {

    private final JavaPlugin plugin;

    public CommandDispatcher(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void command(String name,
                        Consumer<CommandSourceStack> action,
                        String description,
                        Permission permission
    ) {
        LiteralCommandNode<CommandSourceStack> node = Commands.literal(name)
                .executes(ctx -> {
                    CommandSourceStack source = ctx.getSource();

                    if (!permission.has(source)) {
                        source.getSender().sendMessage(Component.text("You do not have permission.")); // todo: configurable permission message?
                        return 0;
                    }

                    action.accept(source);
                    return Command.SINGLE_SUCCESS;
                })
                .build();

        plugin.getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                commands -> commands.registrar().register(node, description)
        );
    }

}
