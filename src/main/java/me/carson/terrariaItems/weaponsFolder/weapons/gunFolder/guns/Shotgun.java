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

public class Shotgun extends Gun {

    public Shotgun(Plugin plugin) {
        super(plugin,"Shotgun","#FF9696", Material.GOLD_NUGGET,"shotgun","Shotgun",30,3,4,0.1f,100, new ArrayList<>(List.of(ChatColor.GRAY+"Fires a spread of bullets",ChatColor.GRAY+"4 damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (new MusketBall(plugin).isThisItem(itemInv)) {
                for(int i =0;i<5;i++){
                    new BulletProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
                }
                player.getInventory().removeItem(MusketBall.getItem(plugin));
                player.getWorld().playSound(player.getLocation(),"terraria:shotgun_shoot", 1.0F, 1.0F);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Shotgun(plugin).createItem();
    }

}
