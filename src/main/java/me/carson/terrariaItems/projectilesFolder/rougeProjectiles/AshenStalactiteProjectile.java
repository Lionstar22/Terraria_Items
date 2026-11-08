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

public class AshenStalactiteProjectile extends RougeProjectiles {

    private final int stealthDur=40;
    private final float stealthSpeed=0.5f;
    private final int stealthPeirce =1;
    private final float stealthGrav=0.01f;

    public AshenStalactiteProjectile(Plugin plugin) {
        super(plugin, 0,  "ashen_stalactite", "AshenStalactiteProjectile",0,0, DamageType.TRIDENT,null);
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

    public void createStalactite(Player player,float speed,float weaponDamage,float duration,double currentStealth){
        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.3));

        Vector dir = player.getEyeLocation().getDirection();

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
        moveStalactite(player,weaponDamage,duration,proj,dir,currentStealth);
    }

    private void moveStalactite(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir,double currentStealth){
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

            RayTraceResult result= player.getWorld().rayTrace(now,direction[0],dist, FluidCollisionMode.NEVER,true,0.1, e -> (e.getType() != proj.getType())&&(e!=player));
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
                        target.damage((damage+weaponDamage+getStealthDamage(weaponDamage+damage,currentStealth)),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(temp);
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
        }, 1L, 1L);
    }

    public void createStealthStalactite(Player player,float weaponDamage,double currentStealth){
        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.3));

        Vector dir = player.getEyeLocation().getDirection();

        dir.normalize().multiply(stealthSpeed);

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
        moveStealthStalactite(player,weaponDamage,stealthDur,proj,dir,currentStealth);
    }

    private void moveStealthStalactite(Player player,float weaponDamage,float duration,ItemDisplay proj, Vector dir,double currentStealth){
        final int[] tick = {0};
        final int[] enemiesHit = {0};
        final Vector[] direction = {dir};
        ArrayList<Entity> hitEntities=new ArrayList<>();

        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            if (proj.isDead()) {
                task.cancel();
                hitEntities.clear();
                return;
            }

            tick[0]++;
            if (tick[0] >= duration) {
                makeDebris(player,proj.getLocation(),direction[0],currentStealth,null);
                proj.remove();
                task.cancel();
                hitEntities.clear();
                return;
            }

            if (tick[0] >= 3) {
                direction[0] = new Vector(direction[0].getX(), direction[0].getY() - stealthGrav, direction[0].getZ());
            }

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
                        target.damage((damage+weaponDamage+getStealthDamage(weaponDamage+damage,currentStealth)),source);
                        hitEntityEffect(target,player);
                        target.setMaximumNoDamageTicks(temp);
                        hitEntities.add(target);
                    }
                    if(enemiesHit[0] >=stealthPeirce) {
                        makeDebris(player,proj.getLocation(),direction[0],currentStealth,hitEntities);
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
        }, 1L, 1L);
    }

    private void makeDebris(Player player,Location location, Vector dir,Double currentStealth,ArrayList<Entity> hitEntities){
        for(int i=0;i<6;i++){
            new AshenDebris(plugin).makeDebris(player,location,dir,currentStealth,hitEntities);
        }
    }

}
