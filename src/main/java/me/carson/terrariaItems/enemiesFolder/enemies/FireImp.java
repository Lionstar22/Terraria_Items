package me.carson.terrariaItems.enemiesFolder.enemies;

import me.carson.terrariaItems.armorFolder.armors.timArmor.TimChestplate;
import me.carson.terrariaItems.armorFolder.armors.timArmor.TimLeggings;
import me.carson.terrariaItems.armorFolder.armors.timArmor.WizardHat;
import me.carson.terrariaItems.enemiesFolder.CustomAIs.CasterAI;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.BurningSphere;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.TimBolt;
import me.carson.terrariaItems.miscFolder.hats.FireImpHat;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class FireImp extends CasterAI implements Listener {


    public FireImp(Plugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPiglinSpawn(CreatureSpawnEvent event){
        if (event.getEntityType() != EntityType.ZOMBIFIED_PIGLIN) return;

        if(instance.getPreHardmodeEnabled()){
            PigZombie pigZombie = (PigZombie) event.getEntity();
            Location loc=pigZombie.getLocation();
            pigZombie.remove();
            Zombie zombie=pigZombie.getWorld().spawn(loc, Zombie.class);
            spawnFireImp(zombie);
        }

    }

    public void spawnFireImp(Zombie zombie) {
        zombie.setCustomName("Fire Imp");
        zombie.setCustomNameVisible(false);
        zombie.getAttribute(Attribute.MAX_HEALTH).setBaseValue(30);
        zombie.setHealth(30);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        zombie.getPersistentDataContainer().set(key, PersistentDataType.STRING, "FireImp");
        zombie.setCanPickupItems(false);
        zombie.setInvisible(true);
        zombie.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0);
        zombie.getAttribute(Attribute.SCALE).setBaseValue(0.6);
        EntityEquipment equipment = zombie.getEquipment();
        equipment.setHelmet(FireImpHat.getItem(plugin));
        equipment.setChestplate(null);
        equipment.setLeggings(null);
        equipment.setBoots(null);
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(0f);
        startAttacks(zombie, new BurningSphere(plugin), "terraria:magic_use");
    }
}
