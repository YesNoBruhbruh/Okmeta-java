package com.maanraj514.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This interface adds a feature in case you want a custom message to be sent
 * when showing the subCommands.
 */
public interface CommandList {

    /**
     * Can be overridden.
     *
     * @param sender who to send the message.
     * @param subCommandList the commandList.
     */
    void displayCommandList(@NotNull CommandSender sender, @NotNull List<SubCommand> subCommandList);
}
