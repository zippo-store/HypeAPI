package me.doublenico.hypeapi.config;

import me.clip.placeholderapi.PlaceholderAPI;
import me.doublenico.hypeapi.colors.ColorChat;
import me.doublenico.hypeapi.config.conditions.Condition;
import me.doublenico.hypeapi.config.conditions.ConditionManager;
import me.doublenico.hypeapi.json.DynamicJsonHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class ConfigManager {
    private YamlConfiguration yml;
    private File config;
    private String fileName;
    private boolean firstTime = false;

    public ConfigManager(String name, String dir) {
        File d = new File(dir);
        if (!d.exists() && !d.mkdirs()) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not create " + d.getPath());
        } else {
            this.config = new File(dir, name + ".yml");
            if (!this.config.exists()) {
                this.firstTime = true;
                Bukkit.getLogger().log(Level.INFO, "Creating " + this.config.getPath());

                try {
                    if (!this.config.createNewFile()) {
                        Bukkit.getLogger().log(Level.SEVERE, "Could not create " + this.config.getPath());
                        return;
                    }
                } catch (IOException var5) {
                    var5.printStackTrace();
                }
            }

            this.yml = YamlConfiguration.loadConfiguration(this.config);
            this.yml.options().copyDefaults(true);
            this.fileName = name;
        }
    }

    public void save() {
        try {
            this.yml.save(this.config);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void reload() {
        this.yml = YamlConfiguration.loadConfiguration(this.config);
    }

    public YamlConfiguration getConfig() {
        return this.yml;
    }

    public String getFileName() {
        return this.fileName;
    }

    public boolean isFirstTime() {
        return this.firstTime;
    }

    public String getString(String path) {
        return ColorChat.color(this.yml.getString(path));
    }

    public String getFormattedString(Player player, String path) {
        return ColorChat.convert(player, path);
    }

    public List<String> getStringList(String path) {
        return ColorChat.colorList(this.yml.getStringList(path));
    }

    public List<String> getFormattedStringList(Player player, String path) {
        return ColorChat.colorList(PlaceholderAPI.setPlaceholders(player, this.yml.getStringList(path)));
    }

    public int getInt(String path) {
        return this.yml.getInt(path);
    }

    public boolean getBoolean(String path) {
        return this.yml.getBoolean(path);
    }

    public double getDouble(String path) {
        return this.yml.getDouble(path);
    }

    public void set(String path, Object value, boolean save) {
        if (save) {
            this.yml.set(path, value);
            this.save();
        } else {
            this.yml.set(path, value);
        }
    }

    public void set(String path, Object value) {
        this.set(path, value, true);
    }

    public void setString(String path, String value) {
        this.yml.set(path, value);
    }

    public void setStringList(String path, List<String> value) {
        this.yml.set(path, value);
    }

    public void setInt(String path, int value) {
        this.yml.set(path, value);
    }

    public void setBoolean(String path, boolean value) {
        this.yml.set(path, value);
    }

    public void setDouble(String path, double value) {
        this.yml.set(path, value);
    }

    public void remove(String path) {
        this.yml.set(path, (Object)null);
    }

    public void setValue(String path, Object value, boolean save) {
        String[] split = path.split("\\.");
        String currentPath = "";
        for (String s : split) {
            if (currentPath.equals("")) {
                currentPath = s;
            } else {
                currentPath = currentPath + "." + s;
            }

            if (this.yml.get(currentPath) == null) {
                this.yml.set(currentPath, value);
            }
        }

        if (save) {
            this.save();
        }

    }

    public void setValue(String path, Object value) {
        this.setValue(path, value, true);
    }

    public boolean contains(String path) {
        return this.yml.get(path) != null;
    }

    public void getCustomMessage(Player player, String path){
        new DynamicJsonHandler().getConfigJson(player, getConfig(),path);
    }

    public void getCustomMessage(Plugin plugin, Player player, String path){
        getCustomMessage(player, path);
        new DynamicJsonHandler().checkComponents(plugin, player, getConfig(),path);
    }

    public boolean checkCondition(Player player, String path){
        if (getConfig().get(path + ".conditions") == null) return true;
        ConfigurationSection section = getConfig().getConfigurationSection(path + ".conditions");
        if (section == null) return true;
        for (String key : section.getKeys(false)){
            ConfigurationSection block = section.getConfigurationSection(key);
            if (block == null) continue;
            if (block.get(".condition") == null) continue;
            if (ConditionManager.getCondition(block.getString(".condition")) == null) continue;
            Condition condition = ConditionManager.getCondition(block.getString(".condition"));
            if (condition == null){
                Bukkit.getLogger().log(Level.SEVERE, "Could not find condition " + block.getString(".condition"));
                continue;
            }
            if (block.get(condition.getIndentifier()) == null) continue;
            return condition.checkCondition(player, block.getString(condition.getIndentifier()));
        }
        return true;
    }

    public int checkPriority(String path){
        if (getConfig().get(path + ".priority") == null) return 1;
        return getConfig().getInt(path + ".priority");
    }


}