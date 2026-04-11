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

public class UndeadMinerHat extends Basic {

    public UndeadMinerHat(Plugin plugin){
        super(plugin,"undead_miner_hat.name","#9696FF", Material.GOLDEN_HELMET,"undead_miner_hat","UndeadMinerHat","undead_miner_hat.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new UndeadMinerHat(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        EquippableComponent equip= meta.getEquippable();
        equip.setSlot(EquipmentSlot.HEAD);
        meta.setEquippable(equip);
        item.setItemMeta(meta);
        return item;
    }

}
