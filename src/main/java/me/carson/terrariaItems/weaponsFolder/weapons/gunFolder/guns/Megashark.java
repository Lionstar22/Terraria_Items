package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.materialsFolder.materials.MusketBall;
import me.carson.terrariaItems.projectilesFolder.projectiles.BulletProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Megashark extends Gun {

    public Megashark(Plugin plugin) {
        super(plugin,"Megashark","#FF96FF", Material.PRISMARINE_CRYSTALS,"megashark","Megashark",0,3,6,0.05f,100, new ArrayList<>(List.of(ChatColor.GRAY+"50% chance to save ammo",ChatColor.GRAY+"Minishark's older brother",ChatColor.GRAY+"6 Damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (new MusketBall(plugin).isThisItem(itemInv)) {
                if(Math.random()<0.5){
                    player.getInventory().removeItem(MusketBall.getItem(plugin));
                }
                player.getWorld().playSound(player.getLocation(),"terraria:gun_shoot", 1.0F, 1.0F);
                new BulletProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Megashark(plugin).createItem();
    }

}
