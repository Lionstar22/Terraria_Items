package me.carson.terrariaItems.projectilesFolder.projectiles;

import me.carson.terrariaItems.projectilesFolder.Projectile;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class EmeraldBolt extends Projectile {

    public EmeraldBolt(Plugin plugin) {
        super(plugin, 0,  "emerald_bolt", "EmeraldBolt",1,0, DamageType.LIGHTNING_BOLT,new Particle.DustOptions(Color.fromRGB(25, 224, 150), 1f),0);
    }


    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    public void createEmeraldBolt(Player player,float speed,float weaponDamage, float spread,float duration){
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

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc,EntityType.ITEM_DISPLAY);

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
        moveEmeraldBolt(player,weaponDamage,duration,proj,dir);
    }

    public void moveEmeraldBolt(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir){
        final int[] tick = {0};
        final int[] enemiesHit = {0};
        final int[] blocksBounced = {0};
        final Vector[] direction = {dir};
        ArrayList<Entity> hitEntities=new ArrayList<>();

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

            if (tick[0] >= 20) {
                direction[0] = new Vector(direction[0].getX()*0.95, direction[0].getY()*0.95 , direction[0].getZ()*0.95);
            }

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player)&&!(hitEntities.contains(e)));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        hitBlockEffect(result.getHitBlock());
                        if(blocksBounced[0]>=bounces){
                            proj.remove();
                            task.cancel();
                            return;
                        }else{
                            blocksBounced[0]++;
                            direction[0] =bounce(direction[0],result.getHitBlockFace());
                            next = now.clone().add(direction[0]);
                            hitEntities.clear();
                        }
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        int temp= target.getMaximumNoDamageTicks();
                        target.setMaximumNoDamageTicks(0);
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage+weaponDamage),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(temp);
                        hitEntities.add(target);
                    }
                    if(enemiesHit[0] >=peirce) {
                        proj.remove();
                        task.cancel();
                        return;
                    }else {
                        enemiesHit[0]++;
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
