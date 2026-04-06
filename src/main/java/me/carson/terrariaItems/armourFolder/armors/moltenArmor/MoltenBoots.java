package me.carson.terrariaItems.armourFolder.armors.moltenArmor;

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

public class MoltenBoots extends Armor {

    public MoltenBoots(Plugin plugin){
        super(plugin,"Molten Boots","#FFC896", Material.NETHERITE_BOOTS,"molten_boots","molten_armor", EquipmentSlot.FEET,"MoltenBoots",new ArrayList<>(List.of(ChatColor.GRAY+"+.15 increased attack speed",ChatColor.GRAY+"Set Bonus: Grants immunity to fire",ChatColor.GRAY+"+3 Armor")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new MoltenBoots(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,1,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"molten_boots_attack_speed"),0.15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        player.getAttribute(Attribute.ATTACK_SPEED).addModifier(new AttributeModifier(new NamespacedKey(plugin,"molten_boots_attack_speed"),0.15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_SPEED).removeModifier(new AttributeModifier(new NamespacedKey(plugin,"molten_boots_attack_speed"),0.15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
    }
}
