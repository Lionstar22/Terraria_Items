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

public class Shotgun extends Weapon {

    public Shotgun(Plugin plugin) {
        super(plugin,"Shotgun","#FF9696",4, 0.15f, Material.RAW_GOLD,"shotgun","Shotgun",30, new ArrayList<>(List.of(ChatColor.GRAY+"Fires a spread of bullets")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (new MusketBall(plugin).isThisItem(itemInv)) {
                for(int i =0;i<5;i++){
                    new BulletProjectile(plugin).createProjectile(player,super.damage,super.spread);
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
