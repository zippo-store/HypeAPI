package me.doublenico.hypeapi.v1_9_R1;

import me.doublenico.hypeapi.wrapper.NBTWrapper;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTItemStack_v1_9_R1 extends NBTWrapper {

    private final net.minecraft.server.v1_9_R1.ItemStack item;
    public NBTItemStack_v1_9_R1(ItemStack itemStack) {
        super(itemStack);
        this.item = CraftItemStack.asNMSCopy(itemStack);
        if (item.getTag() == null) item.setTag(new NBTTagCompound());

    }

    @Override
    public String getString(String key) {
        return item.getTag().getString(key);
    }

    @Override
    public void setString(String key, String value) {
        item.getTag().setString(key, value);
    }

    @Override
    public Integer getInt(String key) {
        return item.getTag().getInt(key);
    }

    @Override
    public void setInt(String key, int value) {
        item.getTag().setInt(key, value);
    }

    @Override
    public boolean getBoolean(String key) {
        return item.getTag().getBoolean(key);
    }

    @Override
    public void setBoolean(String key, boolean value) {
        item.getTag().setBoolean(key, value);
    }

    @Override
    public Double getDouble(String key) {
        return item.getTag().getDouble(key);
    }

    @Override
    public void setDouble(String key, double value) {
        item.getTag().setDouble(key, value);
    }

    @Override
    public Float getFloat(String key) {
        return item.getTag().getFloat(key);
    }

    @Override
    public void setFloat(String key, float value) {
        item.getTag().setFloat(key, value);
    }

    @Override
    public Long getLong(String key) {
        return item.getTag().getLong(key);
    }

    @Override
    public void setLong(String key, long value) {
        item.getTag().setLong(key, value);
    }

    @Override
    public boolean hasKey(String key) {
        return item.getTag().hasKey(key);
    }

    @Override
    public ItemStack getItemStack() {
        return CraftItemStack.asBukkitCopy(item);
    }
    
}
