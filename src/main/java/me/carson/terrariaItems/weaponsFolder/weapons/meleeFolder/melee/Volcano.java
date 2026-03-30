package me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee;

import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.Sword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.attribute.Attribute;

import java.util.ArrayList;
import java.util.List;

public class Volcano extends Sword {

    public Volcano(Plugin plugin) {
        super(plugin,"Volcano","#FFC896", Material.NETHERITE_SWORD,"volcano","Volcano",0,0,0,0,0, new ArrayList<>(List.of(ChatColor.GRAY+"It's made out of fire!",ChatColor.GRAY+"15 Damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new Volcano(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addEnchant(Enchantment.FIRE_ASPECT,2,true);
        meta.setEnchantmentGlintOverride(false);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(plugin,"speed"),-3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.ENTITY_INTERACTION_RANGE, new AttributeModifier(new NamespacedKey(plugin,"range"),1.0, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),14, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.MAINHAND));
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
