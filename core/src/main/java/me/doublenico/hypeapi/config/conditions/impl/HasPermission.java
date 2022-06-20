package me.doublenico.hypeapi.config.conditions.impl;

import me.doublenico.hypeapi.config.conditions.Condition;
import org.bukkit.entity.Player;

public class HasPermission extends Condition {

    @Override
    public String getName() {
        return "hasPermission";
    }

    @Override
    public String getDescription() {
        return "Check if the player has the specified permission";
    }

    @Override
    public String getIndentifier() {
        return "permission";
    }

    @Override
    public boolean checkCondition(Player player, String args) {
        return player.hasPermission(args);
    }
}

