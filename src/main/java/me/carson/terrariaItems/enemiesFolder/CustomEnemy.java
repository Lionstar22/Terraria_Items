package me.carson.terrariaItems.enemiesFolder;

import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class CustomEnemy {

    protected final Plugin plugin;

    public CustomEnemy(Plugin plugin){
        this.plugin = plugin;
    }

    public void startAttacks(LivingEntity shooter, EnemyProjectile enemyProjectile,String sound) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (shooter.isDead()){return;}
            List<Player> playerList = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getWorld() != shooter.getWorld()) continue;
                if (p.getLocation().distanceSquared(shooter.getLocation()) <= 40 * 40) {
                    playerList.add(p);
                }
            }
            if (!playerList.isEmpty()){
                Player target=getRandomPlayer(playerList);
                if(shooter.hasLineOfSight(target)){
                    enemyProjectile.createBossProjectile(shooter,target,0,0,0,100);
                    shooter.getWorld().playSound(shooter.getLocation(), sound, 3.0F, 1.0F);
                }

            }
        }, 60L, 60L);
    }

    public Player getRandomPlayer(List<Player> players) {
        if (players == null || players.isEmpty()) return null;
        Random random = new Random();
        return players.get(random.nextInt(players.size()));
    }

}
