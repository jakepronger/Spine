package me.jakepronger.spine.api.command;

import org.bukkit.entity.Player;

public class CommandContext {

    private final Player player;
    private final String[] args;

    public CommandContext(Player player, String[] args) {
        this.player = player;
        this.args = args;
    }

    public Player player() {
        return player;
    }

    public String arg(int i) {
        return i < args.length ? args[i] : null;
    }
}
