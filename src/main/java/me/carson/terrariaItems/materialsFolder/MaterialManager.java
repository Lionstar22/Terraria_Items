package me.carson.terrariaItems.materialsFolder;


import me.carson.terrariaItems.materialsFolder.materials.*;
import me.carson.terrariaItems.materialsFolder.materials.bullets.BubonicRound;
import me.carson.terrariaItems.materialsFolder.materials.bullets.ExplodingBullet;
import me.carson.terrariaItems.materialsFolder.materials.bullets.MusketBall;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class MaterialManager implements Listener {

    private static MaterialManager instance;
    public final HashMap<String, ItemStack> bulletItemList = new HashMap<>();
    public final NamespacedKey bulletKey;

    public MaterialManager(Plugin plugin) {

        bulletKey = new NamespacedKey(plugin, "custom_item_id");

        bulletItemList.put("MusketBall", MusketBall.getItem(plugin));
        bulletItemList.put("ExplodingBullet", ExplodingBullet.getItem(plugin));
        bulletItemList.put("BubonicRound", BubonicRound.getItem(plugin));

        Bukkit.getPluginManager().registerEvents(new SoulOfMight(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new SoulOfSight(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new SoulOfFright(plugin), plugin);
    }

    public ItemStack getBulletItem(ItemStack item){
        if(item==null|| !item.hasItemMeta()){return null;}
        String accessoryId= item.getItemMeta().getPersistentDataContainer().get(bulletKey, PersistentDataType.STRING);
        return bulletItemList.get(accessoryId);
    }


    public static void initialize(JavaPlugin plugin) {
        instance = new MaterialManager(plugin);
    }

    public static MaterialManager getInstance() {
        return instance;
    }
}
