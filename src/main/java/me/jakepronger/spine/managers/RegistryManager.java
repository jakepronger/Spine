package me.jakepronger.spine.managers;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.jakepronger.spine.builders.CommandBuilder;
import me.jakepronger.spine.builders.ListenerBuilder;
import me.jakepronger.spine.registers.CommandRegister;
import me.jakepronger.spine.registers.ListenerRegister;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class RegistryManager {

    private final ListenerRegister listenerRegister;
    private final CommandRegister commandRegister;

    public RegistryManager(JavaPlugin plugin) {
        this.listenerRegister = new ListenerRegister(plugin);
        this.commandRegister = new CommandRegister(plugin);
    }

    public <T extends Event> ListenerBuilder<T> listener(Class<T> type, Consumer<T> action) {
        return new ListenerBuilder<>(listenerRegister, type, action);
    }

    public CommandBuilder command(String name, Consumer<CommandSourceStack> action) {
        return new CommandBuilder(commandRegister, name, action);
    }

}
