package me.carson.terrariaItems.armourFolder.armors.forbiddenArmor;

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

public class ForbiddenElytra extends Armor {

    public ForbiddenElytra(Plugin plugin){
        super(plugin,"Forbidden Elytra","#FF96FF", Material.ELYTRA,"forbidden_elytra_sprite","forbidden_elytra_armor", EquipmentSlot.CHEST,"ForbiddenElytra",new ArrayList<>(List.of(ChatColor.GRAY+"")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ForbiddenElytra(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {

    }

}
