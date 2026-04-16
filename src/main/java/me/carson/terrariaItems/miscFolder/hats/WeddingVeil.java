package me.carson.terrariaItems.miscFolder.hats;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

public class WeddingVeil extends Basic {

    public WeddingVeil(Plugin plugin){
        super(plugin,"wedding_veil.name","#FFFFFF", Material.LEATHER_HELMET,"wedding_veil","WeddingVeil","wedding_veil.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new WeddingVeil(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(EquipmentSlot.HEAD);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

}
