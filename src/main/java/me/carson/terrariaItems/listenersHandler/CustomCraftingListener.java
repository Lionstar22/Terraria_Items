package me.carson.terrariaItems.listenersHandler;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCraftingListener implements Listener {

    private final NamespacedKey customItemKey;

    public CustomCraftingListener(JavaPlugin plugin) {
        this.customItemKey = new NamespacedKey(plugin, "customItem");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();
        ItemStack[] matrix = inv.getMatrix();
        ItemStack result=inv.getResult();
        if(result!=null){
            if(hasCustom(matrix)&&!result.getItemMeta().getPersistentDataContainer().has(customItemKey)){
                inv.setResult(null);
            }
        }
    }
    
    public Boolean hasCustom(ItemStack[] matrix){
        for (ItemStack item : matrix) {
            if(item!=null){
                if(item.getItemMeta().getPersistentDataContainer().has(customItemKey)){
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean isAllVanilla(ItemStack[] matrix){
        for (ItemStack item : matrix) {
            if(item!=null){
                if(item.getItemMeta().getPersistentDataContainer().has(customItemKey)){
                    return false;
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack item = event.getResult();
        if (item != null) {
            if(item.getItemMeta()!=null){
                PersistentDataContainer data=item.getItemMeta().getPersistentDataContainer();
                if (data.has(customItemKey, PersistentDataType.BYTE)) {
                    event.setResult(null);
                }
            }
        }
    }

    @EventHandler
    public void onSmithing(PrepareSmithingEvent event) {
        ItemStack item = event.getInventory().getInputEquipment(); // or getInputTemplate(), getInputMineral() depending on layout

        if (item != null) {
            if(item.getItemMeta()!=null){
                PersistentDataContainer data=item.getItemMeta().getPersistentDataContainer();
                if (data.has(customItemKey, PersistentDataType.BYTE)) {
                    event.setResult(null);
                }
            }
        }
    }
}
