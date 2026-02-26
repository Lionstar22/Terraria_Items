package me.carson.terrariaItems.accesoryFolder;

import me.carson.terrariaItems.listenersHandler.PlayerDataHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Accessory {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final Material baseMaterial;
    protected final String texture;
    protected final String id;
    protected final ArrayList<String> lore;
    private final NamespacedKey activeKey;
    private final NamespacedKey customItemKey;
    private final NamespacedKey unplaceableKey;
    private final PlayerDataHandler playerDataInstance=PlayerDataHandler.getInstance();


    public Accessory(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, ArrayList<String> lore){
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.id = id;
        customItemKey=new NamespacedKey(plugin, "customItem");
        unplaceableKey=new NamespacedKey(plugin, "unplaceable");
        activeKey = new NamespacedKey(plugin,"activekey");
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(baseMaterial);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria",texture));
        meta.getPersistentDataContainer().set(customItemKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(unplaceableKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(activeKey, PersistentDataType.INTEGER, 0);
        meta.setMaxStackSize(Integer.valueOf(1));
        item.setItemMeta(meta);
        return item;
    }

    public boolean isThisItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        String storedId = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return id.equals(storedId);
    }

    public boolean isActivated(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        return data.getOrDefault(activeKey, PersistentDataType.INTEGER, 0) == 1;
    }

    public boolean hasItem(Player player){
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        for(ItemStack item:playerDataInstance.getInventory(player.getUniqueId())){
            if(item!=null) {
                if (Objects.equals(item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING), id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ItemStack getItem(Player player){
        if(!player.getInventory().contains(baseMaterial)){return null;}
        for(ItemStack itemInv : player.getInventory().getContents()){
            if(isThisItem(itemInv)){return itemInv;}
        }
        return null;
    }

    public abstract void activateEffect(Player player);

    public abstract void deactivateEffect(Player player);
}
