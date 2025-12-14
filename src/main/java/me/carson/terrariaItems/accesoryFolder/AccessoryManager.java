package me.carson.terrariaItems.accesoryFolder;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AccessoryManager implements Listener {
    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();
    private final HashMap<String, Accessory> accessoryList = new HashMap<>();
    private final NamespacedKey accessoryKey;

    public AccessoryManager(Plugin plugin) {
        accessoryKey = new NamespacedKey(plugin, "custom_item_id");

        accessoryList.put("Aglet",new Aglet(plugin));
        accessoryList.put("ObsidianSkull",new ObsidianSkull(plugin));
        accessoryList.put("RedBalloon",new RedBalloon(plugin));
        accessoryList.put("BandOfRegeneration",new BandOfRegeneration(plugin));
        accessoryList.put("CloudInBottle",new CloudInBottle(plugin));
        accessoryList.put("LuckyHorseshoe",new LuckyHorseshoe(plugin));
        accessoryList.put("CobaltShield",new CobaltShield(plugin));
        accessoryList.put("CounterScarf",new CounterScarf(plugin));
        accessoryList.put("AncientFossil",new AncientFossil(plugin));
        accessoryList.put("NeptunesShell",new NeptunesShell(plugin));
        accessoryList.put("Bezoar",new Bezoar(plugin));
        accessoryList.put("Blindfold",new Blindfold(plugin));
        accessoryList.put("FastClock",new FastClock(plugin));
        accessoryList.put("Vitamins",new Vitamins(plugin));
        accessoryList.put("WarriorEmblem",new WarriorEmblem(plugin));
        accessoryList.put("RangerEmblem",new RangerEmblem(plugin));
        accessoryList.put("Shackle",new Shackle(plugin));
        accessoryList.put("AvengerEmblem",new AvengerEmblem(plugin));

        //Adds listeners for special cases
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new LuckyHorseshoe(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CloudInBottle(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CobaltShield(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CounterScarf(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new Bezoar(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new Blindfold(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new FastClock(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new Vitamins(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new WarriorEmblem(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new RangerEmblem(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new Shackle(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new AvengerEmblem(plugin),plugin);
    }

    public void deactivateItem(ItemStack itemChecked,Player player){
        Accessory accessory=getAccessory(itemChecked);
        if(accessory!=null){
            accessory.deactivateEffect(player);
            accessory.setActivated(itemChecked,false);
        }
    }

    public void activateItem(ItemStack item,Player player){
        Accessory accessory=getAccessory(item);
        if(accessory!=null){
            accessory.activateEffect(player);
            accessory.setActivated(item,true);
        }
    }

    public Accessory getAccessory(ItemStack item){
        if(item==null|| !item.hasItemMeta()){return null;}
        String accessoryId= item.getItemMeta().getPersistentDataContainer().get(accessoryKey, PersistentDataType.STRING);
        return accessoryList.get(accessoryId);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Inventory inv = event.getPlayer().getInventory();
        for(ItemStack item:inv){
            deactivateItem(item, event.getPlayer());
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem= event.getItemDrop().getItemStack();
        deactivateItem(droppedItem,player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory topInv = event.getView().getTopInventory();
        Inventory clickedInv = event.getClickedInventory();
        Inventory playerInv= player.getInventory();

        // Manual placement
        if (clickedInv != null && clickedInv.equals(topInv)) {
            ItemStack cursor = event.getCursor();
            if (!cursor.getType().isAir()) {
                deactivateItem(cursor,player);
            }
        }
        // Shift-click movement
        if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY && clickedInv != null && (topInv.getType() != InventoryType.CRAFTING)) {
            ItemStack item = event.getCurrentItem();
            if (item != null && !item.getType().isAir()) {
                deactivateItem(item,player);
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player player = event.getPlayer();
        if(!player.isSneaking())return;

        //handles rapid clicks
        long currentTime = System.currentTimeMillis();
        long lastTime = lastClickTime.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastTime < 500) {
            return; // Ignore repeated right-clicks
        }
        lastClickTime.put(player.getUniqueId(), currentTime);

        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;

        Accessory accessory=getAccessory(heldItem);
        if(accessory!=null) {
            if(accessory.isActivated(heldItem)){
                deactivateItem(heldItem,player);
            }else if(checkAmountActivated(player)){
                activateItem(heldItem,player);
            }
        }
    }

    public boolean checkAmountActivated(Player player){
        int counter=0;
        for (ItemStack itemInv : player.getInventory().getContents()) {
            Accessory accessory=getAccessory(itemInv);
            if(accessory!=null&& accessory.isActivated(itemInv)){
                counter++;
            }
        }
        if (counter >= 5){
            player.sendMessage(ChatColor.RED+"Can not have more than 5 active Accessories");
        }
        return counter < 5;
    }



}