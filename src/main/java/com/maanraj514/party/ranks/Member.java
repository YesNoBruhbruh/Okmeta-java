package com.maanraj514.party.ranks;

import com.maanraj514.party.PartyRank;
import org.bukkit.ChatColor;

public class Member implements PartyRank {
    @Override
    public String getName() {
        return "&7MEMBER";
    }

    @Override
    public String getPrefix() {
        return "&7[" + getName() + "&7]&7: ";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.GRAY;
    }

    @Override
    public double getStrength() {
        return 1.0;
    }
}
