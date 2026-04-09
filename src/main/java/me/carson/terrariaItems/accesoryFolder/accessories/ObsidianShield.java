package me.carson.terrariaItems.accesoryFolder.accessories;


import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ObsidianShield extends Accessory implements Listener {

    public ObsidianShield(Plugin plugin){
        super(plugin,"Obsidian Shield","#FF9696", Material.NETHER_BRICK,"obsidian_shield","ObsidianShield",
                new ArrayList<>(List.of(
                        ChatColor.GRAY+"Grants immunity to knockback",
                        ChatColor.GRAY+"Grants immunity to fire damage",
                        ChatColor.GRAY+"+2 Armor",
                        ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"obsidian_shield_armor"),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ARMOR).addModifier(new AttributeModifier(new NamespacedKey(plugin,"obsidian_shield_armor"),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"obsidian_shield_armor"),2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianShield(plugin).createItem();
    }
}
