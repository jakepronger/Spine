package me.jakepronger.spine.api.command;

@FunctionalInterface
public interface CommandExecutor {
    void execute(CommandContext ctx);
}
