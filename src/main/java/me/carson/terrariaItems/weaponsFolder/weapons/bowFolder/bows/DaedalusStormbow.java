package me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows;

import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.Bow;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DaedalusStormbow extends Bow {

    public DaedalusStormbow(Plugin plugin) {
        super(plugin,"Daedalus Stormbow","#D2A0FF", Material.BOW,"daedalus_stormbow","DaedalusStormbow",0,0,0,0, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots arrows from the sky")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DaedalusStormbow(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player){
        if (!player.getInventory().contains(Material.ARROW)) {return;}

        World world = player.getWorld();
        RayTraceResult result = world.rayTrace(
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                150,
                FluidCollisionMode.NEVER,
                true,
                0.1f,
                e ->e!=player
        );

        if (result == null) {
            return;
        }

        Vector hit = result.getHitPosition();
        Location target = new Location(world, hit.getX(), hit.getY()+20, hit.getZ());
        player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
        for(int i =0;i<3;i++){
            Arrow arrow=world.spawnArrow(target,new Vector(0,-1,0),2,10);
            arrow.setDamage(5.0);
        }
    }
}
