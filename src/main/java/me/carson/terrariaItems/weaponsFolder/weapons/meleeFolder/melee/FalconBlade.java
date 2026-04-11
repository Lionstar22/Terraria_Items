package me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee;

import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.Sword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FalconBlade extends Sword {

    public FalconBlade(Plugin plugin) {
        super(plugin,"falcon_blade.name","#FF9696", Material.IRON_SWORD,"falcon_blade","FalconBlade",0,0,0,0,0,"falcon_blade.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FalconBlade(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(plugin,"speed"),1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.MAINHAND));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void leftActivate(Player player) {
        player.getWorld().playSound(player.getLocation(), "terraria:sword_use", 1.0F, 1.0F);
    }

    @Override
    public void rightActivate(Player player) {

    }

}
