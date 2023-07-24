package com.maanraj514.command;

import com.maanraj514.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CoreCommand extends Command {

    /*
    * The ArrayList for subCommands.
    */
    private final ArrayList<SubCommand> subCommands;
    /*
    * The commandList instance.
    */
    private final CommandList commandList;

    /**
     * Creates a new instance of
     * CoreCommand.
     *
     * @param name the name of the command.
     * @param description the description of the command.
     * @param usageMessage how to use the command.
     * @param commandList how the commandList should be sent.
     * @param aliases alternative names for the command.
     * @param subCommands the subCommands list.
     */
    public CoreCommand(String name, String description, String usageMessage, CommandList commandList, List<String> aliases, ArrayList<SubCommand> subCommands){
        super(name, description, usageMessage, aliases);
        this.subCommands = subCommands;
        this.commandList = commandList;
    }

    /**
     * This executes the command.
     *
     * @param sender who executed the command.
     * @param s this does nothing.
     * @param args the arguments for the command.
     * @return true.
     */
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {

        if (args.length > 0){
            for (SubCommand subCommand : subCommands) {
                if (args[0].equalsIgnoreCase(subCommand.getName()) || (subCommand.getAliases() != null && subCommand.getAliases().contains(args[0]))) {
                    if (sender.hasPermission(subCommand.getPermission())) {
                        subCommand.perform(sender, args);
                    }
                }
            }
        } else {
            if (commandList == null){
                sender.sendMessage(ColorUtil.color("&e--------------------"));
                for (SubCommand subCommand : subCommands){
                    sender.sendMessage(ColorUtil.color(subCommand.getSyntax() + " &b- " + subCommand.getDescription()));
                }
                sender.sendMessage(ColorUtil.color("&e--------------------"));
            }else{
                commandList.displayCommandList(sender, subCommands);
            }
        }

        return true;
    }

    /**
     * Tab completer for the
     * core command.
     *
     * @param sender the sender of the command.
     * @param alias alternative name of the command.
     * @param args arguments of the command.
     * @return arrayList of the subCommands.
     */
    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, String[] args) {
        if (args.length == 1){ // /lepvp <subCommand> <args>
            ArrayList<String> subCommandsArguments = new ArrayList<>();

            for (SubCommand subCommand : subCommands) {
                subCommandsArguments.add(subCommand.getName());
            }
            return subCommandsArguments;
        } else if (args.length >= 2){
            for (SubCommand subCommand : subCommands) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    List<String> subCommandArgs = subCommand.getSubCommandArguments((Player) sender, args);

                    //getSubcommandArguments will have returned null if no implementation was provided.
                    return Objects.requireNonNullElse(subCommandArgs, Collections.emptyList());
                }
            }
        }

        return Collections.emptyList();
    }
}