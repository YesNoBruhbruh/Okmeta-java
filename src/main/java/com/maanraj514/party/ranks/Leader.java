package com.maanraj514.party.ranks;


import com.maanraj514.party.PartyRank;
import org.bukkit.ChatColor;

public class Leader implements PartyRank {
    @Override
    public String getName() {
        return "&eLEADER";
    }

    @Override
    public String getPrefix() {
        return "&e[" + getName() + "&e]&e: ";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.GOLD;
    }

    @Override
    public double getStrength() {
        return 3.0;
    }
}
