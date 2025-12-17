package me.carson.terrariaItems.armourFolder.armors.cactusArmor;

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

public class CactusBoots extends Armor {

    public CactusBoots(Plugin plugin){
        super(plugin,"Cactus Boots","#FFFFFF", Material.LEATHER_BOOTS,"cactus_boots","cactus_armor", EquipmentSlot.FEET,"CactusBoots",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus",ChatColor.GRAY+"Attackers take damage from the cactus spines",ChatColor.GRAY+"+1 Armor")));
    }

    @Override
    public void activateArmorEffect(Player player) {
    }

    @Override
    public void deactivateArmorEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new CactusBoots(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addAttributeModifier(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(plugin,"cactus_boots_armor"),1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        item.setItemMeta(meta);
        return item;
    }

}
