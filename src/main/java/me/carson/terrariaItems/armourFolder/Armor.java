package me.carson.terrariaItems.armourFolder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;


public abstract class Armor {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final Material baseMaterial;
    protected final String texture;
    protected final String model;
    protected final EquipmentSlot slot;
    protected final String id;
    protected final ArrayList<String> lore;
    private final NamespacedKey customItemKey;


    public Armor(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String model, EquipmentSlot slot, String id, ArrayList<String> lore) {
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.model = model;
        this.slot = slot;
        this.id = id;
        customItemKey=new NamespacedKey(plugin, "customItem");
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack armor = new ItemStack(baseMaterial);
        ItemMeta meta = armor.getItemMeta();
        meta.setDisplayName(net.md_5.bungee.api.ChatColor.of(rarity)+name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        meta.setUnbreakable(true);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria", texture));
        meta.getPersistentDataContainer().set(customItemKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(1));
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(slot);
        equip.setModel(new NamespacedKey("terraria", model));
        meta.setEquippable(equip);
        armor.setItemMeta(meta);
        return armor;
    }

    public boolean isThisItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        String storedId = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return id.equals(storedId);
    }

    public Boolean hasSetBonus(Player player) {
        PlayerInventory inv = player.getInventory();

        ItemStack helmet = inv.getHelmet();
        ItemStack chest = inv.getChestplate();
        ItemStack legs = inv.getLeggings();
        ItemStack boots = inv.getBoots();
        if(helmet==null||chest==null||legs==null||boots==null){return false;}
        ItemMeta hMeta = helmet.getItemMeta();
        ItemMeta cMeta = chest.getItemMeta();
        ItemMeta lMeta = legs.getItemMeta();
        ItemMeta bMeta = boots.getItemMeta();
        if(hMeta==null||cMeta==null||lMeta==null||bMeta==null){return false;}
        String hKey=hMeta.getEquippable().getModel().getKey();
        String cKey=cMeta.getEquippable().getModel().getKey();
        String lKey=lMeta.getEquippable().getModel().getKey();
        String bKey=bMeta.getEquippable().getModel().getKey();

        return hKey.equals(cKey)&&hKey.equals(lKey)&&hKey.equals(bKey);
    }

    public abstract void activateArmorEffect(Player player);

    public abstract void deactivateArmorEffect(Player player);
}