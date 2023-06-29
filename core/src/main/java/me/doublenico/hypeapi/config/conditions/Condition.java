package me.doublenico.hypeapi.config.conditions;

import org.bukkit.entity.Player;

public abstract class Condition {

    public Condition(){
        ConditionManager.addCondition(this);
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getIdentifier();

    public abstract boolean checkCondition(Player player, String args);

}
