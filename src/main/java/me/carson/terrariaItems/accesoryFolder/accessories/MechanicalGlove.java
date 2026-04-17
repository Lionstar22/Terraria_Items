package me.carson.terrariaItems.accesoryFolder.accessories;


import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class MechanicalGlove extends Accessory  {

    public MechanicalGlove(Plugin plugin){
        super(plugin,"mechanical_glove.name","#D2A0FF",Material.NETHER_BRICK,"mechanical_glove","MechanicalGlove","mechanical_glove.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.getAttribute(Attribute.ATTACK_KNOCKBACK).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"mechanical_glove_knockback"),1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_KNOCKBACK).addModifier(new AttributeModifier(new NamespacedKey(plugin,"mechanical_glove_knockback"),1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"mechanical_glove_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"mechanical_glove_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        playerInstance.addBonusMelee(player.getUniqueId(),0.15);
    }

    @Override
    public void deactivateEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"mechanical_glove_attack_speed"),0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_KNOCKBACK).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"mechanical_glove_knockback"),1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.15);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new MechanicalGlove(plugin).createItem();
    }

}
