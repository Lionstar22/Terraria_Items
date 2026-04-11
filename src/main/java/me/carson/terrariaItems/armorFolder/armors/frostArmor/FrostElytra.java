package me.carson.terrariaItems.armorFolder.armors.frostArmor;

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

public class FrostElytra extends Armor {

    public FrostElytra(Plugin plugin){
        super(plugin,"frost_elytra.name","#FF96FF", Material.ELYTRA,"frost_elytra_sprite","frost_elytra_armor", EquipmentSlot.CHEST,"FrostElytra","frost_elytra.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FrostElytra(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_leggings_armor"),5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addCritChance(player.getUniqueId(),0.05);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractCritChance(player.getUniqueId(),0.05);
    }

}
