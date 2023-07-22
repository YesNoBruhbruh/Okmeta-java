package com.maanraj514.menu;

import org.bukkit.entity.Player;

import java.util.Stack;

public class PlayerMenuUtility {

    private Player owner;
    private final Stack<Menu> history = new Stack<>();

    public PlayerMenuUtility(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Menu lastMenu() {
        this.history.pop(); //Makes back() work for some reason
        return this.history.pop();
    }

    public void pushMenu(Menu menu) {
        this.history.push(menu);
    }
}