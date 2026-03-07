package me.carson.terrariaItems.blocksFolder.blocks;

import me.carson.terrariaItems.blocksFolder.CustomBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Hellforge extends CustomBlock implements Listener {

    public Hellforge(Plugin plugin){
        super(plugin,"Hellforge","#FFFFFF",Material.FURNACE,"hellforge",new ArrayList<>(List.of(ChatColor.GRAY+"The Hellforge is a crafting station that functions as a faster Furnace",ChatColor.GRAY+"with the additional ability of smelting Hellstone into Hellstone Bars.")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Hellforge(plugin).createItem();
    }
}
