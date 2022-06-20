package me.doublenico.hypeapi.title;

import org.bukkit.entity.Player;

public abstract class AbstractTitle {

    private Player player;
    private String title;
    private String subtitle;
    private int fadein = 20;
    private int stay = 60;
    private int fadeout = 20;
    private boolean cancelled = false;
    private boolean colored = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getFadein() {
        return fadein;
    }

    public void setFadein(int fadein) {
        this.fadein = fadein;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public int getFadeout() {
        return fadeout;
    }

    public void setFadeout(int fadeout) {
        this.fadeout = fadeout;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
