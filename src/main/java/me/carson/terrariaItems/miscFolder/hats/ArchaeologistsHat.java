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

public class ArchaeologistsHat extends Basic {

    public ArchaeologistsHat(Plugin plugin){
        super(plugin,"Archaeologist's Hat","#FFFFFF", Material.LEATHER_HELMET,"archaeologists_hat","ArchaeologistsHat",new ArrayList<>(List.of(ChatColor.GRAY+"That belongs in a museum!")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new ArchaeologistsHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(EquipmentSlot.HEAD);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

}
