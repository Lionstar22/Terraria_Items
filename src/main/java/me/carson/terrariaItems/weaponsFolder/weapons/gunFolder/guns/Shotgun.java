package me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns;


import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.Projectile;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.Gun;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class Shotgun extends Gun{
    ProjectileManager projectileManagerInstance= ProjectileManager.getInstance();
    MaterialManager materialManagerInstance = MaterialManager.getInstance();
    NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");

    public Shotgun(Plugin plugin) {
        super(plugin,"Shotgun","#FF9696", Material.ARMADILLO_SCUTE,"shotgun","Shotgun",30,3,3.5f,0.1f,100, new ArrayList<>(List.of(ChatColor.GRAY+"Fires a spread of bullets",ChatColor.GRAY+"3.5 Damage")));
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
                for(int i =0;i<5;i++){
                    projectile.createProjectile(player,speed,damage,spread,duration);
                }
                player.getInventory().removeItem(bullet);
                player.getWorld().playSound(player.getLocation(),"terraria:shotgun_shoot", 1.0F, 1.0F);
                break;
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {return new Shotgun(plugin).createItem();}


}
