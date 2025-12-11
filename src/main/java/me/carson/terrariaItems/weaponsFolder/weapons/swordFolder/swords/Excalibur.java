package me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords;

import me.carson.terrariaItems.weaponsFolder.Weapon;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.Sword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Excalibur extends Sword {

    public Excalibur(Plugin plugin) {
        super(plugin,"Excalibur","#FF96FF", Material.NETHERITE_SWORD,"excalibur","Excalibur",0,0,0,0,0, new ArrayList<>(List.of(ChatColor.GRAY+"The holy sword of legend",ChatColor.GRAY+"20 Damage, +1 Range")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new Excalibur(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.addEnchant(Enchantment.KNOCKBACK,1,true);
        meta.addEnchant(Enchantment.SWEEPING_EDGE,2,true);
        meta.setEnchantmentGlintOverride(false);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(plugin,"speed"),-1.5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.ENTITY_INTERACTION_RANGE, new AttributeModifier(new NamespacedKey(plugin,"range"),1.0, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(plugin,"attack"),19, AttributeModifier.Operation.ADD_NUMBER));
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
