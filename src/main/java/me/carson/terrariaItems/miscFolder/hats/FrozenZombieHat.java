package me.carson.terrariaItems.miscFolder.hats;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class FrozenZombieHat extends Basic {

    public FrozenZombieHat(Plugin plugin){
        super(plugin,"Frozen Zombie Hat","#FFFFFF", Material.SNOWBALL,"frozen_zombie_hat","FrozenZombieHat",new ArrayList<>(List.of("How did you get this?")));
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FrozenZombieHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(EquipmentSlot.HEAD);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }


}
