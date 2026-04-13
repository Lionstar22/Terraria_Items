package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LifeCrystal extends Tool {

    public LifeCrystal(Plugin plugin){
        super(plugin,"life_crystal.name","#96FF96", Material.PINK_DYE,"life_crystal","LifeCrystal",10,"life_crystal.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(player.getAttribute(Attribute.MAX_HEALTH).getValue()<40){
            player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(Math.min(40,player.getAttribute(Attribute.MAX_HEALTH).getValue()+2));
            player.playSound(player.getLocation(), "terraria:life_crystal_use", 0.5F, 1.0F);
            player.getInventory().removeItem(LifeCrystal.getItem(plugin));
        }
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item =new LifeCrystal(plugin).createItem();
        ItemMeta meta= item.getItemMeta();
        meta.setMaxStackSize(64);
        item.setItemMeta(meta);
        return item;
    }
}
