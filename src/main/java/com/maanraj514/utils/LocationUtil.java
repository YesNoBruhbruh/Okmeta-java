package com.maanraj514.utils;

import com.maanraj514.model.Pos;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for ease of use of locations.
 */
public class LocationUtil {

    /**
     * Converts a Location to a String.
     *
     * @param location the location to be converted.
     * @param includeExtra if true, includes yaw and pitch if false, not.
     * @return the location in string.
     */
    public static String locationToString(@NotNull Location location, boolean includeExtra){

        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        return (includeExtra ? world + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch : world + ":" + x + ":" + y + ":" + z);
    }

    public static Pos locationToPos(Location location){
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        return new Pos(x, y, z, yaw, pitch);
    }

    public static Location posToLocation(Pos pos, World world){
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        float yaw = pos.getYaw();
        float pitch = pos.getPitch();
        Location location = new Location(world, x, y, z);
        location.setYaw(yaw);
        location.setPitch(pitch);
        return location;
    }

    public static Pos stringToPos(String s) {
        if (s == null || s.trim().equals("")) {
            return null;
        }

        final String[] parts = s.split(":");
        if (parts.length == 3) {
            final double x = Double.parseDouble(parts[0]);
            final double y = Double.parseDouble(parts[1]);
            final double z = Double.parseDouble(parts[2]);
            return new Pos(x, y, z);
        }
        return null;
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

    public static List<Pos> posFromTwoPoints(Pos pos1, Pos pos2) {
        List<Pos> posList = new ArrayList<>();

        int topBlockX = (Math.max(doubleToInt(pos1.getX()), doubleToInt(pos2.getX())));
        int bottomBlockX = (Math.min(doubleToInt(pos1.getX()), doubleToInt(pos2.getX())));

        int topBlockY = (Math.max(doubleToInt(pos1.getY()), doubleToInt(pos2.getY())));
        int bottomBlockY = (Math.min(doubleToInt(pos1.getY()), doubleToInt(pos2.getY())));

        int topBlockZ = (Math.max(doubleToInt(pos1.getZ()), doubleToInt(pos2.getZ())));
        int bottomBlockZ = (Math.min(doubleToInt(pos1.getZ()), doubleToInt(pos2.getZ())));

        for (int x = bottomBlockX; x <= topBlockX; x++)
        {
            for (int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                for (int y = bottomBlockY; y <= topBlockY; y++)
                {

                    // location of the blocks
                    posList.add(new Pos(x, y, z));
                }
            }
        }

        return posList;
    }

    public static boolean coordinatesMatch(Location location1, Location location2){
        if (location1 == null || location2 == null) return false;

        return location1.getX() == location2.getX() &&
                location1.getY() == location2.getY() &&
                location1.getZ() == location2.getZ();
    }

    public static boolean coordinatesMatch(Pos pos1, Pos pos2){
        if (pos1 == null || pos2 == null) return false;

        return pos1.getX() == pos2.getX() &&
                pos1.getY() == pos2.getY() &&
                pos1.getZ() == pos2.getZ();
    }

    public static int doubleToInt(double number){
        return NumberConversions.floor(number);
    }
}