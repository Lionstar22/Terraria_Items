package me.carson.terrariaItems.miscFolder.hats;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SandElementalHat extends Basic {

    public SandElementalHat(Plugin plugin){
        super(plugin,"Sand Elemental Hat","#96FF96", Material.LEATHER_HELMET,"sand_elemental_hat","SandElementalHat",new ArrayList<>(List.of(ChatColor.GRAY+"How did you get this?")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new SandElementalHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(EquipmentSlot.HEAD);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

}
