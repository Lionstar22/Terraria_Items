package me.carson.terrariaItems.projectilesFolder.rougeProjectiles;

import me.carson.terrariaItems.projectilesFolder.RougeProjectiles;
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


public class BurningCinder extends RougeProjectiles {

    private final double speed=2;
    private final double gravStrength=0.5;
    private final int duration=100;
    private final int spinSpeed=15;

    public BurningCinder(Plugin plugin) {
        super(plugin, 6,  "burning_cinder", "BurningCinder",0,0, DamageType.TRIDENT,new Particle.DustOptions(Color.fromRGB(179, 35, 69), 1f));
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

    public void makeCinder(Player player, Location loc, double currentStealth){

        Vector dir = new Vector((Math.random() - 0.5), 1, (Math.random() - 0.5));

        dir.normalize().multiply(speed);

        loc.setDirection(dir);

        ItemDisplay proj = (ItemDisplay) player.getWorld().spawnEntity(loc, EntityType.ITEM_DISPLAY);

        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta=item.getItemMeta();
        meta.setItemModel(new NamespacedKey("terraria", texture));
        item.setItemMeta(meta);

        proj.setItemStack(item);
        proj.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.HEAD);
        NamespacedKey key = new NamespacedKey(plugin, id);
        proj.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        proj.setInterpolationDuration(0);
        proj.setTeleportDuration(2);
        proj.setInterpolationDelay(-1);
        faceDirection(proj, dir);
        moveCinder(player,proj,dir,currentStealth);
    }

    private void moveCinder(Player player,ItemDisplay proj, Vector dir,double currentStealth){
        final int[] tick = {0};
        final Vector[] direction = {dir};
        final float[] spinAngle = {0f};
        ArrayList<Entity> hitEntities=new ArrayList<>();

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                hitEntities.clear();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                proj.remove();
                task.cancel();
                hitEntities.clear();
                return;
            }

            direction[0] = new Vector(direction[0].getX(), direction[0].getY() - gravStrength, direction[0].getZ());

            //block handling
            Location now = proj.getLocation();
            Location next = now.clone().add(direction[0]);
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist,FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player)&&!(hitEntities.contains(e)));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable() && result.getHitBlockFace()!=null){
                        proj.remove();
                        task.cancel();
                        return;
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        int temp= target.getMaximumNoDamageTicks();
                        target.setMaximumNoDamageTicks(0);
                        DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(player).build();
                        target.damage((damage+getStealthDamage(damage,currentStealth)),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(temp);
                    }
                    proj.remove();
                    task.cancel();
                    return;
                }
            }
            Vector norm = direction[0].clone().normalize();
            float yaw = (float) Math.toDegrees(Math.atan2(-norm.getX(), norm.getZ()));
            float pitch = (float) Math.toDegrees(Math.asin(-norm.getY()));
            next.setYaw(yaw);
            next.setPitch(pitch);
            proj.teleport(next);
            //proj.setInterpolationDelay(0);
            spinProjectile(proj, spinAngle, spinSpeed);

            if (particle != null&&tick[0]>2) {
                proj.getWorld().spawnParticle(Particle.DUST, now, 1, 0, 0, 0, 0,particle);
            }
        }, 1L, 1L);
    }

}
