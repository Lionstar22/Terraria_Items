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

public class Handgun extends Gun {

    public Handgun(Plugin plugin) {
        super(plugin,"Handgun","#96FF96",Material.RAW_IRON,"handgun","Handgun",10,3,4,0,100, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots Bullets")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (new MusketBall(plugin).isThisItem(itemInv)) {
                player.getInventory().removeItem(MusketBall.getItem(plugin));
                player.getWorld().playSound(player.getLocation(),"terraria:gun_shoot_2", 1.0F, 1.0F);
                new BulletProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Handgun(plugin).createItem();
    }

}
