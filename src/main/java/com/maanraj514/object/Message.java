package com.maanraj514.object;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a message that can be sent to a commandSender.
 */
public class Message {

    /*
     * This pattern is used to match hex colors.
     */
    private static final Pattern PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");

    /*
     * This is the message without any modifications.
     */
    private final String def;

    /*
     * This is the current message with all modifications.
     */
    private String current;

    /**
     * Creates new instance of the class.
     *
     * @param text the text of the message.
     */
    protected Message(String text){
        this.def = text;
        this.current = text;
    }

    /**
     * Replaces the placeholder with the replacement.
     *
     * @param placeholder the text to be replaced.
     * @param replacement the text replacing the placeholder.
     * @return this message with the modifications.
     */
    public Message replace(String placeholder, String replacement){
        this.current = current.replace(placeholder, replacement);
        return this;
    }

    /**
     * Sends a message to the commandSender.
     *
     * @param sender the entity the message gets sent to.
     */
    public void send(CommandSender sender){
        String translate = translate();
        String[] lines = translate().split("\n");

        for (String line : lines){
            sender.sendMessage(line);
        }
        this.current = def;
    }

    /**
     * Broadcasts the message to the entire server.
     */
    public void broadcast(){
        String translate = translate();
        String[] lines = translate.split("\n");

        for (String line : lines){
            Bukkit.broadcastMessage(line);
        }
        this.current = def;
    }

    /**
     * Creates a new message with the string array.
     *
     * @param text the string array converted to a new message.
     * @return new message created from the text
     */
    public static Message of(String... text){
        return new Message(String.join("\n", text));
    }

    /**
     * Creates a new message with the string text.
     *
     * @param text this text converted to a new message.
     * @return new message created from the text
     */
    public static Message of(String text){
        return new Message(text);
    }

    /**
     * Creates a new message with the list of strings.
     *
     * @param text the list of strings converted to a message.
     * @return new message created from the text
     */
    public static Message of(List<String> text){
        return new Message(String.join("\n", text));
    }

    /**
     * This will translate the message to color or hex color.
     *
     * @return the translated message.
     */
    private String translate() {
        Matcher matcher = PATTERN.matcher(current);
        while (matcher.find()) {
            String color = current.substring(matcher.start(), matcher.end());
            current = current.replace(color, String.valueOf(ChatColor.of(color)));
            matcher = PATTERN.matcher(current);
        }
        return ChatColor.translateAlternateColorCodes('&', current);
    }
}