package me.jakepronger.spine.core.command;

import me.jakepronger.spine.api.contract.CommandExecutor;
import me.jakepronger.spine.enums.Permission;

import java.util.ArrayList;
import java.util.List;

public class CommandDefinition {

    public String name;
    public CommandExecutor executor;

    public String description;

    public Permission permission = Permission.DEFAULT;
    public int cooldown = 0;
    public List<String> aliases = new ArrayList<>();

    public CommandDefinition(String name, CommandExecutor executor) {
        this.name = name;
        this.executor = executor;
    }

}
