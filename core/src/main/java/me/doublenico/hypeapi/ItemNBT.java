package me.doublenico.hypeapi;

import me.doublenico.hypeapi.v1_10_R1.NBTItemStack_v1_10_R1;
import me.doublenico.hypeapi.v1_11_R1.NBTItemStack_v1_11_R1;
import me.doublenico.hypeapi.v1_12_R1.NBTItemStack_v1_12_R1;
import me.doublenico.hypeapi.v1_8_R1.NBTItemStack_v1_8_R1;
import me.doublenico.hypeapi.v1_8_R2.NBTItemStack_v1_8_R2;
import me.doublenico.hypeapi.v1_8_R3.NBTItemStack_v1_8_R3;
import me.doublenico.hypeapi.v1_9_R1.NBTItemStack_v1_9_R1;
import me.doublenico.hypeapi.v1_9_R2.NBTItemStack_v1_9_R2;
import me.doublenico.hypeapi.wrapper.NBTWrapper;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ItemNBT {

    private final ItemBuilder itemStack;

    public ItemNBT(ItemBuilder itemBuilder) {
        this.itemStack = itemBuilder;
    }

    public ItemBuilder getItemStack() {
        return itemStack;
    }

    public String getString(String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        return getNBTWrapper().getString(key);
    }

    public String getString(Plugin plugin, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getString(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(new NamespacedKey(plugin, key), ItemTagType.STRING);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public String getString(NamespacedKey namespacedKey, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getString(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(namespacedKey, ItemTagType.STRING);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }


    public Integer getInt(String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        return getNBTWrapper().getInt(key);
    }

    public Integer getInt(Plugin plugin, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getInt(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(new NamespacedKey(plugin, key), ItemTagType.INTEGER);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
    }

    public Integer getInt(NamespacedKey namespacedKey, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getInt(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(namespacedKey, ItemTagType.INTEGER);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.INTEGER);
    }

    public Boolean getBoolean(String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        return getNBTWrapper().getBoolean(key);
    }

    public Boolean getBoolean(Plugin plugin, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getBoolean(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(new NamespacedKey(plugin, key), ItemTagType.BYTE) != 0;
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.BYTE) != 0;
    }

    public Boolean getBoolean(NamespacedKey namespacedKey, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getBoolean(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(namespacedKey, ItemTagType.BYTE) != 0;
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.BYTE) != 0;
    }

    public Double getDouble(String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        return getNBTWrapper().getDouble(key);
    }

    public Double getDouble(Plugin plugin, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getDouble(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(new NamespacedKey(plugin, key), ItemTagType.DOUBLE);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.DOUBLE);
    }

    public Double getDouble(NamespacedKey namespacedKey, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getDouble(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(namespacedKey, ItemTagType.DOUBLE);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.DOUBLE);
    }

    public Float getFloat(String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        return getNBTWrapper().getFloat(key);
    }

    public Float getFloat(Plugin plugin, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getFloat(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(new NamespacedKey(plugin, key), ItemTagType.FLOAT);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.FLOAT);
    }

    public Float getFloat(NamespacedKey namespacedKey, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getFloat(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(namespacedKey, ItemTagType.FLOAT);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.FLOAT);
    }

    public Long getLong(String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        return getNBTWrapper().getLong(key);
    }

    public Long getLong(Plugin plugin, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getLong(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(new NamespacedKey(plugin, key), ItemTagType.LONG);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.LONG);
    }

    public Long getLong(NamespacedKey namespacedKey, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return null;
        if (VersionUtils.lowerThan112()) return getLong(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().getCustomTag(namespacedKey, ItemTagType.LONG);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.LONG);
    }

    public boolean hasKey(String key){
        if (itemStack.getItemStack().getItemMeta() == null) return false;
        return getNBTWrapper().hasKey(key);
    }

    public boolean hasKey(Plugin plugin, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return false;
        if (VersionUtils.lowerThan112()) return hasKey(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().hasCustomTag(new NamespacedKey(plugin, key), ItemTagType.STRING);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public boolean hasKey(NamespacedKey namespacedKey, String key){
        if (itemStack.getItemStack().getItemMeta() == null) return false;
        if (VersionUtils.lowerThan112()) return hasKey(key);
        if (VersionUtils.is113()) return itemStack.getItemStack().getItemMeta().getCustomTagContainer().hasCustomTag(namespacedKey, ItemTagType.STRING);
        return itemStack.getItemStack().getItemMeta().getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING);
    }

    public void setString(String key, String value){
        getNBTWrapper().setString(key, value);
    }

    public void setString(Plugin plugin, String key, String value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setString(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, key), ItemTagType.STRING, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
    }

    public void setString(NamespacedKey namespacedKey, String key, String value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setString(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(namespacedKey, ItemTagType.STRING, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
    }

    public void setInt(String key, int value){
        getNBTWrapper().setInt(key, value);
    }

    public void setInt(Plugin plugin, String key, int value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setInt(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, key), ItemTagType.INTEGER, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, value);
    }

    public void setInt(NamespacedKey namespacedKey, String key, int value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setInt(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(namespacedKey, ItemTagType.INTEGER, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, value);
    }

    public void setDouble(String key, double value){
        getNBTWrapper().setDouble(key, value);
    }

    public void setDouble(Plugin plugin, String key, double value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setDouble(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, key), ItemTagType.DOUBLE, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.DOUBLE, value);
    }

    public void setDouble(NamespacedKey namespacedKey, String key, double value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setDouble(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(namespacedKey, ItemTagType.DOUBLE, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(namespacedKey, PersistentDataType.DOUBLE, value);
    }

    public void setLong(String key, long value){
        getNBTWrapper().setLong(key, value);
    }

    public void setLong(Plugin plugin, String key, long value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setLong(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, key), ItemTagType.LONG, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.LONG, value);
    }

    public void setLong(NamespacedKey namespacedKey, String key, long value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setLong(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(namespacedKey, ItemTagType.LONG, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(namespacedKey, PersistentDataType.LONG, value);
    }

    public void setBoolean(String key, boolean value){
        getNBTWrapper().setBoolean(key, value);
    }

    public void setBoolean(Plugin plugin, String key, boolean value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setBoolean(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, key), ItemTagType.BYTE, value ? (byte) 1 : (byte) 0);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.BYTE, value ? (byte) 1 : (byte) 0);
    }

    public void setBoolean(NamespacedKey namespacedKey, String key, boolean value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setBoolean(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(namespacedKey, ItemTagType.BYTE, value ? (byte) 1 : (byte) 0);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(namespacedKey, PersistentDataType.BYTE, value ? (byte) 1 : (byte) 0);
    }

    public void setFloat(String key, float value){
        getNBTWrapper().setFloat(key, value);
    }

    public void setFloat(Plugin plugin, String key, float value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setFloat(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, key), ItemTagType.FLOAT, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.FLOAT, value);
    }

    public void setFloat(NamespacedKey namespacedKey, String key, float value){
        if (itemStack.getItemStack().getItemMeta() == null) return;
        if (VersionUtils.lowerThan112()) getNBTWrapper().setFloat(key, value);
        if (VersionUtils.is113()) itemStack.getItemStack().getItemMeta().getCustomTagContainer().setCustomTag(namespacedKey, ItemTagType.FLOAT, value);
        else itemStack.getItemStack().getItemMeta().getPersistentDataContainer().set(namespacedKey, PersistentDataType.FLOAT, value);
    }

    private NBTWrapper getNBTWrapper() {
        if (itemStack == null) return null;
        switch (VersionUtils.getNMSVersion()) {
            case "v1_8_R1": return new NBTItemStack_v1_8_R1(itemStack.getItemStack());
            case "v1_8_R2": return new NBTItemStack_v1_8_R2(itemStack.getItemStack());
            case "v1_8_R3": return new NBTItemStack_v1_8_R3(itemStack.getItemStack());
            case "v1_9_R1": return new NBTItemStack_v1_9_R1(itemStack.getItemStack());
            case "v1_9_R2": return new NBTItemStack_v1_9_R2(itemStack.getItemStack());
            case "v1_10_R1": return new NBTItemStack_v1_10_R1(itemStack.getItemStack());
            case "v1_11_R1": return new NBTItemStack_v1_11_R1(itemStack.getItemStack());
            case "v1_12_R1": return new NBTItemStack_v1_12_R1(itemStack.getItemStack());
        }
        return null;
    }

}
