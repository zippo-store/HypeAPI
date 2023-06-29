package me.doublenico.hypeapi.actionbar;

import dev.dynamicstudios.json.data.util.CColor;
import me.doublenico.hypeapi.VersionUtils;
import me.doublenico.hypeapi.colors.ColorChat;
import me.doublenico.hypeapi.v1_10_R1.ActionBar_v1_10_R1;
import me.doublenico.hypeapi.v1_11_R1.ActionBar_v1_11_R1;
import me.doublenico.hypeapi.v1_8_R1.ActionBar_v1_8_R1;
import me.doublenico.hypeapi.v1_8_R2.ActionBar_v1_8_R2;
import me.doublenico.hypeapi.v1_8_R3.ActionBar_v1_8_R3;
import me.doublenico.hypeapi.v1_9_R1.ActionBar_v1_9_R1;
import me.doublenico.hypeapi.v1_9_R2.ActionBar_v1_9_R2;
import me.doublenico.hypeapi.wrapper.ActionBarWrapper;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ActionBar extends AbstractActionbar implements IActionBar{

    private static boolean cancelBroadcast = false;
    private static final HashMap<Player, Boolean> cancel = new HashMap<>();

    @Override
    public void send() {
        if(this.getPlayer() == null)
            return;
        if(isCancelled())
            return;
        if(isColor())
            sendActionBar(getPlayer(), ColorChat.color(getMessage()));
        else
            sendActionBar(getPlayer(), getMessage());
    }

    @Override
    public void sendToAll() {
        if(this.getPlayer() == null)
            return;
        if(isCancelled())
            return;
        if(isColor())
            sendBroadcastActionBar(ColorChat.color(getMessage()));
        else
            sendBroadcastActionBar(getMessage());
    }

    @Override
    public void timer() {
        if(isCancelled()) return;
        if(getTypes() == ActionBarTypes.PLAYER)
            send();
        if(getTypes() == ActionBarTypes.ALL)
            sendToAll();
        if(getTypes() == null)
            return;

        if(getDuration() >= 0){
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(isCancelled()) cancel();
                    setMessage("");
                    if(getTypes() == ActionBarTypes.PLAYER)
                        send();
                    if(getTypes() == ActionBarTypes.ALL)
                        sendToAll();
                }
            }.runTaskLaterAsynchronously(getPlugin(), getDuration() + 1);
        }

        while (getDuration() > 40) {
            subtractDuration(40);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(isCancelled()) cancel();
                    if(getTypes() == ActionBarTypes.PLAYER)
                        send();
                    if(getTypes() == ActionBarTypes.ALL)
                        sendToAll();
                }
            }.runTaskLaterAsynchronously(getPlugin(), getDuration());
        }


    }

    public void color(){
        setColor(true);
    }

    public static void sendActionBar(Player player, String message) {
        if(player == null) return;
        if(VersionUtils.lowerThan112()) {
            getWrapper().createActionBar(CColor.translateAlternateColorCodes('&', message));
            getWrapper().sendToPlayer(player);
            return;
        }
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, ColorChat.colorJSON(message));
    }
    public static void sendBroadcastActionBar(String message) {
        if(VersionUtils.lowerThan112()) {
            if(Bukkit.getOnlinePlayers().size() < 1) return;
            getWrapper().createActionBar(CColor.translateAlternateColorCodes('&', message));
            getWrapper().sendToAll();
            cancelBroadcast = false;
            return;
        }
        for(Player player : Bukkit.getOnlinePlayers()) {
            if (Bukkit.getOnlinePlayers().size() > 1) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorChat.color(message)));
                cancelBroadcast = false;
                return;
            }
        }
    }

    public static void sendActionBar(Plugin plugin, Player player, String message, int duration) {
        sendActionBar(player, message);

        if (duration >= 0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(player != null) sendActionBar(player, "");
                    else cancel();
                }
            }.runTaskLaterAsynchronously(plugin, duration + 1);
        }

        while (duration > 40) {
            duration -= 40;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(player != null) sendActionBar(player, message);
                    else cancel();
                }
            }.runTaskLaterAsynchronously(plugin, duration);
        }
    }

    public static void sendBroadcastActionBar(Plugin plugin, String message, int duration) {
        sendBroadcastActionBar(message);

        if (duration >= 0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(cancelBroadcast) cancel();
                    if(Bukkit.getOnlinePlayers().size() > 1) sendBroadcastActionBar(message);
                    else cancel();
                }
            }.runTaskLaterAsynchronously(plugin, duration + 1);
        }

        while (duration > 40) {
            duration -= 40;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(cancelBroadcast) cancel();
                    if(Bukkit.getOnlinePlayers().size() > 1) sendBroadcastActionBar(message);
                    else cancel();
                }
            }.runTaskLaterAsynchronously(plugin, duration);
        }
    }

    public static void clearActionBarBroadcast(){
        cancelBroadcast = true;
    }

    public static ActionBarWrapper getWrapper(){
         switch (VersionUtils.getNMSVersion()) {
            case "v1_8_R1": return new ActionBar_v1_8_R1();
            case "v1_8_R2": return new ActionBar_v1_8_R2();
            case "v1_8_R3": return new ActionBar_v1_8_R3();
            case "v1_9_R1": return new ActionBar_v1_9_R1();
            case "v1_9_R2": return new ActionBar_v1_9_R2();
            case "v1_10_R1": return new ActionBar_v1_10_R1();
            case "v1_11_R1": return new ActionBar_v1_11_R1();
        }
        return null;
    }


}
