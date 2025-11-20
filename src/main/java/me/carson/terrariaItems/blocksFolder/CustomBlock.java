package me.carson.terrariaItems.blocksFolder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public abstract class CustomBlock {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final Material baseMaterial;
    protected final String id;
    protected final ArrayList<String> lore;
    private final NamespacedKey uncraftableKey;
    private final NamespacedKey customCraftableKey;

    protected CustomBlock(Plugin plugin, String name, String rarity, Material baseMaterial, String id, ArrayList<String> lore) {
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.id = id;
        this.lore = lore;
        uncraftableKey=new NamespacedKey(plugin, "uncraftable");
        customCraftableKey=new NamespacedKey(plugin, "customCraftable");
    }

    public ItemStack createItem(){
        ItemStack block =new ItemStack(baseMaterial);
        ItemMeta meta = block.getItemMeta();
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(customCraftableKey, PersistentDataType.BYTE, (byte) 1);
        block.setItemMeta(meta);
        return block;
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
