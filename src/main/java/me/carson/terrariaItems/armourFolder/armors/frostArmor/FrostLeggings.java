package me.carson.terrariaItems.armourFolder.armors.frostArmor;

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

public class FrostLeggings extends Armor {

    public FrostLeggings(Plugin plugin){
        super(plugin,"Frost Leggings","#FF96FF", Material.NETHERITE_LEGGINGS,"frost_leggings","frost_armor",EquipmentSlot.LEGS,"FrostLeggings",new ArrayList<>(List.of(ChatColor.GRAY+"Increases melee and ranged damage by 10%")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FrostLeggings(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"frost_leggings_armor"),8.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
        meta.addEnchant(Enchantment.PROTECTION,3,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMelee(player.getUniqueId(),0.1);
        playerInstance.addBonusRanged(player.getUniqueId(),0.1);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.1);
        playerInstance.subtractBonusRanged(player.getUniqueId(),0.1);
    }
}
