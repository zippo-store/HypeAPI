package me.doublenico.hypeapi.wrapper;

import java.util.LinkedList;

public abstract class TitleWrapper extends NMSWrapper {

    private static final LinkedList<TitleWrapper> titleWrappers = new LinkedList<>();

    public abstract void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);

    public abstract void sendTitle(String title, String subtitle);

    public abstract void sendTitle(String title);

    public abstract void sendSubtitle(String subtitle, int fadeIn, int stay, int fadeOut);

    public abstract void sendSubtitle(String subtitle);

    public abstract void clearTitle();

    @Override
    public ChatComponents getChatComponents() {
        return ChatComponents.TITLE;
    }

}
