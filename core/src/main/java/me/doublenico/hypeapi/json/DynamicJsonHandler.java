package me.doublenico.hypeapi.json;

import dev.dynamicstudios.json.DynamicJText;
import dev.dynamicstudios.json.data.component.DynamicGradientComponent;
import dev.dynamicstudios.json.data.util.CColor;
import dev.dynamicstudios.json.data.util.DynamicClickAction;
import dev.dynamicstudios.json.data.util.DynamicHoverAction;
import me.doublenico.hypeapi.actionbar.ActionBar;
import me.doublenico.hypeapi.colors.ColorChat;
import me.doublenico.hypeapi.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class DynamicJsonHandler {

    public void checkComponents(Plugin plugin, Player player, YamlConfiguration config, String path){
        if (config.get(path + ".actionBar") != null){
            if (config.get(path + ".actionBar.message") == null){
                Bukkit.getLogger().warning("[HypeAPI] The path " + path + ".actionBar.message does not exist in the configuration file.");return;
            }
            if (config.get(path + ".actionBar.duration") != null)
                if (isNumber(config.getString(path + ".actionBar.duration")))
                    ActionBar.sendActionBar(plugin, player, ColorChat.convert(player,config.getString(path + ".actionBar.message")), Integer.parseInt(ColorChat.convert(player, config.getString(path + ".actionBar.duration"))));
                else
                    ActionBar.sendActionBar(plugin, player, ColorChat.convert(player,config.getString(path + ".actionBar.message")), config.getInt(path + ".actionBar.duration"));
            else
                ActionBar.sendActionBar(player, ColorChat.convert(player,config.getString(path + ".actionBar.message")));
        }
        if (config.get(path + ".title") != null) {
            if (config.get(path + ".title.title") == null) {
                Bukkit.getLogger().warning("[HypeAPI] The path " + path + ".title.title does not exist in the configuration file.");
                return;
            }
            if (config.get(path + ".title.subtitle") == null) {
                Bukkit.getLogger().warning("[HypeAPI] The path " + path + ".title.subtitle does not exist in the configuration file.");
                return;
            }
            int fadeIn = 20;
            int stay = 60;
            int fadeOut = 20;
            if (isNumber(config.getString(path + ".title.fadeIn")))
                fadeIn = Integer.parseInt(ColorChat.convert(player,config.getString(path + ".title.fadeIn")));
            else
                fadeIn = config.getInt(path + ".title.fadeIn");
            if (isNumber(config.getString(path + ".title.stay")))
                stay = Integer.parseInt(ColorChat.convert(player,config.getString(path + ".title.stay")));
            else
                stay = config.getInt(path + ".title.stay");

            if (isNumber(config.getString(path + ".title.fadeOut")))
                fadeOut = Integer.parseInt(ColorChat.convert(player,config.getString(path + ".title.fadeOut")));
            else
                fadeOut = config.getInt(path + ".title.fadeOut");
            Title.sendTitle(player, ColorChat.convert(player,config.getString(path + ".title.title")), ColorChat.convert(player,config.getString(path + ".title.subtitle")), fadeIn, stay, fadeOut);

        }
    }
    public void getConfigJson(Player player, YamlConfiguration config, String path) {
        if (config == null) {Bukkit.getLogger().warning("Config is null");return;}

        if (config.get(path) == null) {Bukkit.getLogger().warning("[HypeAPI] The path " + path + " does not exist in the configuration file.");return;}

        if(!config.getBoolean(path + ".format")) {DynamicJText.parseText((ColorChat.convert(player, config.getString(path)))).send(player);return;}


        ConfigurationSection section = config.getConfigurationSection(path + ".keys");
        DynamicJText text = new DynamicJText();
        if (section == null) { Bukkit.getLogger().warning("[HypeAPI] The path " + path + ".keys does not exist in the configuration file.");return;}
        for (String key : section.getKeys(false)) {
            ConfigurationSection block = section.getConfigurationSection(key);
            if (block == null) {
                Bukkit.getLogger().warning("[HypeAPI] The path " + path + ".keys." + key + " does not exist in the configuration file.");
                return;
            }
            if (block.getString("message") == null) {
                Bukkit.getLogger().warning("[HypeAPI] The path " + path + "." + key + " does not contain a message.");
                return;
            }
            DynamicJText messageComponent = new DynamicJText(ColorChat.convert(player, block.getString("message")));
            if (block.get("hover") != null) {
                if (block.getString("hover") != null) messageComponent.hoverPlain(ColorChat.convert(player, block.getString("hover")));

                List<String> hover = block.getStringList("hover");
                List<String> converted = new ArrayList<>();
                for (String s : hover) {
                    converted.add(ColorChat.convert(player, s));
                }
                String[] convertedArray = converted.toArray(new String[0]);
                messageComponent.hover(convertedArray);
            }

            if (block.get("hover" + ".action") != null) {
                if (block.get("hover" + ".value") == null)
                    Bukkit.getLogger().warning("[HypeAPI] The path " + path + "." + key + " does not contain a hover value.");
                messageComponent.hover(DynamicHoverAction.valueOf(block.getString("hover" + ".action")), ColorChat.placeholder(player, block.getString("hover" + ".value")));
            }

            if (block.getString("command") != null) messageComponent.command(block.getString("command"));
            if (block.getString("suggest") != null) messageComponent.suggest(block.getString("suggest"));
            if (block.getString("url") != null) messageComponent.url(block.getString("url"));

            if (block.get("click") != null) {
                if (block.getString("click" + ".action") == null)
                    Bukkit.getLogger().warning("[HypeAPI] The path " + path + "." + key + " does not contain a click action.");
                if (block.getString("click" + ".value") == null)
                    Bukkit.getLogger().warning("[HypeAPI] The path " + path + "." + key + " does not contain a click value.");
                if (block.getString("click" + ".action") != null && block.getString("click" + ".value") != null)
                    messageComponent.click(DynamicClickAction.valueOf(block.getString("click" + ".action")), ColorChat.convert(player, block.getString("click" + ".value")));
            }

            if (block.get("gradient") != null) {
                if (block.get("gradient.colors") == null && block.get("gradient.start") != null) {
                    if (block.getString("gradient" + ".start") == null) {
                        Bukkit.getLogger().warning("[HypeAPI] The path " + path + "." + key + " does not contain a gradient start.");
                        return;
                    }
                    if (block.getString("gradient" + ".end") == null) {
                        Bukkit.getLogger().warning("[HypeAPI] The path " + path + "." + key + " does not contain a gradient end.");
                        return;
                    }
                    if (block.getString("gradient" + ".start") != null && block.getString("gradient" + ".end") != null) {
                        messageComponent.gradient(true);
                        messageComponent.add(new DynamicGradientComponent().colors(CColor.fromHex(ColorChat.placeholder(player, block.getString("gradient" + ".start"))), CColor.fromHex(ColorChat.placeholder(player, block.getString("gradient" + ".end")))));
                    }
                    if (block.getString("gradient" + ".start") != null && block.getString("gradient" + ".end") != null && block.getString("gradient" + ".align") != null) {
                        if (CColor.GradientCenter.fromName(block.getString("gradient" + ".align")) == null) {
                            messageComponent.gradient(true);
                            messageComponent.add(new DynamicGradientComponent().colors(CColor.fromHex(ColorChat.placeholder(player, block.getString("gradient" + ".start"))), CColor.fromHex(ColorChat.placeholder(player, block.getString("gradient" + ".end")))).center(CColor.GradientCenter.LEFT));
                        }
                        else {
                            messageComponent.gradient(true);
                            messageComponent.add(new DynamicGradientComponent().colors(CColor.fromHex(ColorChat.placeholder(player, block.getString("gradient" + ".start"))), CColor.fromHex(ColorChat.placeholder(player, block.getString("gradient" + ".end")))).center(CColor.GradientCenter.fromName(block.getString("gradient" + ".align"))));
                            Bukkit.getLogger().info("[HypeAPI] The path " + path + "." + key + " has a gradient align of " + block.getString("gradient" + ".align"));
                            Bukkit.getLogger().info("[HypeAPI] The path " + path + "." + key + " has a gradient start of " + block.getString("gradient" + ".start"));
                            Bukkit.getLogger().info("[HypeAPI] The path " + path + "." + key + " has a gradient start of " + block.getString("gradient" + ".end"));
                        }
                    }
                }
                if (block.get("gradient.colors") != null && block.get("gradient.start") == null) {
                    block.getStringList("gradient.colors");
                    List<String> colors = block.getStringList("gradient" + ".colors");
                    List<CColor> cColors = new ArrayList<>();
                    colors.forEach(color -> cColors.add(CColor.fromHex(ColorChat.placeholder(player, color))));
                    if (colors.isEmpty()) {
                        Bukkit.getLogger().warning("[HypeAPI] The path " + path + "." + key + " does not contain a gradient color.");
                        return;
                    }
                    if (colors.size() < 2) {
                        Bukkit.getLogger().warning("[HypeAPI] The path " + path + "." + key + " does not contain 2 or more gradient colors.");
                        return;
                    }
                    if (block.getString("gradient" + ".align") == null) {
                        messageComponent.gradient(true);
                        messageComponent.add(new DynamicGradientComponent().colors(cColors.toArray(new CColor[0])));
                    }
                    else {
                        messageComponent.gradient(true);
                        messageComponent.add(new DynamicGradientComponent().colors(cColors.toArray(new CColor[0])).center(CColor.GradientCenter.fromName(block.getString("gradient" + ".align"))));
                        Bukkit.getLogger().info("[HypeAPI] The path " + path + "." + key + " has a gradient align of " + block.getString("gradient" + ".align"));
                        Bukkit.getLogger().info("[HypeAPI] The path " + path + "." + key + " has a gradient align of " + cColors);
                    }
                }
            }
            text.add(messageComponent);
        }
        text.send(player);
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBoolean(String str) {
        if (str == null || str.isEmpty()) return false;
        return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false");
    }

}
