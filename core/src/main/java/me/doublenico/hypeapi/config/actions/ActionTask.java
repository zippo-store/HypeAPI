package me.doublenico.hypeapi.config.actions;

import com.cryptomorin.xseries.XSound;
import io.dynamicstudios.json.DynamicJText;
import me.doublenico.hypeapi.actionbar.ActionBar;
import me.doublenico.hypeapi.colors.ColorChat;
import me.doublenico.hypeapi.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionTask extends BukkitRunnable {

    private final Player player;
    private final ConfigActions action;
    private final String message;

    private final Plugin plugin;

    public ActionTask(Plugin plugin, Player player, ConfigActions action, String message) {
        this.plugin = plugin;
        this.player = player;
        this.action = action;
        this.message = message;
    }

    @Override
    public void run() {
        switch (action) {
            case MESSAGE:
                player.sendMessage(ColorChat.convert(player, message));
                break;
            case CONSOLE:
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
                break;
            case CHAT:
                player.chat(ColorChat.color(message));
                break;
            case BROADCAST:
                Bukkit.broadcastMessage(ColorChat.convert(player, message));
            case PLAYER:
                player.chat("/" + ColorChat.convert(player, message));
                break;
            case TITLE:
                sendTitle(player, message, false);
                break;
            case TITLE_BROADCAST:
                sendTitle(player, message, true);
                break;
            case SUBTITLE:
                sendSubtitle(player, message, false);
                break;
            case SUBTITLE_BROADCAST:
                sendSubtitle(player, message, true);
                break;
            case ACTIONBAR:
                sendActionbar(plugin, player, message, false);
                break;
            case ACTIONBAR_BROADCAST:
                sendActionbar(plugin, player, message, true);
                break;
            case SOUND:
                sendSound(player, message, false);
                break;
            case SOUND_BROADCAST:
                sendSound(player, message, true);
                break;
            case JSON:
                DynamicJText.parseJson(message).send(player);
                break;
            case JSON_BROADCAST:
                Bukkit.getOnlinePlayers().forEach(p -> DynamicJText.parseJson(message).send(p));
                break;
        }
    }


    private void sendTitle(Player player, String string, boolean broadcast) {
        String[] split = string.split(",");
        if(split.length > 5) { Bukkit.getLogger().info("[HypeAPI] Too many arguments for title"); return; }
        int fadeIn;
        int stay;
        int fadeOut;
        String subtitle;
        if(split[0].isEmpty()){ Bukkit.getLogger().info("[HypeAPI] No title given"); return; }
        String title = split[0];

        if (split.length <= 1 || split[1].isEmpty()) subtitle = "";
        else subtitle = split[1];

        if(split.length <= 2 || !isInt(split[2])) fadeIn = 20;
        else fadeIn = Integer.parseInt(split[2]);

        if(split.length <= 3 || !isInt(split[3])) stay = 60;
        else stay = Integer.parseInt(split[3]);

        if(split.length <= 4 || !isInt(split[4])) fadeOut = 20;
        else fadeOut = Integer.parseInt(split[4]);

        if (!broadcast) Title.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
        else Title.sendTitleBroadcast(title, subtitle, fadeIn, stay, fadeOut);
    }

    private void sendSubtitle(Player player, String string, boolean broadcast) {
        String[] split = string.split(",");
        if(split.length > 4) { Bukkit.getLogger().info("[HypeAPI] Too many arguments for subtitle"); return; }
        int fadeIn;
        int stay;
        int fadeOut;
        if (split[0].isEmpty()) { Bukkit.getLogger().info("[HypeAPI] No subtitle given"); return; }
        String subtitle = split[0];
        if(split.length <= 1 || !isInt(split[1])) fadeIn = 20;
        else fadeIn = Integer.parseInt(split[1]);

        if(split.length <= 2 || !isInt(split[2])) stay = 60;
        else stay = Integer.parseInt(split[2]);

        if(split.length <= 3|| !isInt(split[3])) fadeOut = 20;
        else fadeOut = Integer.parseInt(split[3]);

        if (!broadcast) Title.sendSubtitle(player, subtitle, fadeIn, stay, fadeOut);
        else Title.sendSubtitleBroadcast(subtitle, fadeIn, stay, fadeOut);
    }

    private void sendActionbar(Plugin plugin, Player player, String string, boolean broadcast) {
        String[] split = string.split(",");
        if(split.length > 2) { Bukkit.getLogger().info("[HypeAPI] Too many arguments for actionbar"); return; }
        if(split[0].isEmpty()) { Bukkit.getLogger().info("[HypeAPI] No message for actionbar"); return; }
        if(split.length <= 1 || !isInt(split[1])) {
            if (!broadcast) ActionBar.sendActionBar(player, split[0]);
            else ActionBar.sendBroadcastActionBar(split[0]);
        }
        else {
            if (!broadcast) ActionBar.sendActionBar(plugin, player, split[0], Integer.parseInt(split[1]));
            else ActionBar.sendBroadcastActionBar(plugin, split[0], Integer.parseInt(split[1]));
        }
    }

    private void sendSound(Player player, String string, boolean broadcast) {
        String[] split = string.split(",");
        if(split.length > 3) { Bukkit.getLogger().info("[HypeAPI] To many arguments for sound"); return;}
        int volume;
        int pitch;
        String sound = split[0];
        if(sound.isEmpty()) { Bukkit.getLogger().info("[HypeAPI] No sound given"); return; }
        if(split.length < 2 || !isInt(split[1])) volume = 20;
        else volume = Integer.parseInt(split[1]);

        if(split.length < 3 || !isInt(split[2])) pitch = 1;
        else pitch = Integer.parseInt(split[2]);
        if (!XSound.matchXSound(sound).isPresent()) { Bukkit.getLogger().info("[HypeAPI] The sound " + sound + " is not a valid sound"); return; }
        if (!broadcast) player.playSound(player.getLocation(), XSound.matchXSound(sound).get().parseSound(), volume, pitch);
        else Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), XSound.matchXSound(sound).get().parseSound(), volume, pitch));
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

}
