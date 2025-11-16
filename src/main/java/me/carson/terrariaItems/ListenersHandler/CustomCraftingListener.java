package me.carson.terrariaItems.ListenersHandler;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCraftingListener implements Listener {

    private final NamespacedKey customMaterialKey;
    private final NamespacedKey customCraftableKey;

    public CustomCraftingListener(JavaPlugin plugin) {
        this.customMaterialKey = new NamespacedKey(plugin, "customMaterial");
        this.customCraftableKey = new NamespacedKey(plugin, "customCraftable");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();
        ItemStack[] matrix = inv.getMatrix();
        ItemStack result=inv.getResult();
        if(result!=null){
            if (!hasCustom(matrix) && result.getItemMeta().getPersistentDataContainer().has(customCraftableKey)) {
                inv.setResult(null);
            } else if (hasCustom(matrix) && !result.getItemMeta().getPersistentDataContainer().has(customCraftableKey)) {
                inv.setResult(null);
            }
        }

    }
    
    public Boolean hasCustom(ItemStack[] matrix){
        for (ItemStack item : matrix) {
            if(item!=null){
                if(item.getItemMeta().getPersistentDataContainer().has(customMaterialKey)){
                    return true;
                }
            }
        }
        return false;
    }

}
