package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;

import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.Projectile;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.projectilesFolder.projectiles.OnyxCrystal;
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

public class OnyxBlaster extends Gun {

    ProjectileManager projectileManagerInstance= ProjectileManager.getInstance();
    MaterialManager materialManagerInstance = MaterialManager.getInstance();
    NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");

    public OnyxBlaster(Plugin plugin) {
        super(plugin,"Onyx Blaster","#FF9696", Material.ARMADILLO_SCUTE,"onyx_blaster","OnyxBlaster",30,2.5f,4f,0.1f,100, new ArrayList<>(List.of(ChatColor.GRAY+"Fires a spread of bullets and an onyx crystal that explodes upon impact",ChatColor.GRAY+"4 Damage")));
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
                for(int i =0;i<4;i++){
                    projectile.createProjectile(player,speed,damage,spread,duration);
                }
                new OnyxCrystal(plugin).createProjectile(player,speed+0.5f,damage,0,duration);
                player.getInventory().removeItem(bullet);
                player.getWorld().playSound(player.getLocation(),"terraria:shotgun_shoot", 1.0F, 1.0F);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new OnyxBlaster(plugin).createItem();
    }
}
