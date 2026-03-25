package me.carson.terrariaItems.armourFolder.armors.frostArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FrostBoots extends Armor {

    public FrostBoots(Plugin plugin){
        super(plugin,"Frost Boots","#FF96FF", Material.NETHERITE_BOOTS,"frost_boots","frost_armor",EquipmentSlot.FEET,"FrostBoots",new ArrayList<>(List.of(ChatColor.GRAY+"10% increased movement speed",ChatColor.GRAY+"10% increased melee speed")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FrostBoots(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,5,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        player.setWalkSpeed(player.getWalkSpeed()+0.02f);
        player.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(player.getAttribute(Attribute.ATTACK_SPEED).getValue()+0.4);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        player.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(Math.max(player.getAttribute(Attribute.ATTACK_SPEED).getValue()-0.4,4));
        player.setWalkSpeed(Math.max(player.getWalkSpeed() - 0.02f, 0.2f));
    }
}
