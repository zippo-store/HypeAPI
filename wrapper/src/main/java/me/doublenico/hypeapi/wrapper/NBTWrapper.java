package me.doublenico.hypeapi.wrapper;

import org.bukkit.inventory.ItemStack;

public abstract class NBTWrapper {

    public NBTWrapper(ItemStack itemStack) {}

    public abstract String getString(String key);

    public abstract void setString(String key, String value);

    public abstract Integer getInt(String key);

    public abstract void setInt(String key, int value);

    public abstract boolean getBoolean(String key);

    public abstract void setBoolean(String key, boolean value);

    public abstract Double getDouble(String key);

    public abstract void setDouble(String key, double value);

    public abstract Float getFloat(String key);

    public abstract void setFloat(String key, float value);

    public abstract Long getLong(String key);

    public abstract void setLong(String key, long value);

    public abstract boolean hasKey(String key);

    public abstract ItemStack getItemStack();
}
