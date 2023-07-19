package com.maanraj514.utils;

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
     * This method will check if the provided text is an int
     * @param text Text to check
     * @return If the text is an int
     */
    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an int from the provided text.
     * @param text Text to get the int from
     * @return int from the text
     * @throws NullPointerException If the text is not an int
     */

    public static Integer getInteger(String text) {
        if (!isInteger(text)) return null;

        return Integer.parseInt(text);
    }

    /**
     * This method will check if the provided text is a long
     * @param text Text to check
     * @return If the text is a long
     */
    public static boolean isLong(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get a long from the provided text.
     * @param text Text to get the long from
     * @return long from the text
     * @throws NullPointerException If the text is not a long
     */
    public static Long getLong(String text) {
        if (!isLong(text)) return null;

        return Long.parseLong(text);
    }

    /**
     * This method will check if the provided text is a double.
     * @param text Text to check
     * @return If the text is a double
     */
    public static boolean isDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get a double from the provided text.
     * @param text Text to get the double from
     * @return double from the text
     * @throws NullPointerException If the text is not a double
     */
    public static Double getDouble(String text) {
        if (!isDouble(text)) return null;

        return Double.parseDouble(text);
    }

    /**
     * This method will check if the provided text is a float.
     * @param text Text to check
     * @return If the text is a float
     */
    public static boolean isFloat(String text) {
        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get a float from the provided text.
     * @param text Text to get the float from
     * @return float from the text
     * @throws NullPointerException If the text is not a float
     */
    public static Float getFloat(String text) {
        if (!isFloat(text)) return null;

        return Float.parseFloat(text);
    }

    /**
     * This method will check if the provided text is a short.
     * @param text Text to check
     * @return If the text is a short
     */
    public static boolean isShort(String text) {
        try {
            Short.parseShort(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get a short from the provided text.
     * @param text Text to get the short from
     * @return short from the text
     * @throws NullPointerException If the text is not a short
     */
    public static Short getShort(String text) {
        if (!isShort(text)) return null;

        return Short.parseShort(text);
    }
}