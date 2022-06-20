package me.doublenico.hypeapi.v1_11_R1;

import me.doublenico.hypeapi.wrapper.ActionBarWrapper;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar_v1_11_R1 extends ActionBarWrapper {
    private PacketPlayOutChat packet;

    public ActionBar_v1_11_R1(){
        super();
    }

    @Override
    public void createActionBar(String message) {
        this.packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
    }

    @Override
    public Versions getVersion() {
        return Versions.v1_11R1;
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
