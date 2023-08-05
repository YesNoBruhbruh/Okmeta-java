package com.maanraj514.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for ease of use of locations.
 */
public class LocationUtil {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.#");

    /**
     * Converts a Location to a String.
     *
     * @param location the location to be converted.
     * @param includeExtra if true, includes yaw and pitch if false, not.
     * @return the location in string.
     */
    public static String locationToString(@NotNull Location location, boolean includeExtra){

        String world = location.getWorld().getName();
        double x = Double.parseDouble(decimalFormat.format(location.getX()));
        double y = Double.parseDouble(decimalFormat.format(location.getY()));
        double z = Double.parseDouble(decimalFormat.format(location.getZ()));
        float yaw = Float.parseFloat(decimalFormat.format(location.getYaw()));
        float pitch = Float.parseFloat(decimalFormat.format(location.getPitch()));

        return (includeExtra ? world + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch : world + ":" + x + ":" + y + ":" + z);
    }

    /**
     * Converts a String to a Location.
     *
     * @param location the location to be created
     * @return the string in location.
     */
    @Nullable
    public static Location stringToLocation(@NotNull String location){
        if (location.trim().equals("")){
            return null;
        }

        final String[] parts = location.split(":");
        // this means location without yaw or pitch.

        final World world;
        final double x;
        final double y;
        final double z;

        if (parts.length == 4) {
            world = Bukkit.getWorld(parts[0]);
            x = Double.parseDouble(parts[1]);
            y = Double.parseDouble(parts[2]);
            z = Double.parseDouble(parts[3]);

            return new Location(world, x, y, z);
        } else if (parts.length == 6){
            // this means location with yaw and pitch.

            world = Bukkit.getWorld(parts[0]);
            x = Double.parseDouble(parts[1]);
            y = Double.parseDouble(parts[2]);
            z = Double.parseDouble(parts[3]);
            float yaw = Float.parseFloat(parts[4]);
            float pitch = Float.parseFloat(parts[5]);

            return new Location(world, x, y, z, yaw, pitch);
        }
        return null;
    }

    /**
     * Gets all the locations
     * inside the invisible
     * cuboid.
     *
     * @param location1 the first location.
     * @param location2 the second location.
     * @return all the locations in the invisible cuboid.
     */
    public static List<Location> locationsFromTwoPoints(Location location1, Location location2) {
        List<Location> locations = new ArrayList<>();

        int topBlockX = (Math.max(location1.getBlockX(), location2.getBlockX()));
        int bottomBlockX = (Math.min(location1.getBlockX(), location2.getBlockX()));

        int topBlockY = (Math.max(location1.getBlockY(), location2.getBlockY()));
        int bottomBlockY = (Math.min(location1.getBlockY(), location2.getBlockY()));

        int topBlockZ = (Math.max(location1.getBlockZ(), location2.getBlockZ()));
        int bottomBlockZ = (Math.min(location1.getBlockZ(), location2.getBlockZ()));

        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++) {
                    locations.add(location1.getWorld().getBlockAt(x,y,z).getLocation());
                }
            }
        }

        return locations;
    }

    public static boolean coordinatesMatch(Location location1, Location location2){
        if (location1 == null || location2 == null) return false;

        return location1.getX() == location2.getX() &&
                location1.getY() == location2.getY() &&
                location1.getZ() == location2.getZ();
    }
}