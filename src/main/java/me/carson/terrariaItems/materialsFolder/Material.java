package me.carson.terrariaItems.materialsFolder;

import me.carson.terrariaItems.TILangManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Material {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final org.bukkit.Material baseMaterial;
    protected final String texture;
    public final String id;
    protected final String lore;
    private final NamespacedKey customItemKey;
    private final NamespacedKey unplaceableKey;
    public final TILangManager lang =TILangManager.getInstance();


    public Material(Plugin plugin, String name, String rarity, org.bukkit.Material baseMaterial, String texture, String id, String lore){
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.id = id;
        customItemKey=new NamespacedKey(plugin, "customItem");
        unplaceableKey=new NamespacedKey(plugin, "unplaceable");
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack material = new ItemStack(baseMaterial);
        ItemMeta meta = material.getItemMeta();
        meta.setDisplayName(net.md_5.bungee.api.ChatColor.of(rarity)+lang.get("materials",name));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(new ArrayList<>(lang.getList("materials",lore)));
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria",texture));
        meta.getPersistentDataContainer().set(customItemKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(unplaceableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(64));
        material.setItemMeta(meta);
        return material;
    }

    public boolean isThisItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        String storedId = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return id.equals(storedId);
    }


}
