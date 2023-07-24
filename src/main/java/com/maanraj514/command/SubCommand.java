package com.maanraj514.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * This is the parent class of subCommands.
 */
public abstract class SubCommand {

    /*
    * The name of the command.
    */
    public abstract String getName();

    /*
     * The aliases of the command.
     */
    public abstract List<String> getAliases();

    /*
     * The description of the command.
     */
    public abstract String getDescription();

    /*
     * The syntax of the command.
     */
    public abstract String getSyntax();

    /*
     * The permission to use the command.
     */
    public abstract String getPermission();

    /**
     * The perform method of the command.
     *
     * @param sender who performs the command.
     * @param args the arguments for the command.
     */
    public abstract void perform(CommandSender sender, String[] args);

    /**
     * Gets the subCommandArguments.
     *
     * @param player who we get the args from.
     * @param args the arguments of the subCommand.
     * @return subCommandArguments.
     */
    public abstract List<String> getSubCommandArguments(Player player, String[] args);
}
