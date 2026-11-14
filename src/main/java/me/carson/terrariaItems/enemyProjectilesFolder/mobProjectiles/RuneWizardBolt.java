package me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class RuneWizardBolt extends EnemyProjectile {

    public RuneWizardBolt(Plugin plugin) {
        super(plugin, 15,1f, "rune_wizard_bolt", "RuneWizardBolt",0,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(34, 177, 76), 1f));
    }

    private final int duration=100;

    @Override
    public void hitEntityEffect(LivingEntity entity) {
        entity.setFreezeTicks(300);
        entity.setFireTicks(60);
    }

    @Override
    public void hitBlockEffect(Block block) {
        block.getWorld().playSound(block.getLocation(), "terraria:impact_1", 2.0F, 1.0F);
    }

    @Override
    public void shootProj(LivingEntity shooter, Player target) {
        Location loc = shooter.getEyeLocation();
        Location targetLoc=target.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.1));

        Vector dir = targetLoc.toVector().subtract(loc.toVector()).normalize();

        dir.normalize().multiply(projSpeed);

        loc.setDirection(dir);

        ItemDisplay proj = (ItemDisplay) shooter.getWorld().spawnEntity(loc, EntityType.ITEM_DISPLAY);

        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta=item.getItemMeta();
        meta.setItemModel(new NamespacedKey("terraria", texture));
        item.setItemMeta(meta);

        proj.setItemStack(item);
        NamespacedKey key = new NamespacedKey(plugin, id);
        proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        proj.setInterpolationDuration(3);
        proj.setTeleportDuration(1);
        faceDirection(proj,dir);
        moveProj(shooter,proj,dir);
    }

    private void moveProj(LivingEntity shooter,ItemDisplay proj, Vector dir){
        final int[] tick = {0};
        final Vector[] direction = {dir};

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
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= shooter.getWorld().rayTrace(now,now.getDirection(),dist, FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=shooter)&&e.getType()==EntityType.PLAYER);
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        hitBlockEffect(result.getHitBlock());
                        proj.remove();
                        task.cancel();
                        return;
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(shooter).withDirectEntity(shooter).build();
                        target.damage((damage),source);
                        hitEntityEffect(target);
                        proj.remove();
                        task.cancel();
                        return;
                    }
                }
            }
            Vector norm = direction[0].clone().normalize();
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));
            next.setYaw(yaw);
            next.setPitch(pitch);
            proj.teleport(next);

            if (particle != null&&tick[0]>2) {
                proj.getWorld().spawnParticle(Particle.DUST, now, 1, 0, 0, 0, 0,particle);
            }
        }, 1L, 1L);
    }

}
