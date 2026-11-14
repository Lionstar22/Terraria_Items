package me.carson.terrariaItems.enemiesFolder.enemies.Casters;

import me.carson.terrariaItems.enemiesFolder.CustomAIs.CasterAI;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.BurningSphere;
import me.carson.terrariaItems.miscFolder.hats.FireImpHat;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class FireImp extends CasterAI {


    public FireImp(Plugin plugin) {
        super(plugin);
    }

    public void spawnFireImp(Zombie zombie) {
        zombie.setCustomName("Fire Imp");
        zombie.setCustomNameVisible(false);
        zombie.getAttribute(Attribute.MAX_HEALTH).setBaseValue(30);
        zombie.setHealth(30);
        zombie.setCanPickupItems(false);
        zombie.setInvisible(true);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        zombie.getPersistentDataContainer().set(key, PersistentDataType.STRING,"FireImp");
        zombie.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0);
        zombie.getAttribute(Attribute.SCALE).setBaseValue(0.6);
        EntityEquipment equipment = zombie.getEquipment();
        equipment.setHelmet(FireImpHat.getItem(plugin));
        equipment.setChestplate(null);
        equipment.setLeggings(null);
        equipment.setBoots(null);
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(0f);
        startCasterAI(zombie, new BurningSphere(plugin), "terraria:magic_use");
    }
}
