package me.jakepronger.spine.managers;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.jakepronger.spine.builders.CommandBuilder;
import me.jakepronger.spine.builders.EventBuilder;
import me.jakepronger.spine.registers.CommandRegister;
import me.jakepronger.spine.registers.EventRegister;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class RegistryManager {

    private final EventRegister eventRegister;
    private final CommandRegister commandRegister;

    public RegistryManager(JavaPlugin plugin) {
        this.eventRegister = new EventRegister(plugin);
        this.commandRegister = new CommandRegister(plugin);
    }

    public <T extends Event> EventBuilder<T> event(Class<T> type, Consumer<T> action) {
        return new EventBuilder<>(eventRegister, type, action);
    }

    public CommandBuilder command(String name, Consumer<CommandSourceStack> action) {
        return new CommandBuilder(commandRegister, name, action);
    }

}
