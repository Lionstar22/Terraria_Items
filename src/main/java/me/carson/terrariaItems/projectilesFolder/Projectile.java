package me.carson.terrariaItems.projectilesFolder;

import me.carson.terrariaItems.projectilesFolder.projectiles.Leaf;
import me.carson.terrariaItems.weaponsFolder.weapons.HallowedRepeater;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public abstract class Projectile implements Listener {

    protected final Plugin plugin;
    protected final int damage;
    protected final float speed;
    protected final float spread;
    protected final int duration;
    protected final String texture;
    protected final String id;

    public Projectile(Plugin plugin, int damage, float speed, float spread, int duration, String texture, String id) {
        this.plugin = plugin;
        this.speed = speed;
        this.spread = spread;
        this.duration= duration;
        this.texture = texture;
        this.id = id;
        this.damage=damage;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta=item.getItemMeta();
        meta.setItemModel(new NamespacedKey("terraria", texture));
        item.setItemMeta(meta);
        return item;
    }

    public void createProjectile(Player player, ItemStack item){
        Location loc = player.getEyeLocation();
        loc.add( loc.getDirection().normalize());
        Vector dir = loc.getDirection().normalize().multiply(speed);

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc,EntityType.ITEM_DISPLAY);
        proj.setItemStack(item);
        NamespacedKey key = new NamespacedKey(plugin, id);
        proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        proj.setInterpolationDuration(3);
        proj.setTeleportDuration(1);

        final int[] tick = {0};

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                proj.remove();
                task.cancel();
                return;
            }

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(dir);
            if (!next.getBlock().isPassable()) {
                proj.remove();
                task.cancel();
                return;
            }

            // Entity hit
            Entity hit = proj.getNearbyEntities(0.4, 0.4, 0.4).stream()
                    .filter(e -> e != player)
                    .findFirst()
                    .orElse(null);

            if (hit != null) {
                // handle entity hit
                if(hit instanceof LivingEntity target){
                    target.damage(damage, player);
                    hitEffect(target);
                }
                proj.remove();
                task.cancel();
                return;
            }

            proj.teleport(proj.getLocation().add(dir));
        }, 1L, 1L);

    }

    public abstract void hitEffect(LivingEntity entity);

}
