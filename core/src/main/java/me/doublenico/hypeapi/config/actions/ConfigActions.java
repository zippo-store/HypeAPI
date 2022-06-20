package me.doublenico.hypeapi.config.actions;

public enum ConfigActions {

    CONSOLE("[CONSOLE]"),
    PLAYER("[PLAYER]"),
    MESSAGE("[MESSAGE]"),
    CHAT("[CHAT]"),
    BROADCAST("[BROADCAST]"),
    JSON("[JSON]"),
    JSON_BROADCAST("[JSON_BROADCAST]"),
    SOUND("[SOUND]"),
    SOUND_BROADCAST("[SOUND_BROADCAST]"),
    TITLE("[TITLE]"),
    TITLE_BROADCAST("[TITLE_BROADCAST]"),
    SUBTITLE("[SUBTITLE]"),
    SUBTITLE_BROADCAST("[SUBTITLE_BROADCAST]"),
    ACTIONBAR("[ACTIONBAR]"),
    ACTIONBAR_BROADCAST("[ACTIONBAR_BROADCAST]"),
    BOSSBAR("[BOSSBAR]"),
    BOSSBAR_BROADCAST("[BOSSBAR_BROADCAST]");

    public final String command;

    ConfigActions(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static ConfigActions getAction(String command) {
        for (ConfigActions action : ConfigActions.values()) {
            if (action.getCommand().startsWith(command)) {
                return action;
            }
        }
        return null;
    }

}
