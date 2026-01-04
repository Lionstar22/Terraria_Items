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

public class Handgun extends Gun {

    ProjectileManager projectileManagerInstance= ProjectileManager.getInstance();
    MaterialManager materialManagerInstance = MaterialManager.getInstance();
    NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");

    public Handgun(Plugin plugin) {
        super(plugin,"Handgun","#96FF96",Material.WHITE_DYE,"handgun","Handgun",10,3,4,0,100, new ArrayList<>(List.of(ChatColor.GRAY+"Shoots Bullets",ChatColor.GRAY+"4 Damage")));
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
                player.getWorld().playSound(player.getLocation(),"terraria:gun_shoot_2", 1.0F, 1.0F);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Handgun(plugin).createItem();
    }

}
