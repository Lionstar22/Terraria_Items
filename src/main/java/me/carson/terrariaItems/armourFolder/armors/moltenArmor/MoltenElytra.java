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

public class MoltenElytra extends Armor {

    public MoltenElytra(Plugin plugin){
        super(plugin,"Molten Elytra","#FFC896", Material.ELYTRA,"molten_elytra","molten_armor", EquipmentSlot.CHEST,"MoltenElytra",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Grants immunity to fire")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new MoltenElytra(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,1,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {

    }

}
