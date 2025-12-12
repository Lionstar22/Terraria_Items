package me.carson.terrariaItems.projectilesFolder;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Projectile implements Listener {

    protected final Plugin plugin;
    protected final int damage;
    protected final String texture;
    protected final String id;
    protected final int peirce;
    protected final DamageType damageType;

    public Projectile(Plugin plugin, int damage, String texture, String id, int peirce, DamageType damageType) {
        this.plugin = plugin;
        this.texture = texture;
        this.id = id;
        this.damage=damage;
        this.peirce = peirce;
        this.damageType = damageType;
    }

    public void createProjectile(Player player,float speed,float weaponDamage, float spread,float duration){
        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection().normalize().multiply(0.1));

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
        proj.setInterpolationDuration(3);
        proj.setTeleportDuration(1);

        final int[] tick = {0};

        final int[] enemiesHit = {0};

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
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,now.getDirection(),dist,FluidCollisionMode.NEVER,true,0.1,e -> (e.getType() != proj.getType())&&(e!=player));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable()){
                        hitBlockEffect(result.getHitBlock());
                        proj.remove();
                        task.cancel();
                        return;
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        target.setNoDamageTicks(0);
                        target.setMaximumNoDamageTicks(0);
                        //DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(target).build();
                        target.damage((damage+weaponDamage),player);
                        hitEntityEffect(target);
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

            proj.teleport(next);
        }, 1L, 1L);

    }

    public void createFallingProjectile(Player player,float speed,float weaponDamage, float spread,float duration,float height,Location location){
        if(spread==0){return;}
        float valueX = ThreadLocalRandom.current().nextFloat(-spread, spread);
        float valueZ = ThreadLocalRandom.current().nextFloat(-spread, spread);

        Location loc = location.clone().add(valueX,height,valueZ);

        Vector dir = location.toVector().subtract(loc.toVector());

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
        proj.setInterpolationDuration(3);
        proj.setTeleportDuration(1);

        final int[] tick = {0};

        final int[] enemiesHit = {0};

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
            float dist= (float) now.distance(next);

            RayTraceResult result= player.getWorld().rayTrace(now,now.getDirection(),dist,FluidCollisionMode.NEVER,true,0.1,e -> (e.getType() != proj.getType())&&(e!=player));
            if(result!=null){
                if(result.getHitBlock()!=null){
                    if(!result.getHitBlock().isPassable()){
                        hitBlockEffect(result.getHitBlock());
                        proj.remove();
                        task.cancel();
                        return;
                    }
                }
                if(result.getHitEntity()!=null){
                    if(result.getHitEntity() instanceof LivingEntity target){
                        target.setNoDamageTicks(0);
                        target.setMaximumNoDamageTicks(0);
                        //DamageSource source = DamageSource.builder(damageType).withCausingEntity(player).withDirectEntity(target).build();
                        target.damage((damage+weaponDamage),player);
                        hitEntityEffect(target);
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

            proj.teleport(next);
        }, 1L, 1L);

    }

    public abstract void hitEntityEffect(LivingEntity entity);

    public abstract void hitBlockEffect(Block block);

}
