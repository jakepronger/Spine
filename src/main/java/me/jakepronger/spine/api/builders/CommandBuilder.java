package me.jakepronger.spine.api.builders;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.jakepronger.spine.enums.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Must use register() to initialize.
 */
public class CommandBuilder {

    private final CommandRegister register;

    private final String name;
    private final Consumer<CommandSourceStack> action;

    @Nullable
    private String description;

    private Permission permission;
    private String[] aliases;

    // todo: tab completion system (preexisting selections PLAYER, INTEGER(range check))
    // todo: sub commands

    public CommandBuilder(CommandRegister register, String name, Consumer<CommandSourceStack> action) {
        this.register = register;
        this.name = name;
        this.action = action;
        this.aliases = new String[0];
        this.permission = Permission.DEFAULT;
    }

    public void register() {
        register.command(name, action, description, permission, aliases);
    }

    // todo: support colored descriptions?
    public CommandBuilder description(@Nullable String value) {
        this.description = value;
        return this;
    }

    // there are two different perms, these permission enum group and specifics... ??? what do we do, consider tab completions too
    public CommandBuilder permission(@NotNull Permission value) {
        this.permission = value;
        return this;
    }

    public CommandBuilder aliases(@NotNull String @NotNull ... value) {
        this.aliases = value;
        return this;
    }

}
