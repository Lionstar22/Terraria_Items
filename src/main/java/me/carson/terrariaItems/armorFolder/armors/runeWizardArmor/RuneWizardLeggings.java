package me.carson.terrariaItems.armorFolder.armors.runeWizardArmor;

import me.carson.terrariaItems.armorFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class RuneWizardLeggings extends Armor {

    public RuneWizardLeggings(Plugin plugin){
        super(plugin,"unobtainable.name","#FFFFFF", Material.LEATHER_LEGGINGS,"cactus_leggings","rune_wizard_armor", EquipmentSlot.LEGS,"RuneWizardLeggings","unobtainable.lore");
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new RuneWizardLeggings(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"rune_wizard_leggings_armor"),0.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
        item.setItemMeta(meta);
        return item;
    }

}
