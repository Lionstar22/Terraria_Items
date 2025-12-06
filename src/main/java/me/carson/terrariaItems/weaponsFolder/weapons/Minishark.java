package me.carson.terrariaItems.weaponsFolder.weapons;

import me.carson.terrariaItems.materialsFolder.materials.MusketBall;
import me.carson.terrariaItems.projectilesFolder.projectiles.BulletProjectile;
import me.carson.terrariaItems.weaponsFolder.Weapon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Minishark extends Weapon {

    public Minishark(Plugin plugin) {
        super(plugin,"Minishark","#96FF96", Material.IRON_INGOT,"minishark","Minishark",2, new ArrayList<>(List.of(ChatColor.GRAY+"33% chance to save ammo",ChatColor.GRAY+"Half shark, half gun, completely awesome")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(player.getInventory().contains(MusketBall.getItem(plugin))){
            if(Math.random()<0.66){
                player.getInventory().removeItem(MusketBall.getItem(plugin));
            }
            player.getWorld().playSound(player.getLocation(),"terraria:gun_shoot", 1.0F, 1.0F);
            new BulletProjectile(plugin).createProjectile(player);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Minishark(plugin).createItem();
    }
}
