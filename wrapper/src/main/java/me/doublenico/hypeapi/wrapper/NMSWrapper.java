package me.doublenico.hypeapi.wrapper;

import org.bukkit.entity.Player;

public abstract class NMSWrapper {

    public abstract Versions getVersion();

    public abstract ChatComponents getChatComponents();

    public abstract void sendToPlayer(Player player);

    public abstract void sendToAll();

    public enum ChatComponents {
        ACTION_BAR, TITLE, BOSSBAR;

        public static ChatComponents get(String name) {
            for (ChatComponents chatComponent : ChatComponents.values()) {
                if (chatComponent.name().equalsIgnoreCase(name)) {
                    return chatComponent;
                }
            }
            return null;
        }
    }

    public enum Versions {
        v1_8R1("v1_8_R1"),
        v1_8R2("v1_8_R2"),
        v1_8R3("v1_8_R3"),
        v1_9R1("v1_9_R1"),
        v1_9R2("v1_9_R2"),
        v1_10R1("v1_10_R1"),
        v1_11R1("v1_11_R1"),
        v1_12R1("v1_12_R1"),
        v1_13R1("v1_13_R1"),
        v1_13R2("v1_13_R2"),
        v1_14R1("v1_14_R1"),
        v1_15R1("v1_15_R1"),
        v1_16R1("v1_16_R1"),
        v1_16R2("v1_16_R2"),
        v1_16R3("v1_16_R3"),
        v1_17R1("v1_17_R1"),
        v1_18R1("v1_18_R1"),
        v1_18R2("v1_18_R2");

        private final String version;

        Versions(String version) {
            this.version = version;
        }

        public String getVersionString() {
            return version;
        }

        public static Versions getVersion(String version) {
            for (Versions v : values()) {
                if (v.getVersionString().equalsIgnoreCase(version)) {
                    return v;
                }
            }
            return null;
        }

        public static boolean isSupported(String version) {
            for (Versions v : values()) {
                if (v.getVersionString().equalsIgnoreCase(version)) {
                    return true;
                }
            }
            return false;
        }

    }

}
