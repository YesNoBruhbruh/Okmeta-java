package com.maanraj514.utils;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used for translating colors.
 */
public class ColorUtil {

    /*
     * This pattern is used to match hex colors.
     */
    private static final Pattern PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");

    /**
     * Colors the string text.
     *
     * @param text The text to translate.
     * @return The translated text.
     */
    public static String color(@NotNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Translates a string with basic color or hex color.
     *
     * @param text The text to translate.
     * @return The translated string.
     */
    public static String translate(@NotNull String text) {
        Matcher matcher = PATTERN.matcher(text);
        while (matcher.find()) {
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, String.valueOf(ChatColor.of(color)));
            matcher = PATTERN.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}