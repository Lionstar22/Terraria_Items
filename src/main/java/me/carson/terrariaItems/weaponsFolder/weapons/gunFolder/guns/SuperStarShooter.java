package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.projectilesFolder.projectiles.StarCannonStar;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SuperStarShooter extends Gun {

    public SuperStarShooter(Plugin plugin) {
        super(plugin,"Super Star Shooter","#FFC896", Material.YELLOW_DYE,"super_star_shooter","SuperStarShooter",5,3.5f,20,0,200, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots fallen stars",ChatColor.GRAY+"Pierces infinitely",ChatColor.GRAY+"20 Damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        for(ItemStack itemInv : player.getInventory().getStorageContents()){
            if(new FallenStar(plugin).isThisItem(itemInv)){
                new StarCannonStar(plugin).createProjectile(player,speed,damage,spread,duration);
                player.getInventory().removeItem(FallenStar.getItem(plugin));
                player.getWorld().playSound(player.getLocation(),"terraria:falling_star", 0.75F, 1.0F);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SuperStarShooter(plugin).createItem();
    }

}
