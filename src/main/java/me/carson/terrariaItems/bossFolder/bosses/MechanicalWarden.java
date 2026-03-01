package me.carson.terrariaItems.bossFolder.bosses;

import me.carson.terrariaItems.accesoryFolder.accessories.BlizzardInABottle;
import me.carson.terrariaItems.bossFolder.Boss;
import me.carson.terrariaItems.bossProjectilesFolder.bossProjectiles.WardenFlame;
import me.carson.terrariaItems.bossProjectilesFolder.bossProjectiles.WardenLaser;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.SnowballCannon;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords.IceBlade;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MechanicalWarden extends Boss {

    public MechanicalWarden(Plugin plugin){
        super(plugin,750, EntityType.WARDEN,"Mechanical Warden",150);
    }

    @Override
    public void summonBoss(Player player) {
        World world=player.getWorld();
        Warden boss= (Warden) world.spawnEntity(getSpawnPoint(player), type);
        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
        boss.setHealth(health);
        boss.setCustomName(name);
        boss.setCustomNameVisible(true);
        boss.setAnger(player,150);
        NamespacedKey key = new NamespacedKey(plugin, "BossWarden");
        boss.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        BossBar bar = Bukkit.createBossBar(name, BarColor.RED, BarStyle.SOLID);
        attachBossBar(boss,bar);
        startAttacks(boss);
    }

    @Override
    public void startAttacks(LivingEntity shooter) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (shooter.isDead()){return;}
            List<Player> playerList = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getWorld() != shooter.getWorld()) continue;
                if (p.getLocation().distanceSquared(shooter.getLocation()) <= range * range) {
                    playerList.add(p);
                }
            }
            if (!playerList.isEmpty()){
                fireAttack(shooter,getRandomPlayer(playerList));
            }else {
                shooter.remove();
            }
        }, 20L, 100L);
    }

    public void fireAttack(LivingEntity shooter, Player target){
        double distance = shooter.getLocation().distance(target.getLocation());
        if(distance>10){
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    new WardenLaser(plugin).createBossProjectile(shooter,target,2,10,0.05f,100);
                    shooter.getWorld().playSound(shooter.getLocation(), "terraria:laser", 3.0F, 1.0F);

                    count++;
                    if (count >= 3) {
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0L, 10L);
        }else {
            new WardenFlame(plugin).createBossProjectile(shooter,target,0.5f,20,0,100);
            shooter.getWorld().playSound(shooter.getLocation(), "terraria:flame", 2F, 1.0F);
        }
    }

    public Location getSpawnPoint(Player player){
        Location center =player.getLocation();
        Vector dir = player.getEyeLocation().getDirection().normalize();
        dir.setY(0);
        Location result=center.add(dir.multiply(20));
        return null;
    }

}
