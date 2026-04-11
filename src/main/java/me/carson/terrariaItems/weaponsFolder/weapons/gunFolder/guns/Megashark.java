package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

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

import java.util.ArrayList;
import java.util.List;

public class Megashark extends Gun {

    ProjectileManager projectileManagerInstance= ProjectileManager.getInstance();
    MaterialManager materialManagerInstance = MaterialManager.getInstance();
    NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");

    public Megashark(Plugin plugin) {
        super(plugin,"megashark.name","#FF96FF", Material.PRISMARINE_CRYSTALS,"megashark","Megashark",0,3,6,0.05f,100,"megashark.lore");
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
                if(Math.random()>0.5){
                    player.getInventory().removeItem(bullet);
                }
                player.getWorld().playSound(player.getLocation(),"terraria:gun_shoot", 1.0F, 1.0F);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Megashark(plugin).createItem();
    }

}
