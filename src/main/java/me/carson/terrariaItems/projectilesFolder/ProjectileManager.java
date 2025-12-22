package me.carson.terrariaItems.projectilesFolder;

import me.carson.terrariaItems.materialsFolder.Material;
import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.projectiles.bullets.ExplodingBulletProjectile;
import me.carson.terrariaItems.projectilesFolder.projectiles.bullets.MusketBallProjectile;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public class ProjectileManager implements Listener {

    private static ProjectileManager instance;
    public final HashMap<String, Projectile> bulletList = new HashMap<>();
    public final NamespacedKey bulletKey;
    MaterialManager materialManagerInstance= MaterialManager.getInstance();

    public ProjectileManager(Plugin plugin) {
        bulletKey = new NamespacedKey(plugin, "custom_item_id");

        bulletList.put("MusketBall", new MusketBallProjectile(plugin));
        bulletList.put("ExplodingBullet", new ExplodingBulletProjectile(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Projectile getBullet(String id){
        return bulletList.get(id);
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new ProjectileManager(plugin);
    }

    public static ProjectileManager getInstance() {
        return instance;
    }
}
