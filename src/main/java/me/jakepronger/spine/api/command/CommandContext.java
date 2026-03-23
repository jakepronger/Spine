package me.jakepronger.spine.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandContext {

    private final CommandSender sender;
    private final String[] args;

    public CommandContext(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public Player player() {
        return (Player) sender;
    }

    public String arg(int i) {
        return i < args.length ? args[i] : null;
    }
}
