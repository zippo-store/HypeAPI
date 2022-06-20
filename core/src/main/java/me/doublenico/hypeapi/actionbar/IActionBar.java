package me.doublenico.hypeapi.actionbar;


import org.bukkit.entity.Player;

public interface IActionBar {

    void send();

    void sendToAll();

    void timer();

    void color();

    static void sendActionBar(Player player, String message) {

    }

    static void sendActionBar(Player player, String message, int duration){

    }

    static void sendBroadcastActionBar(String message){

    }

    static void sendBroadcastActionBar(String message, int duration){

    }
}
