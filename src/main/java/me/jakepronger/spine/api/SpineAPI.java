package me.jakepronger.spine.api;

import me.jakepronger.spine.Spine;
import me.jakepronger.spine.api.contract.CommandExecutor;
import me.jakepronger.spine.core.engines.CommandEngine;
import me.jakepronger.spine.core.command.CommandRegistration;
import org.bukkit.plugin.java.JavaPlugin;

public class SpineAPI {

    private final Spine spine;

    private final CommandEngine commandEngine;

    public SpineAPI(Spine spine) {
        this.spine = spine;
        this.commandEngine = new CommandEngine(spine.plugin());
        //this.listenerEngine = new ListenerEngine(spine);
    }

    public JavaPlugin plugin() {
        return spine.plugin();
    }

    public CommandRegistration command(String name, CommandExecutor executor) {
        return new CommandRegistration(commandEngine, name, executor);
    }

    /*
    public void listen(Class<?> event, EventListener listener) {
        listenerEngine.register(event, listener);
    }
     */

}
