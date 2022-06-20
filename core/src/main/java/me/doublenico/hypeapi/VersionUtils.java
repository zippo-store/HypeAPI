package me.doublenico.hypeapi;

import org.bukkit.Bukkit;

public class VersionUtils {

    public static String getNMSVersion(){
        String v = Bukkit.getServer().getClass().getPackage().getName();
        return v.substring(v.lastIndexOf('.') + 1);
    }

    public static boolean isLegacyVersion() {
        switch (getNMSVersion()) {
            default: return false;
            case "v1_8_R3":
            case "v1_8_R1":
            case "v1_8_R2":
                return true;
        }
    }

    public static boolean hasHexSupport(){
        switch (getNMSVersion()) {
            default: return false;
            case "v1_16_R1":
            case "v1_16_R2":
            case "v1_16_R3":
            case "v1_17_R1":
            case "v1_18_R1":
            case "v1_18_R2": return true;
        }
    }

    public static boolean lowerThan112(){
        switch (getNMSVersion()) {
            default: return false;
            case "v1_11_R1":
            case "v1_10_R1":
            case"v1_9R2":
            case "v1_9R1":
            case"v1_8_R3":
            case "v1_8_R2":
            case "v1_8_R1": return true;
        }
    }

    public static boolean is113(){
        switch (getNMSVersion()) {
            default: return false;
            case "v1_13_R1":
            case "v1_13_R2": return true;
        }
    }


}
