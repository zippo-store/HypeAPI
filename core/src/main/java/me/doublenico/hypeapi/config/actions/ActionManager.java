package me.doublenico.hypeapi.config.actions;

public class ActionManager {

    private ConfigActions actions;
    private String message;

    public ActionManager(ConfigActions actions, String message) {
        this.actions = actions;
        this.message = message;
    }

    public ConfigActions getActions() {
        return actions;
    }

    public void setActions(ConfigActions actions) {
        this.actions = actions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
