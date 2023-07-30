package com.maanraj514.party.ranks;

import com.maanraj514.party.PartyRank;
import org.bukkit.ChatColor;

public class Moderator implements PartyRank {
    @Override
    public String getName() {
        return "&dMODERATOR";
    }

    @Override
    public String getPrefix() {
        return "&d[" + getName() + "&d]&d: ";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.LIGHT_PURPLE;
    }

    @Override
    public double getStrength() {
        return 2.0;
    }
}
