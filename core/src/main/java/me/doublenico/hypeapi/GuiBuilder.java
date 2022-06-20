package me.doublenico.hypeapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class GuiBuilder implements InventoryHolder, Listener {

    private final String name;
    private final int size;
    protected Inventory inventory;

    public GuiBuilder(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event){
        if(!(event.getInventory().getHolder() instanceof GuiBuilder)) return;
        event.setCancelled(true);

        if (event.getCurrentItem() == null) return;

        this.handleMenu(event);
    }

    public abstract void handleMenu(InventoryClickEvent event);

    public abstract void handleItems();

    public void open(Player player){
        this.inventory = Bukkit.createInventory(this, this.size, this.name);

        this.handleItems();

        player.openInventory(this.inventory);
    }

    public void close(Player player){
        player.closeInventory();
    }

    public GuiBuilder enable(Plugin plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
        return this;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

}
