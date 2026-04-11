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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ObsidianSkull extends Accessory implements Listener {

    public ObsidianSkull(Plugin plugin){
        super(plugin,"obsidian_skull.name","#96FF96", Material.OBSIDIAN,"obsidian_skull","ObsidianSkull","obsidian_skull.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"obsidian_skull_armor"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ARMOR).addModifier(new AttributeModifier(new NamespacedKey(plugin,"obsidian_skull_armor"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"obsidian_skull_armor"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianSkull(plugin).createItem();
    }

}
