package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.materialsFolder.materials.MusketBall;
import me.carson.terrariaItems.projectilesFolder.projectiles.BulletProjectile;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class SniperRifle extends Gun implements Listener {

    public SniperRifle(Plugin plugin) {
        super(plugin,"Sniper Rifle","#FFFF0A", Material.EMERALD,"sniper_rifle","SniperRifle",40,6,30,0,250, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots a powerful, high velocity bullet",ChatColor.GRAY+"Crouch to zoom",ChatColor.GRAY+"25 Damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        for (ItemStack itemInv : player.getInventory().getStorageContents()) {
            if (new MusketBall(plugin).isThisItem(itemInv)) {
                player.getInventory().removeItem(MusketBall.getItem(plugin));
                player.getWorld().playSound(player.getLocation(),"terraria:gun_shoot_3", 1.0F, 1.0F);
                new BulletProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
                break;
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (!event.isSneaking()) {
            player.removePotionEffect(PotionEffectType.SLOWNESS);
        }
        if(isThisItem(player.getInventory().getItemInMainHand())){
            if (event.isSneaking()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 999999, 20, false, false, false));
            }
            else {
                player.removePotionEffect(PotionEffectType.SLOWNESS);
            }
        }

    }

    public static ItemStack getItem(Plugin plugin) {
        return new SniperRifle(plugin).createItem();
    }
}
