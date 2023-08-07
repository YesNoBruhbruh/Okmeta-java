package com.maanraj514.model;

import org.bukkit.Location;

public class Pos {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public Pos(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Pos(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Pos(Location location){
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public String toString() {
        return x + ":" + y + ":" + z;
    }
}