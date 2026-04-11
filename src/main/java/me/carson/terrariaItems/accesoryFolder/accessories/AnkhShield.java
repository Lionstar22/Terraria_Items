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

public class AnkhShield extends Accessory implements Listener {

    public AnkhShield(Plugin plugin){
        super(plugin,"ankh_shield.name","#96FF0A", Material.NETHER_BRICK,"ankh_shield","AnkhShield","ankh_shield.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"ankh_shield_armor"),4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ARMOR).addModifier(new AttributeModifier(new NamespacedKey(plugin,"ankh_shield_armor"),4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ARMOR).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"ankh_shield_armor"),4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new AnkhShield(plugin).createItem();
    }
}
