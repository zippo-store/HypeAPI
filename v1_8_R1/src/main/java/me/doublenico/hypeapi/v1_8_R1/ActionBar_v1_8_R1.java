package me.doublenico.hypeapi.v1_8_R1;

import me.doublenico.hypeapi.wrapper.ActionBarWrapper;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar_v1_8_R1 extends ActionBarWrapper {

    private PacketPlayOutChat packet;

    public ActionBar_v1_8_R1(){
        super();
    }

    @Override
    public void createActionBar(String message) {
        this.packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
    }

    @Override
    public Versions getVersion() {
        return Versions.v1_8R1;
    }

    @Override
    public void sendToPlayer(Player p) {
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void sendToAll() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);;
        }
    }

}
