package me.carson.terrariaItems.armourFolder.armors.frostArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FrostHelmet extends Armor {

    public FrostHelmet(Plugin plugin){
        super(plugin,"Frost Helmet","#FF96FF", Material.NETHERITE_HELMET,"frost_helmet","frost_armor", EquipmentSlot.HEAD,"FrostHelmet",new ArrayList<>(List.of(ChatColor.GRAY+"16% increased melee and ranged damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item= new FrostHelmet(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,5,false);
        meta.setEnchantmentGlintOverride(false);
        EquippableComponent equip= meta.getEquippable();
        equip.setModel(null);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMelee(player.getUniqueId(),0.16);
        playerInstance.addBonusRanged(player.getUniqueId(),0.16);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.16);
        playerInstance.subtractBonusRanged(player.getUniqueId(),0.16);
    }

}
