package me.carson.terrariaItems.enemiesFolder.enemies.Casters;

import me.carson.terrariaItems.armorFolder.armors.timArmor.TimChestplate;
import me.carson.terrariaItems.armorFolder.armors.timArmor.TimLeggings;
import me.carson.terrariaItems.armorFolder.armors.timArmor.WizardHat;
import me.carson.terrariaItems.enemiesFolder.CustomAIs.CasterAI;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.TimBolt;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class Tim extends CasterAI implements Listener {

    public Tim(Plugin plugin) {
        super(plugin);
    }

    public void spawnTim(Skeleton skeleton){
        skeleton.setCustomName("Tim");
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(40);
        skeleton.setHealth(40);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"Tim");
        skeleton.setCanPickupItems(false);
        skeleton.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0);
        EntityEquipment equipment=skeleton.getEquipment();
        equipment.setHelmet(WizardHat.getItem(plugin));
        equipment.setChestplate(TimChestplate.getItem(plugin));
        equipment.setLeggings(TimLeggings.getItem(plugin));
        equipment.setBoots(null);
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(1f);
        equipment.setChestplateDropChance(0f);
        equipment.setLeggingsDropChance(0f);
        startCasterAI(skeleton,new TimBolt(plugin),"terraria:magic_use");
    }
}
