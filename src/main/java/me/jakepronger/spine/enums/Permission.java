package me.jakepronger.spine.enums;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public enum Permission {

    DEFAULT(""),
    DONATOR("group.donator"),
    MOD("group.mod"),
    ADMIN("group.admin");

    private final String permissionNode;

    Permission(String permissionNode) {
        this.permissionNode = permissionNode;
    }

    public boolean has(CommandSourceStack source) {

        // DEFAULT = no permission required
        if (permissionNode.isEmpty())
            return true;

        // Console always allowed
        if (!(source.getExecutor() instanceof Player player))
            return true;

        // ADMIN special-case: op OR permission
        if (this == ADMIN) {
            return player.isOp() || player.hasPermission(permissionNode);
        }

        return player.hasPermission(permissionNode);
    }
}
