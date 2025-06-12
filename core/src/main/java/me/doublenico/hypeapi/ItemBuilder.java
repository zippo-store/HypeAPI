package me.doublenico.hypeapi;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import me.doublenico.hypeapi.colors.ColorChat;
import me.doublenico.hypeapi.config.ConfigManager;
import me.doublenico.hypeapi.v1_8_R1.GlowEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {


    private final XMaterial material;
    private final ItemStack itemStack;
    private Plugin plugin;
    private int amount;
    private String name;
    private List<String> lore;
    private boolean enchanted;
    private boolean hideFlags;
    private boolean hideAttributes;
    private boolean hideEnchants;
    private HashMap<Enchantment, Integer> enchants;
    private int customModelData;


    public ItemBuilder(XMaterial material) {
        this.material = material;
        if (material.get() == null) itemStack = new ItemStack(Material.DIRT);
        else itemStack = new ItemStack(material.get());
    }

    public ItemBuilder(Material material) {
        this.material = XMaterial.matchXMaterial(material);
        itemStack = new ItemStack(material);
    }

    public ItemBuilder(String material) {
        this.material = XMaterial.matchXMaterial(material).isPresent() ? XMaterial.matchXMaterial(material).get() : XMaterial.matchXMaterial(Material.DIRT);
        if (this.material.get() == null) itemStack = new ItemStack(Material.DIRT);
        else itemStack = new ItemStack(this.material.get());
    }

    public ItemBuilder(Plugin plugin, String material){
        this.material = XMaterial.matchXMaterial(material).isPresent() ? XMaterial.matchXMaterial(material).get() : XMaterial.matchXMaterial(Material.DIRT);
        this.plugin = plugin;
        if (this.material.get() == null) itemStack = new ItemStack(Material.DIRT);
        else itemStack = new ItemStack(this.material.get());
    }

    public ItemBuilder(Plugin plugin, Material material){
        this.material = XMaterial.matchXMaterial(material);
        this.plugin = plugin;
        itemStack = new ItemStack(material);
    }

    public ItemBuilder(Plugin plugin, XMaterial material){
        this.material = material;
        this.plugin = plugin;
        if (material.get() == null) itemStack = new ItemStack(Material.DIRT);
        else itemStack = new ItemStack(material.get());
    }

    public ItemBuilder(ItemStack itemStack) {
        this.material = XMaterial.matchXMaterial(itemStack.getType()).or(XMaterial.DIRT);
        this.itemStack = itemStack;
        this.amount = itemStack.getAmount();
        if (itemStack.getItemMeta() != null) {
            ItemMeta meta = itemStack.getItemMeta();
            this.name = meta.hasDisplayName() ? meta.getDisplayName() : null;
            this.lore = meta.hasLore() ? meta.getLore() : null;
            this.hideFlags = meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES) || meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS);
            this.hideAttributes = meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES);
            this.hideEnchants = meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS);
            this.customModelData = meta.hasCustomModelData() ? meta.getCustomModelData() : 0;
            this.enchanted = meta.hasEnchants() && meta.getEnchants().containsKey(getGlowEnchantment());
            if (meta.hasEnchants()) {
                this.enchants = new HashMap<>(meta.getEnchants());
            }
        }
    }

    public XMaterial getMaterial() {
        return material;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public static boolean isValidConfigItem(Plugin plugin, ConfigManager config, String path) {
        return config.getConfig().isSet(path + ".material");
    }

    public static ItemStack getConfigItem(Plugin plugin, Player player, ConfigManager config, String path) {
        ItemBuilder itemBuilder = new ItemBuilder(plugin, config.getString(path + ".material"));
        if (itemBuilder.isNumber(config.getString(path + ".amount")))
            itemBuilder.setAmount(Integer.parseInt(ColorChat.convert(player, config.getString(path + ".amount"))));
        else itemBuilder.setAmount(config.getInt(path + ".amount"));
        itemBuilder.setName(ColorChat.convert(player, ColorChat.placeholder(player, config.getString(path + ".name"))));
        itemBuilder.setLore(config.getStringList(path + ".lore").stream().map(s -> ColorChat.convert(player, s)).collect(Collectors.toList()));
        if (itemBuilder.isBoolean(config.getString(path + ".enchanted")))
            itemBuilder.setEnchanted(Boolean.parseBoolean(config.getString(path + ".enchanted")));
        else itemBuilder.setEnchanted(config.getBoolean(path + ".enchanted"));
        if (itemBuilder.isBoolean(config.getString(path + ".hideFlags")))
            itemBuilder.setHideFlags(Boolean.parseBoolean(config.getString(path + ".hideFlags")));
        else itemBuilder.setHideFlags(config.getBoolean(path + ".hideFlags"));
        if (itemBuilder.isBoolean(config.getString(path + ".hideAttributes"))) itemBuilder.setHideAttributes(Boolean.parseBoolean(config.getString(path + ".hideAttributes")));
        else itemBuilder.setHideAttributes(config.getBoolean(path + ".hideAttributes"));
        if (itemBuilder.isBoolean(config.getString(path + ".hideEnchants"))) itemBuilder.setHideEnchants(Boolean.parseBoolean(config.getString(path + ".hideEnchants")));
        else itemBuilder.setHideEnchants(config.getBoolean(path + ".hideEnchants"));
        if (itemBuilder.isNumber(config.getString(path + ".customModelData"))) itemBuilder.setCustomModelData(Integer.parseInt(config.getString(path + ".customModelData")));
        else itemBuilder.setCustomModelData(config.getInt(path + ".customModelData"));
        if (config.getConfig().get(path + ".enchants") != null) {
            for (String enchant : config.getStringList(path + ".enchants")) {
                String[] split = enchant.split(":");
                if (split.length == 0) { Bukkit.getLogger().warning("[HypeAPI] Invalid enchant in config: " + enchant); continue; }
                if (split.length == 2) if (XEnchantment.matchXEnchantment(ColorChat.convert(player, split[0])).isPresent()) itemBuilder.addEnchant(XEnchantment.matchXEnchantment(ColorChat.convert(player, split[0])).get().getEnchant(), Integer.parseInt(ColorChat.convert(player, split[1])));
                if (split.length == 1) if (XEnchantment.matchXEnchantment(ColorChat.convert(player, split[0])).isPresent()) itemBuilder.addEnchant(XEnchantment.matchXEnchantment(ColorChat.convert(player, split[0])).get().getEnchant(), 1);
            }
        }
        if (config.getConfig().get("nbt_string") != null) {
            String nbtString = config.getString("nbt_string");
            if (nbtString.isEmpty()) return itemBuilder.build();
            String[] split = nbtString.split(":");
            if (split.length == 0) { Bukkit.getLogger().warning("[HypeAPI] Invalid nbt_string in config: " + nbtString); return itemBuilder.build(); }
            if (split.length == 2) itemBuilder.setString(ColorChat.convert(player, split[0]), ColorChat.convert(player, split[0]));
            if (split.length == 1) itemBuilder.setString(ColorChat.convert(player, split[0]), "");
        }
        if (config.getConfig().get("nbt_strings") != null){
            for (String s : config.getStringList("nbt_strings")) {
                String[] split = s.split(":");
                if (split.length == 0) { Bukkit.getLogger().warning("[HypeAPI] Invalid nbt string in config: " + s); continue; }
                if (split.length == 2) itemBuilder.setString(ColorChat.convert(player, split[0]), ColorChat.convert(player, split[0]));
                if (split.length == 1) itemBuilder.setString(ColorChat.convert(player, split[0]), "");
            }
        }
        if (config.getConfig().get("nbt_int") != null) {
            String nbtInt = config.getString("nbt_int");
            if (nbtInt.isEmpty()) return itemBuilder.build();
            String[] split = nbtInt.split(":");
            if (split.length == 0) { Bukkit.getLogger().warning("[HypeAPI] Invalid nbt_int in config: " + nbtInt); return itemBuilder.build(); }
            if (split.length == 2) itemBuilder.setInt(ColorChat.convert(player, split[0]), Integer.parseInt(ColorChat.convert(player, split[1])));
            if (split.length == 1) itemBuilder.setInt(ColorChat.convert(player, split[0]), 0);
        }
        if (config.getConfig().get("nbt_ints") != null) {
            for (String s : config.getStringList("nbt_ints")) {
                String[] split = s.split(":");
                if (split.length == 0) { Bukkit.getLogger().warning("[HypeAPI] Invalid nbt int in config: " + s); continue; }
                if (split.length == 2) itemBuilder.setInt(ColorChat.convert(player, split[0]), Integer.parseInt(ColorChat.convert(player, split[0])));
                if (split.length == 1) itemBuilder.setInt(ColorChat.convert(player, split[0]), 0);
            }
        }
        return itemBuilder.build();
    }

    public ItemStack build(){
        if (amount != 0) itemStack.setAmount(amount);
        if(itemStack.getItemMeta() == null) return itemStack;
        ItemMeta meta = itemStack.getItemMeta();
        if(name != null) meta.setDisplayName(name);
        if(lore != null) meta.setLore(lore);
        if (enchanted) meta.addEnchant(getGlowEnchantment(), 1, true);
        if (hideFlags) meta.addItemFlags(ItemFlag.values());
        if (hideAttributes) meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if (hideEnchants) meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        if (enchants != null && !enchants.isEmpty())
            enchants.keySet().forEach(enchant -> meta.addEnchant(enchant, enchants.get(enchant), true));
        if (customModelData != 0) meta.setCustomModelData(customModelData);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private Enchantment getGlowEnchantment(){
        if (plugin == null) return Enchantment.DEPTH_STRIDER;
        if(VersionUtils.lowerThan112()) return GlowEnchantment.getEnchant();
        if (VersionUtils.getNMSVersion().equals("v1_12_R1")) return me.doublenico.hypeapi.v1_12_R1.GlowEnchantment.getEnchant();
        return me.doublenico.hypeapi.v1_18_R2.GlowEnchantment.getEnchant(plugin);
    }

    public int getAmount() {
        return amount;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getLore() {
        return lore;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setLore(String... lore){
        this.lore = Arrays.asList(lore);
        return this;
    }

    public boolean isEnchanted() {
        return enchanted;
    }

    public ItemBuilder setEnchanted(boolean enchanted) {
        this.enchanted = enchanted;
        return this;
    }

    public boolean isHideFlags() {
        return hideFlags;
    }

    public ItemBuilder setHideFlags(boolean hideFlags) {
        this.hideFlags = hideFlags;
        return this;
    }

    public boolean isHideAttributes() {
        return hideAttributes;
    }

    public ItemBuilder setHideAttributes(boolean hideAttributes) {
        this.hideAttributes = hideAttributes;
        return this;
    }

    public boolean isHideEnchants() {
        return hideEnchants;
    }

    public ItemBuilder setHideEnchants(boolean hideEnchants) {
        this.hideEnchants = hideEnchants;
        return this;
    }

    public HashMap<Enchantment, Integer> getEnchants(){
        return enchants;
    }

    public ItemBuilder setEnchants(HashMap<Enchantment, Integer> enchants){
        this.enchants = enchants;
        return this;
    }

    public void clearEnchants() {
        if (enchants != null) enchants.clear();
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level){
        if(enchants == null) enchants = new HashMap<>();
        enchants.put(enchantment, level);
        return this;
    }

    public boolean hasEnchant(Enchantment enchantment, int level){
        if(enchants == null) return false;
        return enchants.containsKey(enchantment) && enchants.get(enchantment) == level;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment){
        if(enchants == null) return this;
        enchants.remove(enchantment);
        return this;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment, int level){
        if(enchants == null) return this;
        if(!hasEnchant(enchantment, level)) return this;
        enchants.remove(enchantment);
        return this;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public ItemBuilder setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    private ItemNBT getNBT(){
        return new ItemNBT(this);
    }

    public ItemBuilder setString(String key, String value){
        getNBT().setString(key, value);
        return this;
    }

    public ItemBuilder setString(Plugin plugin, String key, String value){
        getNBT().setString(plugin, key, value);
        return this;
    }

    public ItemBuilder setString(NamespacedKey namespacedKey, String key, String value) {
        getNBT().setString(namespacedKey, key, value);
        return this;
    }

    public ItemBuilder setBoolean(String key, boolean value){
        getNBT().setBoolean(key, value);
        return this;
    }

    public ItemBuilder setBoolean(Plugin plugin, String key, boolean value){
        getNBT().setBoolean(plugin, key, value);
        return this;
    }

    public ItemBuilder setBoolean(NamespacedKey namespacedKey, String key, boolean value) {
        getNBT().setBoolean(namespacedKey, key, value);
        return this;
    }

    public ItemBuilder setInt(String key, int value){
        getNBT().setInt(key, value);
        return this;
    }

    public ItemBuilder setInt(Plugin plugin, String key, int value){
        getNBT().setInt(plugin, key, value);
        return this;
    }

    public ItemBuilder setInt(NamespacedKey namespacedKey, String key, int value) {
        getNBT().setInt(namespacedKey, key, value);
        return this;
    }

    public ItemBuilder setDouble(String key, double value){
        getNBT().setDouble(key, value);
        return this;
    }

    public ItemBuilder setDouble(Plugin plugin, String key, double value){
        getNBT().setDouble(plugin, key, value);
        return this;
    }

    public ItemBuilder setDouble(NamespacedKey namespacedKey, String key, double value) {
        getNBT().setDouble(namespacedKey, key, value);
        return this;
    }

    public ItemBuilder setLong(String key, long value){
        getNBT().setLong(key, value);
        return this;
    }

    public ItemBuilder setLong(Plugin plugin, String key, long value){
        getNBT().setLong(plugin, key, value);
        return this;
    }

    public ItemBuilder setLong(NamespacedKey namespacedKey, String key, long value) {
        getNBT().setLong(namespacedKey, key, value);
        return this;
    }

    public ItemBuilder setFloat(String key, float value){
        getNBT().setFloat(key, value);
        return this;
    }

    public ItemBuilder setFloat(Plugin plugin, String key, float value){
        getNBT().setFloat(plugin, key, value);
        return this;
    }

    public ItemBuilder setFloat(NamespacedKey namespacedKey, String key, float value) {
        getNBT().setFloat(namespacedKey, key, value);
        return this;
    }

    public String getString(String key){
        return getNBT().getString(key);
    }

    public String getString(Plugin plugin, String key){
        return getNBT().getString(plugin, key);
    }

    public String getString(NamespacedKey namespacedKey, String key){
        return getNBT().getString(namespacedKey, key);
    }

    public boolean getBoolean(String key){
        return getNBT().getBoolean(key);
    }

    public boolean getBoolean(Plugin plugin, String key){
        return getNBT().getBoolean(plugin, key);
    }

    public boolean getBoolean(NamespacedKey namespacedKey, String key){
        return getNBT().getBoolean(namespacedKey, key);
    }

    public int getInt(String key){
        return getNBT().getInt(key);
    }

    public int getInt(Plugin plugin, String key){
        return getNBT().getInt(plugin, key);
    }

    public int getInt(NamespacedKey namespacedKey, String key){
        return getNBT().getInt(namespacedKey, key);
    }

    public double getDouble(String key){
        return getNBT().getDouble(key);
    }

    public double getDouble(Plugin plugin, String key){
        return getNBT().getDouble(plugin, key);
    }

    public double getDouble(NamespacedKey namespacedKey, String key){
        return getNBT().getDouble(namespacedKey, key);
    }

    public long getLong(String key){
        return getNBT().getLong(key);
    }

    public long getLong(Plugin plugin, String key){
        return getNBT().getLong(plugin, key);
    }

    public long getLong(NamespacedKey namespacedKey, String key){
        return getNBT().getLong(namespacedKey, key);
    }

    public float getFloat(String key){
        return getNBT().getFloat(key);
    }

    public float getFloat(Plugin plugin, String key){
        return getNBT().getFloat(plugin, key);
    }

    public float getFloat(NamespacedKey namespacedKey, String key){
        return getNBT().getFloat(namespacedKey, key);
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBoolean(String str) {
        if (str == null || str.isEmpty()) return false;
        return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false");
    }

}

