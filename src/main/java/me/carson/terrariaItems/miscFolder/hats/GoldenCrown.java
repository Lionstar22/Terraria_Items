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

public class GoldenCrown extends Basic {

    public GoldenCrown(Plugin plugin){
        super(plugin,"golden_crown.name","#FFFFFF", Material.GOLDEN_HELMET,"golden_crown","GoldenCrown","golden_crown.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new GoldenCrown(plugin).createItem();
        ItemMeta  meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(EquipmentSlot.HEAD);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

}
