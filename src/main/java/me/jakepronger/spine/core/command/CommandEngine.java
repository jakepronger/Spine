package me.jakepronger.spine.core.command;

import me.jakepronger.spine.internal.paper.PaperCommandBridge;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandEngine {

    // This list holds your commands until Paper is ready to register them
    private final List<CommandDefinition> definitions;

    public CommandEngine(JavaPlugin plugin) {
        this.definitions = new ArrayList<>();
        new PaperCommandBridge(plugin, this);
    }

    public void add(CommandDefinition def) {
        definitions.add(def);
    }

    public List<CommandDefinition> definitions() {
        return definitions;
    }

}
