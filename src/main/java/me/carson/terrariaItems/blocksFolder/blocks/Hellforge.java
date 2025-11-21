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
    public void onBurn(FurnaceBurnEvent event) {
        if (!isHellforge(event.getBlock())) return;
        
        Block block = event.getBlock();
        if (block.getState() instanceof Furnace furnace) {
            FurnaceInventory inv = furnace.getInventory();
            if ((inv.getFuel().getType() == Material.OBSIDIAN)&&(inv.getSmelting()==new Hellstone(plugin).createItem())) {
                event.setBurning(true);
                event.setBurnTime(50);
            }
        }
    }

    @EventHandler
    public void onSmeltStart(FurnaceStartSmeltEvent event) {
        Block block = event.getBlock();
        if (!isHellforge(block)) return;

        // Default furnace time = 200 ticks
        event.setTotalCookTime(50);
    }

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent event) {
        // Only your custom furnaces react
        if (!isHellforge(event.getBlock())) return;

        Furnace furnace = (Furnace) event.getBlock().getState();
        FurnaceInventory inv = furnace.getInventory();

        ItemStack input = inv.getSmelting();
        ItemStack fuel = inv.getFuel();
        if (input == null || fuel == null) return;

        // Your custom recipe:
        if ((fuel.getType() == Material.OBSIDIAN)&&(input==new Hellstone(plugin).createItem())) {
            event.setResult(new HellstoneBar(plugin).createItem());
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
    /*
    @EventHandler
    public void onFuelInsert(InventoryClickEvent event) {
        Block block= Objects.requireNonNull(Objects.requireNonNull(event.getClickedInventory()).getLocation()).getBlock();
        if(!isHellforge(block))return;
        // Only handle clicks where the top inventory is a furnace
        InventoryView view = event.getView();
        if (!(view.getTopInventory() instanceof FurnaceInventory)) return;

        FurnaceInventory top = (FurnaceInventory) view.getTopInventory();

        int rawSlot = event.getRawSlot();
        // In furnace top inventory the fuel slot is index 1 (0 = input, 1 = fuel, 2 = result)
        if (rawSlot != 1) return;

        // Ensure it's a player
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        InventoryAction action = event.getAction();
        ItemStack cursor = event.getCursor();        // item on cursor (when placing)
        ItemStack currentFuel = top.getFuel();       // item currently in fuel slot
        ItemStack clickedItem = event.getCurrentItem(); // item in the clicked slot (same as currentFuel)

        // ----- Handle placing from cursor (normal click / place one/all) -----
        if (action == InventoryAction.PLACE_ALL
                || action == InventoryAction.PLACE_ONE
                || action == InventoryAction.PLACE_SOME
                || action == InventoryAction.SWAP_WITH_CURSOR) {

            if (cursor == null) return; // nothing to place

            // If placing obsidian, allow it
            if (cursor.getType() == Material.OBSIDIAN) {
                // Let the default action occur for most PLACE_* actions by doing nothing
                // but some servers/versions block it, so force it:
                event.setCancelled(true);

                // Compute how many to place depending on action
                if (action == InventoryAction.PLACE_ONE) {
                    ItemStack toPlace = cursor.clone();
                    toPlace.setAmount(1);

                    // Put into fuel slot (if empty -> set, else increase amount)
                    if (currentFuel == null || currentFuel.getType() == Material.AIR) {
                        top.setFuel(toPlace);
                    } else if (currentFuel.isSimilar(toPlace)) {
                        currentFuel.setAmount(Math.min(currentFuel.getMaxStackSize(), currentFuel.getAmount() + 1));
                        top.setFuel(currentFuel);
                    } else {
                        // should not normally reach: fuel slot contains other item; cancel
                        player.getInventory().addItem(toPlace); // fallback
                    }

                    // Remove 1 from cursor
                    ItemStack newCursor = cursor.clone();
                    newCursor.setAmount(cursor.getAmount() - 1);
                    player.setItemOnCursor(newCursor.getAmount() <= 0 ? null : newCursor);

                } else {
                    // PLACE_ALL / PLACE_SOME / SWAP_WITH_CURSOR: place as many as will fit
                    // Simple approach: move entire cursor into fuel slot if empty, else merge.
                    if (currentFuel == null || currentFuel.getType() == Material.AIR) {
                        top.setFuel(cursor.clone());
                        player.setItemOnCursor(null);
                    } else if (currentFuel.isSimilar(cursor)) {
                        int canAdd = currentFuel.getMaxStackSize() - currentFuel.getAmount();
                        int toAdd = Math.min(canAdd, cursor.getAmount());
                        currentFuel.setAmount(currentFuel.getAmount() + toAdd);
                        top.setFuel(currentFuel);

                        ItemStack newCursor = cursor.clone();
                        newCursor.setAmount(cursor.getAmount() - toAdd);
                        player.setItemOnCursor(newCursor.getAmount() <= 0 ? null : newCursor);
                    } else {
                        // different item in fuel slot — do nothing (or swap if SWAP_WITH_CURSOR)
                        if (action == InventoryAction.SWAP_WITH_CURSOR) {
                            top.setFuel(cursor.clone());
                            player.setItemOnCursor(clickedItem); // swap
                        }
                    }
                }
            } else {
                // Not obsidian — keep vanilla behavior (do nothing)
            }

            return;
        }
    }*/
}
