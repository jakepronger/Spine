package me.jakepronger.spine.core.command;

import me.jakepronger.spine.Spine;
import me.jakepronger.spine.api.command.CommandContext;
import me.jakepronger.spine.api.command.CommandExecutor;
import org.bukkit.entity.Player;

public class CommandEngine {

    private final Spine spine;

    public CommandEngine(Spine spine) {
        this.spine = spine;
    }

    public void register(String name, CommandExecutor executor) {
        spine.plugin().getCommand(name).setExecutor((sender, cmd, label, args) -> {
            executor.execute(new CommandContext((Player) sender, args));
            return true;
        });
    }

}
