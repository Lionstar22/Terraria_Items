package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.Projectile;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class SniperRifle extends Gun implements Listener {

    ProjectileManager projectileManagerInstance= ProjectileManager.getInstance();
    MaterialManager materialManagerInstance = MaterialManager.getInstance();
    NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");

    public SniperRifle(Plugin plugin) {
        super(plugin,"Sniper Rifle","#FFFF0A", Material.BLACK_DYE,"sniper_rifle","SniperRifle",40,6,30,0,250, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots a powerful, high velocity bullet",ChatColor.GRAY+"Crouch to zoom",ChatColor.GRAY+"30 Damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        ItemStack bullet;
        for(ItemStack itemInv : player.getInventory().getStorageContents()){
            if(materialManagerInstance.getBulletItem(itemInv)!=null){
                bullet=materialManagerInstance.getBulletItem(itemInv);
                Projectile projectile = projectileManagerInstance.getBullet(bullet.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING));
                projectile.createProjectile(player,speed,damage,spread,duration);
                player.getInventory().removeItem(bullet);
                player.getWorld().playSound(player.getLocation(),"terraria:gun_shoot_3", 1.0F, 1.0F);
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
