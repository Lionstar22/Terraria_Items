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

public class SapphireBolt extends Projectile {

    public SapphireBolt(Plugin plugin) {
        super(plugin, 0,  "sapphire_bolt", "SapphireBolt",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(76, 76, 192), 1f),0);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    public void createSapphireBolts(Player player,float speed,float weaponDamage, float spread,float duration){
        Location eye = player.getEyeLocation();
        double yaw = Math.toRadians(eye.getYaw());
        Vector right = new Vector(-Math.cos(yaw), 0, -Math.sin(yaw));
        double offset = 0.3;
        Location rightLoc = eye.clone().add(right.clone().multiply(offset));
        Location leftLoc  = eye.clone().subtract(right.clone().multiply(offset));

        rightLoc.add(rightLoc.getDirection().normalize().multiply(0.3));
        leftLoc.add(leftLoc.getDirection().normalize().multiply(0.3));

        Vector dir = player.getEyeLocation().getDirection();
        dir.add(new Vector(
                (Math.random() - 0.5) * spread,
                (Math.random() - 0.5) * spread,
                (Math.random() - 0.5) * spread
        ));
        dir.normalize().multiply(speed);

        rightLoc.setDirection(dir);
        leftLoc.setDirection(dir);

        ItemDisplay projRight = (ItemDisplay) player.getWorld().spawnEntity(rightLoc, EntityType.ITEM_DISPLAY);
        ItemDisplay projLeft = (ItemDisplay) player.getWorld().spawnEntity(leftLoc, EntityType.ITEM_DISPLAY);

        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta=item.getItemMeta();
        meta.setItemModel(new NamespacedKey("terraria", texture));
        item.setItemMeta(meta);

        projRight.setItemStack(item);
        NamespacedKey keyRight = new NamespacedKey(plugin, id);
        projRight.getPersistentDataContainer().set(keyRight, PersistentDataType.INTEGER,1);
        projRight.setInterpolationDuration(0);
        projRight.setTeleportDuration(2);
        projRight.setInterpolationDelay(-1);
        faceDirection(projRight, dir);
        moveProj(player,weaponDamage,duration,projRight,dir);

        projLeft.setItemStack(item);
        NamespacedKey keyLeft = new NamespacedKey(plugin, id);
        projLeft.getPersistentDataContainer().set(keyLeft, PersistentDataType.INTEGER,1);
        projLeft.setInterpolationDuration(0);
        projLeft.setTeleportDuration(2);
        projLeft.setInterpolationDelay(-1);
        faceDirection(projLeft, dir);
        moveProj(player,weaponDamage,duration,projLeft,dir);
    }

}
