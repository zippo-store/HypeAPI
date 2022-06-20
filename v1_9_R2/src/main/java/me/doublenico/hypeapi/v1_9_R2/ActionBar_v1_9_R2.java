package me.doublenico.hypeapi.v1_9_R2;

import me.doublenico.hypeapi.wrapper.ActionBarWrapper;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar_v1_9_R2 extends ActionBarWrapper {

    private PacketPlayOutChat packet;

    public ActionBar_v1_9_R2(){
        super();
    }

    @Override
    public void createActionBar(String message) {
        this.packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
    }

    @Override
    public Versions getVersion() {
        return Versions.v1_9R2;
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
