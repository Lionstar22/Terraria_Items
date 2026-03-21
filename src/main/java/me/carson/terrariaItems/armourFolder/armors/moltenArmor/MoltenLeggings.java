package me.carson.terrariaItems.armourFolder.armors.moltenArmor;

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

public class MoltenLeggings extends Armor {

    public MoltenLeggings(Plugin plugin){
        super(plugin,"Molten Leggings","#FFC896", Material.NETHERITE_LEGGINGS,"molten_leggings","molten_armor", EquipmentSlot.LEGS,"MoltenLeggings",new ArrayList<>(List.of(ChatColor.GRAY+"7% increased melee damage",ChatColor.GRAY+"Set Bonus: Grants immunity to fire")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new MoltenLeggings(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,1,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addBonusMelee(player.getUniqueId(),0.07);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractBonusMelee(player.getUniqueId(),0.07);
    }
}
