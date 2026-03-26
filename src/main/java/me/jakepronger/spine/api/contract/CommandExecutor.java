package me.jakepronger.spine.api.contract;

import me.jakepronger.spine.api.command.CommandContext;

@FunctionalInterface
public interface CommandExecutor {
    void execute(CommandContext ctx);
}
