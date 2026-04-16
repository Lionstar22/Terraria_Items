package me.carson.terrariaItems.enemiesFolder;

import me.carson.terrariaItems.accesoryFolder.accessories.MagicQuiver;
import me.carson.terrariaItems.enemiesFolder.enemies.*;
import me.carson.terrariaItems.materialsFolder.materials.ForbiddenFragment;
import me.carson.terrariaItems.materialsFolder.materials.FrostCore;
import me.carson.terrariaItems.toolFolder.tools.summons.BloodyTear;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.BloodRainBow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyManager implements Listener {

    private final NamespacedKey key;
    private final Plugin plugin;

    public EnemyManager(Plugin plugin) {
        this.plugin = plugin;
        key = new NamespacedKey(plugin, "custom_enemy");
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new CustomZombies(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CustomSkeletons(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CustomDrowned(plugin), plugin);
    }


    @EventHandler
    public void onPossessedArmor(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FIRE_TICK) {
            return;
        }
        if (!Objects.equals(event.getEntity().getPersistentDataContainer().get(key, PersistentDataType.STRING), "PossessedArmor")) {
            return;
        }
        event.setCancelled(true);
    }

}
