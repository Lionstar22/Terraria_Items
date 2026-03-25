package me.carson.terrariaItems.armourFolder.armors.frostArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FrostBreastplate extends Armor {

    public FrostBreastplate(Plugin plugin){
        super(plugin,"Frost Breastplate","#FF96FF", Material.NETHERITE_CHESTPLATE,"frost_breastplate","frost_armor",EquipmentSlot.CHEST,"FrostBreastplate",new ArrayList<>(List.of(ChatColor.GRAY+"11% increased critical strike chance")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FrostBreastplate(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,5,false);
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
