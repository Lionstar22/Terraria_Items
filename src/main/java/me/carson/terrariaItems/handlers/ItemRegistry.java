package me.carson.terrariaItems.handlers;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ItemRegistry {

    private final Plugin plugin;
    private static ItemRegistry instance;
    private final Collection<ItemStack> registry=new ArrayList<>();

    private ItemRegistry(Plugin plugin) {
        this.plugin=plugin;
    }

    public void addFromHash(Map<String, ItemStack> map){
        registry.addAll(map.values());
    }

    public void addItem(ItemStack itemStack){
        if(itemStack==null){return;}
        registry.add(itemStack);
    }

    public Collection<ItemStack> getRegistry(){
        return registry;
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new ItemRegistry(plugin);
    }

    public static ItemRegistry getInstance() {
        return instance;
    }
}
