package me.carson.terrariaItems.enemiesFolder.CustomAIs;

import me.carson.terrariaItems.TILangManager;
import me.carson.terrariaItems.enemyProjectilesFolder.EnemyProjectile;
import me.carson.terrariaItems.handlers.WorldDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CasterAI {

    protected final Plugin plugin;
    public final TILangManager lang =TILangManager.getInstance();
    public final WorldDataHandler instance=WorldDataHandler.getInstance();

    public CasterAI(Plugin plugin){
        this.plugin = plugin;
    }



    public void startCasterAI(LivingEntity shooter, EnemyProjectile enemyProjectile, String sound) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (shooter.isDead()){
                return;
            }
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
                    fireAttack(shooter,target,enemyProjectile);
                    shooter.getWorld().playSound(shooter.getLocation(), sound, 1.0F, 1.0F);
                }
                for(int i=0;i<5;i++){
                    Location newLoc=teleport(target);
                    if(newLoc!=null){
                        shooter.teleport(newLoc);
                        break;
                    }
                }

            }
        }, 0L, 140L);
    }

    private Player getRandomPlayer(List<Player> players) {
        if (players == null || players.isEmpty()) return null;
        Random random = new Random();
        return players.get(random.nextInt(players.size()));
    }

    private void fireAttack(LivingEntity shooter, Player target,EnemyProjectile enemyProjectile){
        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (shooter.isDead()){
                    cancel();
                }
                enemyProjectile.shootProj(shooter,target);
                shooter.getWorld().playSound(shooter.getLocation(), "terraria:magic_use", 2.0F, 1.0F);
                count++;
                if (count >= 3) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 40L, 20L);
    }

    private Location teleport(Player player){
        World world=player.getWorld();
        Location playerLoc=player.getLocation();
        int range=30;
        double newX= playerLoc.getX()+((Math.random()-0.5)*range);
        double newY= playerLoc.getY()-3;
        double newZ= playerLoc.getZ()+((Math.random()-0.5)*range);
        Location newLoc= new Location(world,newX,newY,newZ);
        for(int i=0;i<6;i++){
            Block block= world.getBlockAt(newLoc);
            if(isValidTeleport(block)){
                return newLoc;
            }
            newLoc.setY(newLoc.getY()+1);
        }
        return null;
    }

    private boolean isValidTeleport(Block block){
        return block.getRelative(BlockFace.DOWN).getType().isSolid() && !block.getRelative(BlockFace.UP).getType().isSolid() && !block.getType().isSolid();
    }

}
