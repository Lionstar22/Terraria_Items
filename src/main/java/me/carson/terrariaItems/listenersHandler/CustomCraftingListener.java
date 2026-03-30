package me.carson.terrariaItems.listenersHandler;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.BrewEvent;
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
            if(hasCustom(matrix)&&!hasCustomKey(result)){
                inv.setResult(null);
            }
        }
    }
    
    public Boolean hasCustom(ItemStack[] matrix){
        for (ItemStack item : matrix) {
            if(item!=null){
                if(hasCustomKey(item)){
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean hasCustomKey(ItemStack item){
        if (item==null){return false;}
        if(item.getItemMeta()==null){return false;}
        return item.getItemMeta().getPersistentDataContainer().has(customItemKey);
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack item = event.getResult();
        if (hasCustomKey(item)){
            event.setResult(null);
        }
    }

    @EventHandler
    public void onSmithing(PrepareSmithingEvent event) {
        ItemStack item = event.getInventory().getItem(1);
        if(hasCustomKey(item)){
            event.setResult(null);
        }
    }

    @EventHandler
    public void onEnchant(PrepareItemEnchantEvent event){
        ItemStack itemStack = event.getItem();
        if(hasCustomKey(itemStack)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPotion(BrewEvent event)   {
        ItemStack[] contents=event.getContents().getContents();
        for(ItemStack item:contents){
            if(hasCustomKey(item)){
                event.setCancelled(true);
            }
        }
    }


}
