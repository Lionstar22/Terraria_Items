package me.carson.terrariaItems.armourFolder.armors.hallowedArmor;

import me.carson.terrariaItems.armourFolder.Armor;
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

public class HallowedLeggings extends Armor {

    public HallowedLeggings(Plugin plugin){
        super(plugin,"Hallowed Leggings","#FF96FF", Material.NETHERITE_LEGGINGS,"hallowed_leggings","hallowed_armor", EquipmentSlot.LEGS,"HallowedLeggings",new ArrayList<>(List.of(ChatColor.GRAY+"Grants dodge every 10 seconds",ChatColor.GRAY+"50% Damage Increase",ChatColor.GRAY+"20% Movement Speed Increase")));
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new HallowedLeggings(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_leggings_armor"),20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(plugin,"hallowed_leggings_armor_toughness"),5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(plugin,"hallowed_leggings_k_resistance"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

}
