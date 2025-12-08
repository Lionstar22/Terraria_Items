package me.carson.terrariaItems.weaponsFolder.weapons;


import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class HallowedRepeater extends Weapon {

    public HallowedRepeater(Plugin plugin) {
        super(plugin,"Hallowed Repeater","#FF9696",8, 0.2f,Material.CROSSBOW,"hallowed_repeater","HallowedRepeater",7, new ArrayList<>(List.of(ChatColor.GRAY+"Holy crossbow whose arrows illuminate thine target",ChatColor.GRAY+"Converts normal arrows to Spectral Arrows")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if (player.getInventory().contains(Material.ARROW)) {
            player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
            shootArrow(player);
        }else if (player.getInventory().contains(Material.SPECTRAL_ARROW)){
            player.getInventory().removeItem(new ItemStack(Material.SPECTRAL_ARROW, 1));
            shootArrow(player);
        }
    }

    public void shootArrow(Player player){
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection().normalize();
        loc.add(dir.multiply(1));
        World world= player.getWorld();
        world.playSound(loc, "terraria:repeater_use", 1.0F, 1.0F);
        SpectralArrow spectralArrow= world.spawnArrow(loc,dir, 5, super.spread, SpectralArrow.class);
        spectralArrow.setDamage(super.damage);
        spectralArrow.setShooter(player);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new HallowedRepeater(plugin).createItem();
    }
}
