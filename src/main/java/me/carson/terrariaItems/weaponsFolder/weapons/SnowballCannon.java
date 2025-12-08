package me.carson.terrariaItems.weaponsFolder.weapons;

import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SnowballCannon extends Weapon implements Listener {

    public SnowballCannon(Plugin plugin) {
        super(plugin,"Snowball Cannon","#9696FF", 8,0,Material.BOW,"snowball_cannon","SnowballCannon",10, new ArrayList<>(List.of(ChatColor.GRAY+"Launches Snowballs")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SnowballCannon(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if (player.getInventory().contains(Material.SNOWBALL)) {
            player.getInventory().removeItem(new ItemStack(Material.SNOWBALL, 1));
            Location loc = player.getEyeLocation();
            Vector dir = loc.getDirection().normalize().multiply(2.5);
            loc.add(dir.multiply(1));
            World world= player.getWorld();
            world.playSound(loc, "terraria:gun_shoot", 1.0F, 1.0F);
            Snowball snowball=world.spawn(loc,Snowball.class);
            NamespacedKey key = new NamespacedKey(plugin, "custom_snowball");
            snowball.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
            snowball.setVelocity(dir);
        }
    }

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Snowball snowball)) return;
        NamespacedKey key = new NamespacedKey(plugin, "custom_snowball");
        PersistentDataContainer pdc = snowball.getPersistentDataContainer();
        if (!pdc.has(key, PersistentDataType.INTEGER)) return;
        if (!(event.getHitEntity() instanceof LivingEntity target)) return;

        World world= event.getEntity().getWorld();
        world.playSound(event.getHitEntity().getLocation(), "terraria:snowball_impact", 1.0F, 1.0F);
        target.damage(super.damage);
    }
}
