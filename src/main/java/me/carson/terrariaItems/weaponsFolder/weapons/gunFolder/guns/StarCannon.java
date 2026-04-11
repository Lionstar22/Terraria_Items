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

public class StarCannon extends Gun {

    public StarCannon(Plugin plugin) {
        super(plugin,"star_cannon.name","#96FF96", Material.RED_DYE,"star_cannon","StarCannon",5,3,10,0,200,"star_cannon.lore");
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
        return new StarCannon(plugin).createItem();
    }

}
