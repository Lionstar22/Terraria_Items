package me.carson.terrariaItems.armorFolder.armors.cactusArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CactusChestplate extends Armor {

    public CactusChestplate(Plugin plugin){
        super(plugin,"cactus_chestplate.name","#FFFFFF", Material.LEATHER_CHESTPLATE,"cactus_chestplate","cactus_armor", EquipmentSlot.CHEST,"CactusChestplate","cactus_chestplate.lore");
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new CactusChestplate(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"cactus_chestplate_armor"),2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        item.setItemMeta(meta);
        return item;
    }

}
