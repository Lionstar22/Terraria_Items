package me.carson.terrariaItems.projectilesFolder;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Projectile {

    protected final Plugin plugin;
    protected final int damage;
    protected final float speed;
    protected final float spread;
    protected final double duration;
    protected final String texture;
    protected final String id;

    public Projectile(Plugin plugin, int damage, float speed, float spread, double duration, String texture, String id) {
        this.plugin = plugin;
        this.speed = speed;
        this.spread = spread;
        this.duration = duration;
        this.texture = texture;
        this.id = id;
        this.damage=damage;
    }

    public void createProjectile(Player player){
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection().normalize();
        loc.add(dir.multiply(1));
        Arrow arrow =player.getWorld().spawnArrow(loc,dir, speed, spread);
        arrow.setGravity(false);
        arrow.setInvisible(true);
        arrow.setShooter(player);
        NamespacedKey key = new NamespacedKey(plugin, "custom_projectile_id");
        arrow.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        ArmorStand armorStand= player.getWorld().spawn(loc,ArmorStand.class);
        armorStand.setInvisible(true);
        arrow.addPassenger(armorStand);
    }
}
