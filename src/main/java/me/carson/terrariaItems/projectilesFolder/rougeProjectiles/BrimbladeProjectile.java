package me.carson.terrariaItems.projectilesFolder.rougeProjectiles;

import me.carson.terrariaItems.projectilesFolder.RougeProjectiles;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BrimbladeProjectile extends RougeProjectiles {

    private static final HashMap<UUID, ItemDisplay> brimMap = new HashMap<>();
    private static final HashMap<ItemDisplay, Boolean> brimMovingMap = new HashMap<>();

    public BrimbladeProjectile(Plugin plugin) {
        super(plugin, 0,  "brimblade", "BrimbladeProjectile",0,0, DamageType.TRIDENT,null);
    }

    @Override
    public void hitEntityEffect(LivingEntity entity, Player player) {

    }

    @Override
    public void hitBlockEffect(Block block) {

    }

    @Override
    public void onStealthThrow(Player player,float speed, float damage, float spread, float duration,float gravDuration, float gravStrength,float spinSpeed,double currentStealth) {

    }

    public void createBrimbladeProjectile(Player player,float speed,float weaponDamage, float spread,float duration,float spinSpeed,double currentStealth,boolean isStealthStrike){
        Result r = createDefaultProjectile(player,speed,spread);
        brimMap.put(player.getUniqueId(), r.proj());
        brimMovingMap.put(r.proj(),true);
        moveBrimbladeForward(player,weaponDamage,duration, r.proj(), r.dir(),spinSpeed,currentStealth,isStealthStrike);
    }

    private void moveBrimbladeForward(Player player, float weaponDamage, float duration, ItemDisplay proj, Vector dir, float spinSpeed, double currentStealth, boolean isStealthStrike){
        final int[] tick = {0};
        final Vector[] direction = {dir};
        final float[] spinAngle = {0f};
        ArrayList<Entity> hitEntities=new ArrayList<>();
        if(isStealthStrike){
            duration=duration*1.5f;
        }

        float finalDuration = duration;
        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                hitEntities.clear();
                return;
            }

            tick[0]++;

            if (tick[0] >= finalDuration) {
                moveBrimbladeBackwards(player,weaponDamage, finalDuration,proj,dir,spinSpeed,currentStealth,isStealthStrike);
                task.cancel();
                hitEntities.clear();
                return;
            }

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist, FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player)&&!(hitEntities.contains(e)));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        moveBrimbladeBackwards(player,weaponDamage, finalDuration,proj,dir,spinSpeed,currentStealth,isStealthStrike);
                        task.cancel();
                        return;
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        int temp= target.getMaximumNoDamageTicks();
                        target.setMaximumNoDamageTicks(0);
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage+weaponDamage+getStealthDamage(weaponDamage+damage,currentStealth)),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(temp);
                        hitEntities.add(target);
                    }
                }
            }
            Vector norm = direction[0].clone().normalize();
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));
            next.setYaw(yaw);
            next.setPitch(pitch);
            proj.teleport(next);
            spinProjectile(proj, spinAngle, spinSpeed);
        }, 1L, 1L);
    }

    private void moveBrimbladeBackwards(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir,float spinSpeed,double currentStealth,boolean isStealthStrike){
        final int[] tick = {0};
        final Vector[] direction = {dir};
        final float[] spinAngle = {0f};
        ArrayList<Entity> hitEntities=new ArrayList<>();

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                removeBrimblade(player, proj);
                task.cancel();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration*1.5) {
                removeBrimblade(player, proj);
                task.cancel();
                return;
            }

            Vector toPlayer = player.getEyeLocation().toVector().subtract(proj.getLocation().toVector());
            direction[0] = toPlayer.normalize().multiply(direction[0].length());

            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&!(hitEntities.contains(e)));
            if(result!=null){
                if(result.getHitEntity()==player){
                    removeBrimblade(player, proj);
                    task.cancel();
                    return;
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        int temp= target.getMaximumNoDamageTicks();
                        target.setMaximumNoDamageTicks(0);
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage+weaponDamage+getStealthDamage(weaponDamage+damage,currentStealth)),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(temp);
                        hitEntities.add(target);
                    }
                }
            }

            Vector norm = direction[0].clone().normalize();
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(Math.max(-1.0, Math.min(1.0, -norm.getY()))));
            next.setYaw(yaw);
            next.setPitch(pitch);

            proj.teleport(next);
            spinProjectile(proj, spinAngle, spinSpeed);
        }, 1L, 1L);
    }

    private void removeBrimblade(Player player, ItemDisplay proj) {
        proj.remove();
        brimMap.remove(player.getUniqueId());
        brimMovingMap.remove(proj);
    }

}
