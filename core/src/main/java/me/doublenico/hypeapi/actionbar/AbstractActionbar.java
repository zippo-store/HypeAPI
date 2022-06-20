package me.doublenico.hypeapi.actionbar;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class AbstractActionbar {

    private Player player;
    private String message;
    private boolean cancelled = false;
    private int duration = 0;
    private ActionBarTypes types;
    private boolean color = false;
    private Plugin plugin;

    public AbstractActionbar(){
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void subtractDuration(int duration){
        this.duration -= duration;
    }

    public ActionBarTypes getTypes() {
        return types;
    }

    public void setTypes(ActionBarTypes types) {
        this.types = types;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public Plugin getPlugin(){
        return this.plugin;
    }

    public void setPlugin(Plugin plugin){
        this.plugin = plugin;
    }
}

