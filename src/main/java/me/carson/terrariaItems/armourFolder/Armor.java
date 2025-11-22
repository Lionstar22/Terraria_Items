package me.carson.terrariaItems.armourFolder;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Equippable;
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
    private final NamespacedKey uncraftableKey;
    private final NamespacedKey customCraftableKey;


    public Armor(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String model, EquipmentSlot slot, String id, ArrayList<String> lore) {
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.model = model;
        this.slot = slot;
        this.id = id;
        uncraftableKey = new NamespacedKey(plugin, "uncraftable");
        customCraftableKey=new NamespacedKey(plugin, "customCraftable");
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack armor = new ItemStack(baseMaterial);
        ItemMeta meta = armor.getItemMeta();
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        meta.setUnbreakable(true);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria", texture));
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(customCraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(1));
        armor.setItemMeta(meta);
        Equippable equippable = Equippable.equippable(slot).assetId(new NamespacedKey("terraria", model)).build();
        armor.setData(DataComponentTypes.EQUIPPABLE,equippable);
        armor.unsetData(DataComponentTypes.ENCHANTABLE);
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

        if (!helmet.hasData(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }
        if (!chest.hasData(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }
        if (!legs.hasData(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }
        if (!boots.hasData(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }

        Equippable eqHelmet = helmet.getData(DataComponentTypes.EQUIPPABLE);
        if (eqHelmet.assetId() == null) {
            return false;
        }
        var helmetAssetId = eqHelmet.assetId();
        String helmetKey = helmetAssetId.namespace();

        Equippable eqChest = chest.getData(DataComponentTypes.EQUIPPABLE);
        if (eqChest.assetId() == null) {
            return false;
        }
        var chestAssetId = eqChest.assetId();
        String chestKey = chestAssetId.namespace();

        Equippable eqLegs = legs.getData(DataComponentTypes.EQUIPPABLE);
        if (eqLegs.assetId() == null) {
            return false;
        }
        var legsAssetId = eqLegs.assetId();
        String legsKey = legsAssetId.namespace();

        Equippable eqBoots = boots.getData(DataComponentTypes.EQUIPPABLE);
        if (eqBoots.assetId() == null) {
            return false;
        }
        var bootsAssetId = eqBoots.assetId();
        String bootsKey = bootsAssetId.namespace();
        return helmetKey.equals(chestKey) && helmetKey.equals(legsKey) && helmetKey.equals(bootsKey);
    }

    public abstract void activateArmorEffect(Player player);

    public abstract void deactivateArmorEffect(Player player);
}