package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class RubyBolt extends Projectile {

    public RubyBolt(Plugin plugin) {
        super(plugin, 0,  "ruby_bolt", "RubyBolt",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(136, 30, 27), 1f),0);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    public void createRubyBolt(Player player,float speed,float weaponDamage, float spread,float duration){
        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.3));

        Vector dir = player.getEyeLocation().getDirection();
        dir.add(new Vector(
                (Math.random() - 0.5) * spread,
                (Math.random() - 0.5) * spread,
                (Math.random() - 0.5) * spread
        ));
        dir.normalize().multiply(speed);

        loc.setDirection(dir);

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc, EntityType.ITEM_DISPLAY);

        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta=item.getItemMeta();
        meta.setItemModel(new NamespacedKey("terraria", texture));
        item.setItemMeta(meta);

        proj.setItemStack(item);
        NamespacedKey key = new NamespacedKey(plugin, id);
        proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        proj.setInterpolationDuration(0);
        proj.setTeleportDuration(2);
        proj.setInterpolationDelay(-1);
        faceDirection(proj, dir);
        moveProj(player,weaponDamage,duration,proj,dir);
    }

    public void shootRubyBolts(Player player,float  speed,float damage,float spread,float duration){
        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                count++;
                createRubyBolt(player, speed, damage, spread, duration);
                if (count >= 2) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 1L, 6L);
    }
}
