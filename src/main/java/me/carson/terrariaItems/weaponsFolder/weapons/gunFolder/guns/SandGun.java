package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.projectilesFolder.projectiles.RedSandBall;
import me.carson.terrariaItems.projectilesFolder.projectiles.SandBall;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SandGun extends Gun {

    public SandGun(Plugin plugin) {
        super(plugin,"Sand Gun","#9696FF", Material.PAPER,"sand_gun","SandGun",5,2f,7,0,100, new ArrayList<>(List.of(ChatColor.GRAY+"This is a good idea!",ChatColor.GRAY+"7 Damage")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SandGun(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if (player.getInventory().contains(Material.RED_SAND)) {
            player.getInventory().removeItem(new ItemStack(Material.RED_SAND, 1));
            new RedSandBall(plugin).createProjectile(player,speed,damage,spread,duration);
        } else if(player.getInventory().contains(Material.SAND)) {
            player.getInventory().removeItem(new ItemStack(Material.SAND, 1));
            new SandBall(plugin).createProjectile(player,speed,damage,spread,duration);
        }
    }

}
