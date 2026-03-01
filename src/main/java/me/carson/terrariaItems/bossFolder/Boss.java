package me.carson.terrariaItems.bossFolder;

import me.carson.terrariaItems.accesoryFolder.accessories.BlizzardInABottle;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.SnowballCannon;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords.IceBlade;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Boss {

    protected final Plugin plugin;
    protected final double health;
    protected final EntityType type;
    protected final String name;
    protected final double range;

    public Boss(Plugin plugin, double health, EntityType type, String name, double range){
        this.plugin=plugin;
        this.health = health;
        this.type = type;
        this.name = name;
        this.range = range;
    }

    public Player getRandomPlayer(List<Player> players) {
        if (players == null || players.isEmpty()) return null;
        Random random = new Random();
        return players.get(random.nextInt(players.size()));
    }

    public abstract void summonBoss(Player player);

    public abstract void startAttacks(LivingEntity shooter);

    public void attachBossBar(LivingEntity entity, BossBar bar) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead()) {
                    bar.removeAll();
                    bar.setVisible(false);
                    cancel();
                    return;
                }

                // Update health %
                double health = entity.getHealth();
                double max = entity.getAttribute(Attribute.MAX_HEALTH).getValue();
                bar.setProgress(Math.max(0, health / max));

                // Update name
                bar.setTitle(entity.getCustomName() != null ? entity.getCustomName() : entity.getName());

                // Add/remove nearby players
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getWorld() != entity.getWorld()) {
                        bar.removePlayer(p);
                        continue;
                    }

                    if (p.getLocation().distanceSquared(entity.getLocation()) < range * range) {
                        bar.addPlayer(p);
                    } else {
                        bar.removePlayer(p);
                    }
                }
            }
        }.runTaskTimer(plugin, 1, 5); // update every 5 ticks
    }


}
