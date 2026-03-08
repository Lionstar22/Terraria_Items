package me.carson.terrariaItems.miscFolder.BasicItems;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SkeletonArcherHat extends Basic {

    public SkeletonArcherHat(Plugin plugin){
        super(plugin,"SkeletonArcherHat","#FFFFFF", Material.IRON_HELMET,"skeleton_archer_hat","SkeletonArcherHat",new ArrayList<>(List.of("How did you get this?")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new SkeletonArcherHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(EquipmentSlot.HEAD);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

}
