package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SandstormInABottle extends Accessory implements Listener {

    private boolean jumped=false;

    public SandstormInABottle(Plugin plugin){
        super(plugin,"Sandstorm in a Bottle","#96FF96", Material.FIREWORK_STAR,"sandstorm_in_a_bottle","SandstormInABottle",new ArrayList<>(List.of(ChatColor.GRAY+"Allows the holder to do an improved double jump", ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {
    }

    @Override
    public void deactivateEffect(Player player) {
    }

    @EventHandler
    public void onDoubleJump(PlayerInputEvent event){
        if(!event.getInput().isJump()){return;}
        Player player = event.getPlayer();
        if(((Entity)player).isOnGround()){
            return;
        }
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR){return;}
        if(jumped){return;}
        if(!hasItem(player)){return;}

        player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 0.5F, 1.0F);
        final int[] timeLeft = {15};
        Bukkit.getScheduler().runTaskTimer(plugin, task -> {

            // Do something each tick (or each cycle)
            // Example:
            player.setVelocity(player.getVelocity().setY(0.4));
            player.getWorld().spawnParticle(Particle.DUST_PLUME, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);

            timeLeft[0]--;

            if (timeLeft[0] <= 0) {
                task.cancel();
            }

        }, 0L, 1L);

        jumped=true;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(((Entity)player).isOnGround()){
            jumped=false;
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SandstormInABottle(plugin).createItem();
    }

}
