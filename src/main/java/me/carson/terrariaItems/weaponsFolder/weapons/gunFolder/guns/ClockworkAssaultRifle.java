package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.enemyProjectilesFolder.bossProjectiles.WardenLaser;
import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.Projectile;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ClockworkAssaultRifle extends Gun {

    ProjectileManager projectileManagerInstance= ProjectileManager.getInstance();
    MaterialManager materialManagerInstance = MaterialManager.getInstance();
    NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");

    public ClockworkAssaultRifle(Plugin plugin) {
        super(plugin,"Clockwork Assault Rifle","#FF9696",Material.WHITE_DYE,"clockwork_assault_rifle","ClockworkAssaultRifle",10,3,6,0.05f,100, new ArrayList<>(List.of(ChatColor.GRAY+"Three round burst",ChatColor.GRAY+"Only the first shot consumes ammo",ChatColor.GRAY+"5 Damage")));
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
                player.getInventory().removeItem(bullet);
                player.getWorld().playSound(player.getLocation(),"terraria:clockwork_shoot", 1.5F, 1.0F);

                new BukkitRunnable() {
                    int count = 0;
                    @Override
                    public void run() {
                        projectile.createProjectile(player,speed,damage,spread,duration);
                        count++;
                        if (count >= 3) {
                            cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0L, 3L);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ClockworkAssaultRifle(plugin).createItem();
    }

}
