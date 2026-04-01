package me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee;

import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.Sword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SlapHand extends Sword {

    public SlapHand(Plugin plugin) {
        super(plugin,"Slap Hand","#FF9696", Material.NETHERITE_SWORD,"slap_hand","SlapHand",0,0,0,0,0, new ArrayList<>(List.of(ChatColor.GRAY+"BEGONE",ChatColor.GRAY+"9 Damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new SlapHand(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_KNOCKBACK,new AttributeModifier(new NamespacedKey(plugin,"knockback"),30.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),8, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.MAINHAND));
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
