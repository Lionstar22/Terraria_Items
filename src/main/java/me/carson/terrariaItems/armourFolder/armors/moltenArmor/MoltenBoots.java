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

public class MoltenBoots extends Armor {

    public MoltenBoots(Plugin plugin){
        super(plugin,"Molten Boots","#FFC896", Material.NETHERITE_BOOTS,"molten_boots","molten_armor", EquipmentSlot.FEET,"MoltenBoots",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Fire Resistance, 25% Increased Damage, Sets Enemies on Fire")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new MoltenBoots(plugin).createItem();
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
