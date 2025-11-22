package me.carson.terrariaItems.blocksFolder.blocks;

import me.carson.terrariaItems.blocksFolder.CustomBlock;
import me.carson.terrariaItems.materialsFolder.materials.Hellstone;
import me.carson.terrariaItems.materialsFolder.materials.HellstoneBar;
import me.carson.terrariaItems.toolFolder.tools.LifeCrystal;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.*;
import org.bukkit.entity.Item;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hellforge extends CustomBlock implements Listener {

    public Hellforge(Plugin plugin){
        super(plugin,"Hellforge","#FFFFFF",Material.FURNACE,"hellforge",new ArrayList<>(List.of(ChatColor.GRAY+"The Hellforge is a crafting station that functions as a regular Furnace, with the additional ability of smelting Hellstone into Hellstone Bars.")));
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(!isThisItem(e.getItemInHand()))return;

        Block block = e.getBlockPlaced();
        BlockState state = block.getState();

        if (!(state instanceof TileState tile)) return;

        PersistentDataContainer pdc = tile.getPersistentDataContainer();
        pdc.set(new NamespacedKey(plugin, id), PersistentDataType.INTEGER, 1);

        tile.update(true); // true = force
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Hellforge(plugin).createItem();
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (!(e.getPlayer() instanceof Player p)) return;

        Inventory inv = e.getInventory();

        if (inv.getType() != InventoryType.FURNACE) return;
        if (inv.getLocation() == null) return;

        if(!isHellforge(inv.getLocation().getBlock()))return;

        // Change title 1 tick later
        Bukkit.getScheduler().runTask(plugin, () -> {
            InventoryView view = p.getOpenInventory();
            view.setTitle(ChatColor.DARK_RED+"Hellforge");
        });
    }

    @EventHandler
    public void onSmeltStart(FurnaceStartSmeltEvent event) {
        Block block = event.getBlock();
        if (block.getState() instanceof Furnace furnace) {
            FurnaceInventory inv=furnace.getInventory();
            ItemStack smelt=inv.getSmelting();
            if(smelt==null){return;}
            if(isHellforge(block)){
                if((smelt.getType()==Material.MAGMA_BLOCK)&&(!smelt.hasItemMeta())){
                    event.setTotalCookTime(2147483647);
                }else {
                    event.setTotalCookTime(50);
                }
            }else {
                if((smelt.getType()==Material.MAGMA_BLOCK)){
                    event.setTotalCookTime(2147483647);
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.getType() != Material.FURNACE) return;

        if(!isHellforge(block))return;

        List<ItemStack> drops = new ArrayList<>();
        if (block.getState() instanceof Furnace furnace) {
            FurnaceInventory inv = furnace.getInventory();
            drops.add(inv.getSmelting());   // Slot 0
            drops.add(inv.getFuel());      // Slot 1
            drops.add(inv.getResult());      // Slot 2
        }
        for(ItemStack item:drops){
            if(item!=null){
                block.getWorld().dropItem(block.getLocation(), item);
            }
        }
        event.setDropItems(false); // prevent vanilla furnace drop

        ItemStack hellforge=getItem(plugin);

        block.getWorld().dropItem(block.getLocation(), hellforge);
    }


    private boolean isHellforge(Block block) {
        if (block == null) return false;
        BlockState state = block.getState();

        if (!(state instanceof TileState tile)) return false;
        return(tile.getPersistentDataContainer().has(new NamespacedKey(plugin, id), PersistentDataType.INTEGER));
    }

}
