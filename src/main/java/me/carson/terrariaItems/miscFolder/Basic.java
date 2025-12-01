package me.carson.terrariaItems.miscFolder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Basic {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final org.bukkit.Material baseMaterial;
    protected final String texture;
    protected final String id;
    protected final ArrayList<String> lore;
    private final NamespacedKey customItemKey;
    private final NamespacedKey unplaceableKey;


    public Basic(Plugin plugin, String name, String rarity, org.bukkit.Material baseMaterial, String texture, String id, ArrayList<String> lore){
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
        ItemStack basic = new ItemStack(baseMaterial);
        ItemMeta meta = basic.getItemMeta();
        meta.setUnbreakable(true);
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria",texture));
        meta.getPersistentDataContainer().set(customItemKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(unplaceableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(1));
        basic.setItemMeta(meta);
        return basic;
    }

}
