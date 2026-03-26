package me.jakepronger.spine.core.command;

import me.jakepronger.spine.api.contract.CommandExecutor;
import me.jakepronger.spine.enums.Permission;
import me.jakepronger.spine.core.engines.CommandEngine;

import java.util.Arrays;

public class CommandRegistration {

    private final CommandEngine engine;
    private final CommandDefinition def;

    public CommandRegistration(CommandEngine engine, String name, CommandExecutor executor) {
        this.engine = engine;
        this.def = new CommandDefinition(name, executor);
    }

    public CommandRegistration description(String desc) {
        def.description = desc;
        return this;
    }

    public CommandRegistration permission(Permission perm) {
        def.permission = perm;
        return this;
    }

    public CommandRegistration alias(String... aliases) {
        def.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public CommandRegistration cooldown(int seconds) {
        def.cooldown = seconds;
        return this;
    }

    public void register() {
        engine.add(this.def);
    }
}
