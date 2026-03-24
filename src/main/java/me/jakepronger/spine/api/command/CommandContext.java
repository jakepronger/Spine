package me.jakepronger.spine.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class CommandContext {

    private final CommandSender sender;
    private final String[] args;

    public CommandContext(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }

    public CommandSender sender() {
        return sender;
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    /**
     * @return The player, or null if the sender is Console/CommandBlock
     */
    @Nullable
    public Player player() {
        return sender instanceof Player p ? p : null;
    }

    public boolean hasArg(int index) {
        return index >= 0 && index < args.length;
    }

    public boolean hasArgs() {
        return args.length > 0;
    }

    /**
     * @return The argument at index i, or an empty string if it doesn't exist.
     * Efficiency: Uses the has() check to avoid IndexOutOfBounds.
     */
    public String arg(int i) {
        return hasArg(i) ? args[i] : "";
    }

    public int argsLength() {
        return args.length;
    }
}
