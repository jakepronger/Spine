package me.jakepronger.spine.api;

import me.jakepronger.spine.Spine;
import me.jakepronger.spine.api.command.CommandExecutor;
import me.jakepronger.spine.core.FeatureEngine;
import me.jakepronger.spine.core.command.CommandEngine;
import me.jakepronger.spine.core.listener.ListenerEngine;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;

public class SpineAPI {

    private final Spine spine;

    private final CommandEngine commandEngine;
    private final ListenerEngine listenerEngine;
    private final FeatureEngine featureEngine;

    public SpineAPI(Spine spine) {
        this.spine = spine;
        this.commandEngine = new CommandEngine(spine);
        this.listenerEngine = new ListenerEngine(spine);
        this.featureEngine = new FeatureEngine(spine);
    }

    public JavaPlugin plugin() {
        return spine.plugin();
    }

    // clean shortcuts (THIS is your “feel good API” layer)
    public void command(String name, CommandExecutor executor) {
        commands().register(name, executor);
    }

    public void listen(Class<?> event, EventListener listener) {
        api.events().register(event, listener);
    }

}
