package me.doublenico.hypeapi.config;

import me.doublenico.hypeapi.json.DynamicJsonHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LanguageManager extends ConfigManager{

    private String language;
    private static final HashMap<UUID, LanguageManager> playerLanguage = new HashMap<>();
    private static final List<LanguageManager> languages = new ArrayList<>();
    private static LanguageManager defaultLanguage;


    public LanguageManager(String file, String language, String dir) {
        super(file, dir);
        this.language = language;
        languages.add(this);
    }

    public String getLanguage() {
        return language;
    }

    public static LanguageManager getDefaultLanguage() {
        return defaultLanguage;
    }

    public static LanguageManager getPlayerLanguage(Player player) {
        return playerLanguage.getOrDefault(player.getUniqueId(), getDefaultLanguage());
    }

    public static LanguageManager getPlayerLanguage(UUID uuid) {
        return playerLanguage.getOrDefault(uuid, getDefaultLanguage());
    }

    public static boolean isLanguage(String language) {
        return languages.stream().anyMatch(lang -> lang.getLanguage().equalsIgnoreCase(language));
    }

    public static LanguageManager getLanguage(String language) {
        return languages.stream().filter(lang -> lang.getLanguage().equalsIgnoreCase(language)).findFirst().orElse(null);
    }

    public static void changePlayerLanguage(Player player, String language) {
        if (isLanguage(language)) {
            playerLanguage.put(player.getUniqueId(), getLanguage(language));
        }
    }

    public static void changePlayerLanguage(UUID uuid, String language) {
        if (isLanguage(language)) {
            playerLanguage.put(uuid, getLanguage(language));
        }
    }

    public static void changeDefaultLanguage(String language) {
        if (isLanguage(language)) {
            defaultLanguage = getLanguage(language);
        }
    }

    public static void addLanguage(LanguageManager language) {
        languages.add(language);
    }

    public static void removeLanguage(LanguageManager language) {
        languages.remove(language);
    }

    public static void removeLanguage(String language) {
        languages.removeIf(lang -> lang.getLanguage().equalsIgnoreCase(language));
    }

    public static void removePlayerLanguage(Player player) {
        playerLanguage.remove(player.getUniqueId());
    }

    public static void removePlayerLanguage(UUID uuid) {
        playerLanguage.remove(uuid);
    }

    public static void clearPlayerLanguages() {
        playerLanguage.clear();
    }

    public static void clearLanguages() {
        languages.clear();
    }

    public static void clearDefaultLanguage() {
        defaultLanguage = null;
    }

    public static void clear() {
        clearPlayerLanguages();
        clearLanguages();
        clearDefaultLanguage();
    }

    public static boolean hasLanguage(Player player) {
        return playerLanguage.containsKey(player.getUniqueId());
    }

    public static boolean hasLanguage(UUID uuid) {
        return playerLanguage.containsKey(uuid);
    }

    public static boolean hasDefaultLanguage() {
        return defaultLanguage != null;
    }

    public String getMessage(String path) {
        return getDefaultLanguage().getString(path);
    }

    public String getMessage(Player player, String path){
        if(hasLanguage(player)) return getPlayerLanguage(player).getString(path);
        return getMessage(path);
    }

    public String getMessage(UUID uuid, String path){
        if(hasLanguage(uuid)) return getPlayerLanguage(uuid).getString(path);
        return getMessage(path);
    }

    public String getFormattedMessage(Player player,String path){
        if(hasLanguage(player)) return getPlayerLanguage(player).getFormattedString(player,path);
        return getMessage(path);
    }

    public String getFormattedMessage(UUID uuid,String path){
        if(hasLanguage(uuid)) return getPlayerLanguage(uuid).getFormattedString(Bukkit.getPlayer(uuid),path);
        return getDefaultLanguage().getFormattedString(Bukkit.getPlayer(uuid),path);
    }

    public void getConfigMessage(Player player,String path){
        if(hasLanguage(player)) {
            new DynamicJsonHandler().getConfigJson(player, getPlayerLanguage(player).getConfig(), path);
            return;
        }
        new DynamicJsonHandler().getConfigJson(player, getConfig(), path);
    }

    public void getConfigMessage(Plugin plugin, Player player, String path){
        if(hasLanguage(player)) {
            new DynamicJsonHandler().getConfigJson(player, getPlayerLanguage(player).getConfig(), path);
            new DynamicJsonHandler().checkComponents(plugin, player, getPlayerLanguage(player).getConfig(), path);
            return;
        }
        new DynamicJsonHandler().checkComponents(plugin, player, getPlayerLanguage(player).getConfig(), path);
        new DynamicJsonHandler().getConfigJson(player, getConfig(), path);
    }

    public List<String> getMessageList(String path){
        return getDefaultLanguage().getStringList(path);
    }

    public List<String> getMessageList(Player player, String path){
        if(hasLanguage(player)) return getPlayerLanguage(player).getStringList(path);
        return getMessageList(path);
    }

    public List<String> getFormattedMessageList(Player player,String path){
        if(hasLanguage(player)) return getPlayerLanguage(player).getFormattedStringList(player,path);
        return getMessageList(path);
    }

    public static List<LanguageManager> getLanguages() {
        return languages;
    }
}


