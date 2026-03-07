package me.carson.terrariaItems.blocksFolder;

import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomBlockListeners implements Listener {

    private final Plugin plugin;
    private NamespacedKey customItemKey;


    public CustomBlockListeners(Plugin plugin){
        this.plugin=plugin;
        customItemKey=new NamespacedKey(plugin, "custom_item_id");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public boolean isHellforge(Block block) {
        if (block == null) return false;
        BlockState state = block.getState();
        if (!(state instanceof TileState tile)) return false;
        return(tile.getPersistentDataContainer().has(new NamespacedKey(plugin, "hellforge"), PersistentDataType.INTEGER));
    }

    public Boolean isHellforgeItem(ItemStack item,String id){
        if(item!=null&&item.hasItemMeta()){
            if(Objects.equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(customItemKey, PersistentDataType.STRING), id)){
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onHellforgePlace(BlockPlaceEvent e) {
        if(!isHellforgeItem(e.getItemInHand(),"hellforge"))return;

        Block block = e.getBlockPlaced();
        BlockState state = block.getState();

        if (!(state instanceof TileState tile)) return;

        PersistentDataContainer pdc = tile.getPersistentDataContainer();
        pdc.set(new NamespacedKey(plugin, "hellforge"), PersistentDataType.INTEGER, 1);

        tile.update(true); // true = force
    }



    @EventHandler
    public void onHellforgeInventoryOpen(InventoryOpenEvent e) {
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
    public void onHellforgeSmeltStart(FurnaceStartSmeltEvent event) {
        Block block = event.getBlock();
        if (block.getState() instanceof Furnace furnace) {
            FurnaceInventory inv=furnace.getInventory();
            ItemStack smelt=inv.getSmelting();
            if(smelt==null){return;}
            if(isHellforge(block)){
                if((smelt.getType()== Material.MAGMA_BLOCK)&&(!smelt.hasItemMeta())){
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
    public void onHellforgeBreak(BlockBreakEvent event) {
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
        ItemStack hellforge=Hellforge.getItem(plugin);
        block.getWorld().dropItem(block.getLocation(), hellforge);
    }


}
