package me.carson.terrariaItems.armourFolder.armors.moltenArmor;

import me.carson.terrariaItems.armourFolder.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MoltenLeggings extends Armor {

    public MoltenLeggings(Plugin plugin){
        super(plugin,"Molten Leggings","#FFC896", Material.NETHERITE_LEGGINGS,"molten_leggings","molten_armor", EquipmentSlot.LEGS,"MoltenLeggings",new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Fire Resistance, 25% Increased Melee Damage, Fire Ascpect")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MoltenLeggings(plugin).createItem();
    }

    @Override
    public void activateArmorEffect(Player player) {

    }

    @Override
    public void deactivateArmorEffect(Player player) {

    }
}
