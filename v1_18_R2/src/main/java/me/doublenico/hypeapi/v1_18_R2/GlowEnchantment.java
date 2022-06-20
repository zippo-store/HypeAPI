package me.doublenico.hypeapi.v1_18_R2;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

public class GlowEnchantment extends Enchantment {

    public GlowEnchantment(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Glow";
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }

    public static Enchantment getEnchant(Plugin plugin) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            GlowEnchantment glow = new GlowEnchantment(new NamespacedKey(plugin, "glow"));
            Enchantment.registerEnchantment(glow);
            return glow;
        }
        catch (IllegalArgumentException e){
            return DEPTH_STRIDER;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return DEPTH_STRIDER;
    }

}
