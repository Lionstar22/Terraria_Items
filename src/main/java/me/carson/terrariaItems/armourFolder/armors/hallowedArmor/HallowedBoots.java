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

public class HallowedBoots extends Armor {

    public HallowedBoots(Plugin plugin){
        super(plugin,"Hallowed Boots","#FF96FF", Material.NETHERITE_BOOTS,"hallowed_boots","hallowed_armor", EquipmentSlot.FEET,"HallowedBoots",new ArrayList<>(List.of(ChatColor.GRAY+"Grants dodge every 10 seconds",ChatColor.GRAY+"50% Damage Increase",ChatColor.GRAY+"20% Movement Speed Increase")));
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusDamage(player.getUniqueId(),0.05);
        player.setWalkSpeed(player.getWalkSpeed()+0.05f);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusDamage(player.getUniqueId(),0.05);
        player.setWalkSpeed(Math.max(player.getWalkSpeed() - 0.05f, 0.2f));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new HallowedBoots(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"hallowed_boots_armor"),15.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS,new AttributeModifier(new NamespacedKey(plugin,"hallowed_boots_armor_toughness"),5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(plugin,"hallowed_boots_k_resistance"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

}
