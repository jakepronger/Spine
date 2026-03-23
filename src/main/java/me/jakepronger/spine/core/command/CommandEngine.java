package me.jakepronger.spine.core.command;

import me.jakepronger.spine.Spine;
import me.jakepronger.spine.api.command.CommandContext;
import me.jakepronger.spine.api.command.CommandExecutor;
import me.jakepronger.spine.internal.bukkit.BukkitCommandBridge;

public class CommandEngine {

    private final Spine spine;
    private final BukkitCommandBridge bcb;

    public CommandEngine(Spine spine) {
        this.spine = spine;
        this.bcb = new BukkitCommandBridge(spine.plugin());
    }

    public void register(String name, CommandExecutor executor) {
        spine.plugin().getCommand(name).setExecutor((sender, cmd, label, args) -> {
            executor.execute(new CommandContext(sender, args));
            return true;
        });
    }

}
