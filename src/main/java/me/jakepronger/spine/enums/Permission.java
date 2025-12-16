package me.jakepronger.spine.enums;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public enum Permission {

    DEFAULT(null),
    STAFF("group.staff"),
    ADMIN("group.admin");

    private final String permissionNode;

    Permission(String permissionNode) {
        this.permissionNode = permissionNode;
    }

    public boolean has(CommandSourceStack source) {

        // Console always allowed
        if (!(source.getExecutor() instanceof Player player)) {
            return true;
        }

        // DEFAULT = no permission required
        if (permissionNode == null) {
            return true;
        }

        // ADMIN special-case: op OR permission
        if (this == ADMIN) {
            return player.isOp() || player.hasPermission(permissionNode);
        }

        return player.hasPermission(permissionNode);
    }
}
