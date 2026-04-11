package me.carson.terrariaItems.armorFolder.armors.hallowedArmor;

import me.carson.terrariaItems.armorFolder.Armor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HallowedMask extends Armor{

    public HallowedMask(Plugin plugin){
        super(plugin,"hallowed_mask.name","#FF96FF", Material.NETHERITE_HELMET,"hallowed_mask","hallowed_armor", EquipmentSlot.HEAD,"HallowedMask","hallowed_mask.lore");
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMelee(player.getUniqueId(),0.1);
        playerInstance.addCritChance(player.getUniqueId(),0.1);
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"hallowed_mask_attack_speed"),0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"hallowed_mask_attack_speed"),0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.1);
        playerInstance.subtractCritChance(player.getUniqueId(),0.1);
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"feral_claws_attack_speed"),0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack helm=new HallowedMask(plugin).createItem();
        ItemMeta meta=helm.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_mask_armor"),20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(plugin,"hallowed_mask_armor_toughness"),5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(plugin,"hallowed_mask_k_resistance"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addEnchant(Enchantment.PROTECTION,5,true);
        meta.setEnchantmentGlintOverride(false);
        helm.setItemMeta(meta);
        return helm;
    }

}
