package me.doublenico.hypeapi.colors;

import dev.perryplaysmc.dynamicjson.DynamicJText;
import dev.perryplaysmc.dynamicjson.data.CColor;
import me.clip.placeholderapi.PlaceholderAPI;
import me.doublenico.hypeapi.VersionUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorChat {

    public static String convert(Player player, String message) {
        return color(placeholder(player, message));
    }

    public static String placeholder(Player player, String message) {
        if (message == null) {
            return null;
        }
        if (message.isEmpty()) {
            return "";
        }
        return PlaceholderAPI.setPlaceholders(player, message);
    }

    public static String color(String message) {
        if (message == null) {
            return null;
        }
        if (message.isEmpty()) {
            return "";
        }
        if (!VersionUtils.hasHexSupport()) return ChatColor.translateAlternateColorCodes('&', message);
        String gradientRegex = "<gradient:((?:(?=#[a-fA-F0-9]{6}[;>])#[a-fA-F0-9]{6}[^>]?)+)>(.+)<.gradient>";
        Pattern pattern = Pattern.compile(gradientRegex);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String gradient = matcher.group(1);
            String text = matcher.group(2);
            if (gradient.isEmpty()) {
                Bukkit.getLogger().warning("Gradient is empty!");
                continue;
            }
            if(text.isEmpty()) {
                Bukkit.getLogger().warning("Text is empty!");
                continue;
            }
            List<CColor> gradients = new ArrayList<>();
            for (String color : gradient.split(";")) {
                gradients.add(CColor.fromHex(color));
            }
            if (gradients.size() < 2) {
                Bukkit.getLogger().warning("Gradient is not valid!");
                continue;
            }
            return CColor.translateGradient(text, gradients.toArray(new CColor[0]));
        }
        return CColor.translateCommon(message);
    }

    public static BaseComponent[] colorJSON(String message) {
        if (!VersionUtils.hasHexSupport()) return new DynamicJText(CColor.translateAlternateColorCodes('&', message)).toComponents();
        String gradientRegex = "<gradient:((?:(?=#[a-fA-F0-9]{6}[;>])#[a-fA-F0-9]{6}[^>]?)+)>(.+)<.gradient>";
        Pattern pattern = Pattern.compile(gradientRegex);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String gradient = matcher.group(1);
            String text = matcher.group(2);
            if (gradient.isEmpty()) {
                Bukkit.getLogger().warning("Gradient is empty!");
                continue;
            }
            if(text.isEmpty()) {
                Bukkit.getLogger().warning("Text is empty!");
                continue;
            }
            List<CColor> gradients = new ArrayList<>();
            for (String color : gradient.split(";")) {
                gradients.add(CColor.fromHex(color));
            }
            if (gradients.size() < 2) {
                Bukkit.getLogger().warning("Gradient is not valid!");
                continue;
            }
            return new DynamicJText().gradient(gradients.toArray(new CColor[0])).add(text).finish().toComponents();
        }
        return new DynamicJText().add(CColor.translateCommon(message)).toComponents();
    }

    public static List<String> colorList(List<String> messages) {
        List<String> colored = new ArrayList<>();
        for (String message : messages) {
            colored.add(color(message));
        }
        return colored;
    }

}
