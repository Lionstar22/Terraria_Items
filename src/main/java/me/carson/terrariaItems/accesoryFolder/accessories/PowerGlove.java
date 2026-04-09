package me.carson.terrariaItems.accesoryFolder.accessories;


import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class PowerGlove extends Accessory  {

    public PowerGlove(Plugin plugin){
        super(plugin,"Power Glove","#FF96FF",Material.NETHER_BRICK,"power_glove","PowerGlove",new ArrayList<>(List.of(ChatColor.GRAY+"+1.5 increased attack knockback",ChatColor.GRAY+"+0.25 increased attack speed",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ATTACK_KNOCKBACK).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"power_glove_knockback"),1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_KNOCKBACK).addModifier(new AttributeModifier(new NamespacedKey(plugin,"power_glove_knockback"),1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"power_glove_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"power_glove_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"power_glove_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_KNOCKBACK).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"power_glove_knockback"),1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new PowerGlove(plugin).createItem();
    }

}
