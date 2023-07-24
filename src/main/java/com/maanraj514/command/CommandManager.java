package com.maanraj514.command;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This manages the commands, createCoreCommand etc.
 */
public class CommandManager {

    /**
     * Creates a new core command
     * with the following params.
     *
     * @param plugin the main plugin instance.
     * @param commandName the name of the subCommand.
     * @param commandDescription the description of the subCommand.
     * @param commandUsage how you use the command.
     * @param commandList how the message should be sent.
     * @param aliases alternate names for the command.
     * @param subcommands the subCommands registered under the core command.
     * @throws NoSuchFieldException if the field doesn't exist.
     * @throws IllegalAccessException not allowed to access the instance.
     */
    @SafeVarargs
    public final void createCoreCommand(@NotNull JavaPlugin plugin, @NotNull String commandName, @NotNull String commandDescription, @NotNull String commandUsage, @Nullable CommandList commandList, List<String> aliases, @NotNull Class<? extends SubCommand>... subcommands) throws NoSuchFieldException, IllegalAccessException {

        ArrayList<SubCommand> commands = new ArrayList<>();

        Arrays.stream(subcommands).map(subcommand -> {
            try {
                Constructor<? extends SubCommand> constructor = subcommand.getConstructor();
                return constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(commands::add);

        Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
        commandField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandField.get(plugin.getServer());
        commandMap.register(commandName, new CoreCommand(commandName, commandDescription, commandUsage, commandList, aliases, commands));
    }

    /**
     * Creates a new core command
     * with the following params.
     *
     * @param plugin the main plugin instance.
     * @param commandName the name of the subCommand.
     * @param commandDescription the description of the subCommand.
     * @param commandUsage how you use the command.
     * @param commandList how the message should be sent.
     * @param subCommands the subCommands registered under the core command.
     * @throws NoSuchFieldException if the field doesn't exist.
     * @throws IllegalAccessException not allowed to access the instance.
     */
    @SafeVarargs
    public final void createCoreCommand(@NotNull JavaPlugin plugin, @NotNull String commandName, @NotNull String commandDescription, @NotNull String commandUsage, @Nullable CommandList commandList, @NotNull Class<? extends SubCommand>... subCommands) throws NoSuchFieldException, IllegalAccessException {
        createCoreCommand(plugin, commandName, commandDescription, commandUsage, commandList, Collections.singletonList(""), subCommands);
    }
}