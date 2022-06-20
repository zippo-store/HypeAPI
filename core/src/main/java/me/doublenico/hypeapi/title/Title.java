package me.doublenico.hypeapi.title;

import me.doublenico.hypeapi.VersionUtils;
import me.doublenico.hypeapi.colors.ColorChat;
import me.doublenico.hypeapi.v1_10_R1.ActionBar_v1_10_R1;
import me.doublenico.hypeapi.v1_10_R1.Title_v1_10_R1;
import me.doublenico.hypeapi.v1_11_R1.ActionBar_v1_11_R1;
import me.doublenico.hypeapi.v1_11_R1.Title_v1_11_R1;
import me.doublenico.hypeapi.v1_8_R1.ActionBar_v1_8_R1;
import me.doublenico.hypeapi.v1_8_R1.Title_v1_8_R1;
import me.doublenico.hypeapi.v1_8_R2.ActionBar_v1_8_R2;
import me.doublenico.hypeapi.v1_8_R2.Title_v1_8_R2;
import me.doublenico.hypeapi.v1_8_R3.ActionBar_v1_8_R3;
import me.doublenico.hypeapi.v1_8_R3.Title_v1_8_R3;
import me.doublenico.hypeapi.v1_9_R1.ActionBar_v1_9_R1;
import me.doublenico.hypeapi.v1_9_R1.Title_v1_9_R1;
import me.doublenico.hypeapi.v1_9_R2.ActionBar_v1_9_R2;
import me.doublenico.hypeapi.v1_9_R2.Title_v1_9_R2;
import me.doublenico.hypeapi.wrapper.TitleWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Title extends AbstractTitle implements ITitle {

    @Override
    public void send() {
        if (getPlayer() == null)
            return;
        if (isCancelled())
            return;
        if(VersionUtils.isLegacyVersion()){
            if(isColored())
                sendTitle(getPlayer(), ColorChat.color(getTitle()), ColorChat.color(getSubtitle()), getFadein(), getStay(), getFadeout());
            else
                sendTitle(getPlayer(), getTitle(), getSubtitle(), getFadein(), getStay(), getFadeout());
        } else {
            if(isColored())
                getPlayer().sendTitle(ColorChat.color(getTitle()), ColorChat.color(getSubtitle()), getFadein(), getStay(), getFadeout());
            else
                getPlayer().sendTitle(getTitle(), getSubtitle(), getFadein(), getStay(), getFadeout());
        }
    }

    @Override
    public void sendAll() {
        if (getPlayer() == null)
            return;
        if (isCancelled())
            return;
        if(VersionUtils.isLegacyVersion()){
            if(isColored())
                sendTitleBroadcast(ColorChat.color(getTitle()), ColorChat.color(getSubtitle()), getFadein(), getStay(), getFadeout());
            else
                sendTitleBroadcast(getTitle(), getSubtitle(), getFadein(), getStay(), getFadeout());
        } else {
            if(isColored())
                for(Player player : Bukkit.getOnlinePlayers())
                    player.sendTitle(ColorChat.color(getTitle()), ColorChat.color(getSubtitle()), getFadein(), getStay(), getFadeout());
            else
                for(Player player : Bukkit.getOnlinePlayers())
                    player.sendTitle(getTitle(), getSubtitle(), getFadein(), getStay(), getFadeout());
        }
    }

    @Override
    public void color() {
        setColored(true);
    }

    public static void sendTitle(Player player, String title, String subtitle, Integer fadeIn, Integer stay, Integer fadeOut){
        if(VersionUtils.lowerThan112()) {
            getWrapper();
            getWrapper().sendTitle(ColorChat.color(title), ColorChat.color(subtitle), fadeIn, stay, fadeOut);
            getWrapper().sendToPlayer(player);
            return;
        }
        player.sendTitle(ColorChat.color(title), ColorChat.color(subtitle), fadeIn, stay, fadeOut);
    }

    public static void sendTitle(Player player, String title){
        if(VersionUtils.lowerThan112()) {
            getWrapper().sendTitle(ColorChat.color(title));
            getWrapper().sendToPlayer(player);
            return;
        }
        player.sendTitle(ColorChat.color(title), "", 20, 60, 20);
    }

    public static void sendTitle(Player player, String title, String subtitle){
        if(VersionUtils.lowerThan112()) {
            getWrapper().sendTitle(ColorChat.color(title), ColorChat.color(subtitle));
            getWrapper().sendToPlayer(player);
            return;
        }
        player.sendTitle(ColorChat.color(title), ColorChat.color(subtitle), 20, 60, 20);
    }

    public static void sendSubtitle(Player player, String subtitle, Integer fadeIn, Integer stay, Integer fadeOut) {
        if(VersionUtils.lowerThan112()) {
            getWrapper().sendSubtitle(ColorChat.color(subtitle), fadeIn, stay, fadeOut);
            getWrapper().sendToPlayer(player);
            return;
        }
        player.sendTitle("", ColorChat.color(subtitle), 20, 60, 20);
    }

    public static void sendTitleBroadcast(String title, String subtitle, Integer fadeIn, Integer stay, Integer fadeOut){
        if(VersionUtils.lowerThan112()) {
            getWrapper().sendTitle(ColorChat.color(title), ColorChat.color(subtitle), fadeIn, stay, fadeOut);
            getWrapper().sendToAll();
            return;
        }
        for(Player player : Bukkit.getOnlinePlayers()) player.sendTitle(ColorChat.color(title), ColorChat.color(subtitle), fadeIn, stay, fadeOut);
    }

    public static void sendTitleBroadcast(String title){
        if(VersionUtils.lowerThan112()) {
            getWrapper().sendTitle(ColorChat.color(title));
            getWrapper().sendToAll();
            return;
        }
        for(Player player : Bukkit.getOnlinePlayers()) player.sendTitle(ColorChat.color(title), "", 20, 60, 20);
    }

    public static void sendTitleBroadcast(String title, String subtitle){
        if(VersionUtils.lowerThan112()) {
            getWrapper().sendTitle(ColorChat.color(title), ColorChat.color(subtitle));
            getWrapper().sendToAll();
            return;
        }
        for(Player player : Bukkit.getOnlinePlayers()) player.sendTitle(ColorChat.color(title), ColorChat.color(subtitle), 20, 60, 20);
    }


    public static void sendSubtitleBroadcast(String subtitle, Integer fadeIn, Integer stay, Integer fadeOut){
        if(VersionUtils.lowerThan112()) {
            getWrapper().sendSubtitle(ColorChat.color(subtitle), fadeIn, stay, fadeOut);
            getWrapper().sendToAll();
            return;
        }
        for(Player player : Bukkit.getOnlinePlayers()) player.sendTitle("", ColorChat.color(subtitle), 20, 60, 20);
    }

    public static void sendSubtitleBroadcast(String subtitle){
        if(VersionUtils.lowerThan112()) {
            getWrapper().sendSubtitle(ColorChat.color(subtitle));
            getWrapper().sendToAll();
            return;
        }
        for(Player player : Bukkit.getOnlinePlayers()) player.sendTitle("", ColorChat.color(subtitle), 20, 60, 20);
    }

    public static TitleWrapper getWrapper(){
        switch (VersionUtils.getNMSVersion()) {
            case "v1_8_R1": return new Title_v1_8_R1();
            case "v1_8_R2": return new Title_v1_8_R2();
            case "v1_8_R3": return new Title_v1_8_R3();
            case "v1_9_R1": return new Title_v1_9_R1();
            case "v1_9_R2": return new Title_v1_9_R2();
            case "v1_10_R1": return new Title_v1_10_R1();
            case "v1_11_R1": return new Title_v1_11_R1();
        }
        return null;
    }

}
