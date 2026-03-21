package me.carson.terrariaItems.armourFolder.armors.runeWizardArmor;

import me.carson.terrariaItems.armourFolder.Armor;
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

public class RuneWizardChestplate extends Armor {

    public RuneWizardChestplate(Plugin plugin){
        super(plugin,"Rune Wizard Chestplate","#FFFFFF", Material.LEATHER_CHESTPLATE,"cactus_chestplate","rune_wizard_armor", EquipmentSlot.CHEST,"RuneWizardChestplate",new ArrayList<>(List.of(ChatColor.GRAY+"How did you get this?")));
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new RuneWizardChestplate(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"rune_wizard_chestplate_armor"),0.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        item.setItemMeta(meta);
        return item;
    }

}
