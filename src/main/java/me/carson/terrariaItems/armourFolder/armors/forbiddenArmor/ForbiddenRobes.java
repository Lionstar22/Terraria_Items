package me.carson.terrariaItems.armourFolder.armors.forbiddenArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.MoltenChestplate;
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

public class ForbiddenRobes extends Armor {

    public ForbiddenRobes(Plugin plugin){
        super(plugin,"Forbidden Robes","#FF96FF", Material.NETHERITE_CHESTPLATE,"forbidden_robes","forbidden_armor",EquipmentSlot.CHEST,"ForbiddenRobes",new ArrayList<>(List.of(ChatColor.GRAY+"Increases maximum mana by 40")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ForbiddenRobes(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,1,false);
        meta.setEnchantmentGlintOverride(false);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void activateArmorEffect(Player player) {
        playerInstance.addExtraMana(player.getUniqueId(),40);
    }

    @Override
    public void deactivateArmorEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),40);
    }
}
