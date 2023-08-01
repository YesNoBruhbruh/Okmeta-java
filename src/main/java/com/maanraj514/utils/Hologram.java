package com.maanraj514.utils;

import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;

public class Hologram {

    private final TextDisplay textDisplay;
    private final String text;

    public Hologram(Location location, String text){
        this.text = text;

        this.textDisplay = location.getWorld().spawn(location, TextDisplay.class);
        textDisplay.setText(text);
        textDisplay.setBillboard(Display.Billboard.CENTER);
    }

    public void update(){
        this.textDisplay.setText(text);
    }

    public void destroy(){
        this.textDisplay.remove();
    }
}