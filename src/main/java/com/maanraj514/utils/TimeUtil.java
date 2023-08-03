package com.maanraj514.utils;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

/**
 * This class converts longs, ints into readable human time.
 * Or it can convert it the other way around.
 */
public class TimeUtil {

    /**
     * Format a time into a human-readable format.
     * @param time Time to format
     * @param small Whether to use small format
     * @return Formatted time
     */
    @NotNull
    public static String format(long time, boolean small) {
        Preconditions.checkArgument(time >= 0, "Time cannot be smaller than 0.");

        int toSec = (int) (time / 1000) % 60;
        int toMin = (int) ((time / (1000 * 60)) % 60);
        int toHour = (int) ((time / (1000 * 60 * 60)) % 24);
        int toDays = (int) (time / (1000 * 60 * 60 * 24));

        boolean dayNotZero = toDays != 0;
        boolean hourNotZero = toHour != 0;
        boolean minuteNotZero = toMin != 0;
        boolean secondNotZero = toSec != 0;

        String day = (small ? "d" : (toDays == 1 ? "day" : "days"));
        String hour = (small ? "h" : (toHour == 1 ? "hour" : "hours"));
        String minute = (small ? "m" : (toMin == 1 ? "minute" : "minutes"));
        String second = (small ? "s" : (toSec == 1 ? "second" : "seconds"));

        String dayFormat = (dayNotZero ? toDays + (small ? "" : " ") + day + " " : "");
        String hourFormat = (hourNotZero ? toHour + (small ? "" : " ") + hour + " " : "");
        String minuteFormat = (minuteNotZero ? toMin + (small ? "" : " ") + minute + " " : "");
        String secondFormat = (secondNotZero ? toSec + (small ? "" : " ") + second : "");

        return dayFormat + hourFormat + minuteFormat + secondFormat;
    }

    /**
     * Format time from a string.
     * @param input Input string
     * @return Formatted time
     */
    public static long fromString(@NotNull String input) {
        StringBuilder builder = new StringBuilder();
        int seconds = 0;
        int minutes = 0;
        int hours = 0;
        int days = 0;
        int weeks = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                builder.append(c);
            } else {
                switch (c) {
                    case 's':
                        if (builder.length() != 0) {
                            seconds += Integer.parseInt(builder.toString());
                            builder = new StringBuilder();
                        }
                        break;
                    case 'm':
                        if (builder.length() != 0) {
                            minutes += Integer.parseInt(builder.toString());
                            builder = new StringBuilder();
                        }
                        break;
                    case 'h':
                        if (builder.length() != 0) {
                            hours += Integer.parseInt(builder.toString());
                            builder = new StringBuilder();
                        }
                        break;
                    case 'd':
                        if (builder.length() != 0) {
                            days += Integer.parseInt(builder.toString());
                            builder = new StringBuilder();
                        }
                        break;
                    case 'w':
                        if (builder.length() != 0) {
                            weeks += Integer.parseInt(builder.toString());
                            builder = new StringBuilder();
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Not a valid duration format.");
                }
            }
        }
        return 1000L * (seconds + minutes * 60L + hours * 60 * 60L + days * 24 * 60 * 60L + weeks * 7 * 24 * 60 * 60L);
    }
}