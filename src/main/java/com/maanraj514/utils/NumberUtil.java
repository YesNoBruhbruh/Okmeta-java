package com.maanraj514.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;

/**
 * Represents a number utility class used to handle numbers, parse them, etc.
 */
public class NumberUtil {

    /*
     * Format a number to a fancy format.
     */
    private static final DecimalFormat FANCY_FORMAT = new DecimalFormat("###,###,###,###,###.##");

    /**
     * Parse a number to a fancy format.
     * @param number Number to parse
     * @return Parsed number
     */
    public static String parse(int number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     * @param number Number to parse
     * @return Parsed number
     */
    public static String parse(long number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     * @param number Number to parse
     * @return Parsed number
     */
    public static String parse(double number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     * @param number Number to parse
     * @return Parsed number
     */
    public static String parse(float number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     * @param number Number to parse
     * @return Parsed number
     */
    public static String parse(short number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     * @param number Number to parse
     * @return Parsed number
     */
    public static String parse(byte number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * This method will check if the provided text is a {@link Integer}.
     * @param text Text to check
     * @return If the text is a {@link Integer}
     */
    public static boolean isInteger(@NotNull String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Integer} from the provided text.
     * @param text Text to get the {@link Integer} from
     * @return {@link Integer} from the text
     * @throws NullPointerException If the text is not an {@link Integer}
     */
    @Nullable
    public static Integer getInteger(@NotNull String text) {
        if (!isInteger(text)) return null;

        return Integer.parseInt(text);
    }

    /**
     * This method will check if the provided text is a {@link Long}.
     * @param text Text to check
     * @return If the text is a {@link Long}
     */
    public static boolean isLong(@NotNull String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Long} from the provided text.
     * @param text Text to get the {@link Long} from
     * @return {@link Long} from the text
     * @throws NullPointerException If the text is not an {@link Long}
     */
    @Nullable
    public static Long getLong(@NotNull String text) {
        if (!isLong(text)) return null;

        return Long.parseLong(text);
    }

    /**
     * This method will check if the provided text is a {@link Double}.
     * @param text Text to check
     * @return If the text is a {@link Double}
     */
    public static boolean isDouble(@NotNull String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Double} from the provided text.
     * @param text Text to get the {@link Double} from
     * @return {@link Double} from the text
     * @throws NullPointerException If the text is not an {@link Double}
     */
    @Nullable
    public static Double getDouble(@NotNull String text) {
        if (!isDouble(text)) return null;

        return Double.parseDouble(text);
    }

    /**
     * This method will check if the provided text is a {@link Float}.
     * @param text Text to check
     * @return If the text is a {@link Float}
     */
    public static boolean isFloat(@NotNull String text) {
        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Float} from the provided text.
     * @param text Text to get the {@link Float} from
     * @return {@link Float} from the text
     * @throws NullPointerException If the text is not an {@link Float}
     */
    @Nullable
    public static Float getFloat(@NotNull String text) {
        if (!isFloat(text)) return null;

        return Float.parseFloat(text);
    }

    /**
     * This method will check if the provided text is a {@link Short}.
     * @param text Text to check
     * @return If the text is a {@link Short}
     */
    public static boolean isShort(@NotNull String text) {
        try {
            Short.parseShort(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Short} from the provided text.
     * @param text Text to get the {@link Short} from
     * @return {@link Short} from the text
     * @throws NullPointerException If the text is not an {@link Short}
     */
    @Nullable
    public static Short getShort(@NotNull String text) {
        if (!isShort(text)) return null;

        return Short.parseShort(text);
    }
}