package me.doublenico.hypeapi.wrapper;

import java.util.ArrayList;

public abstract class ActionBarWrapper extends NMSWrapper {

    public abstract void createActionBar(String message);

    @Override
    public ChatComponents getChatComponents() {
        return ChatComponents.ACTION_BAR;
    }

}
