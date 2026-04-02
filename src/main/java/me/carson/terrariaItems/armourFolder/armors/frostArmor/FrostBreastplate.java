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

public class FrostBreastplate extends Armor {

    public FrostBreastplate(Plugin plugin){
        super(plugin,"Frost Breastplate","#FF96FF", Material.NETHERITE_CHESTPLATE,"frost_breastplate","frost_armor",EquipmentSlot.CHEST,"FrostBreastplate",new ArrayList<>(List.of(ChatColor.GRAY+"11% increased critical strike chance",ChatColor.GRAY+"+10 Armor")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FrostBreastplate(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"frost_breastplate_armor"),10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        meta.addEnchant(Enchantment.PROTECTION,3,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addCritChance(player.getUniqueId(),0.11);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractCritChance(player.getUniqueId(),0.11);
    }
}
