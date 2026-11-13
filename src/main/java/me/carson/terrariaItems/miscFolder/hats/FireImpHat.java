package me.carson.terrariaItems.miscFolder.hats;

import me.carson.terrariaItems.miscFolder.Basic;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.plugin.Plugin;

public class FireImpHat extends Basic {

    public FireImpHat(Plugin plugin){
        super(plugin,"unobtainable.name","#96FF96", Material.LEATHER_HELMET,"fire_imp_hat","FireImpHat","unobtainable.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new FireImpHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(EquipmentSlot.HEAD);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

}
