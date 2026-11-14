package me.carson.terrariaItems.enemiesFolder.enemies.Casters;

import me.carson.terrariaItems.armorFolder.armors.runeWizardArmor.RuneWizardChestplate;
import me.carson.terrariaItems.armorFolder.armors.runeWizardArmor.RuneWizardLeggings;
import me.carson.terrariaItems.enemiesFolder.CustomAIs.CasterAI;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.RuneWizardBolt;
import me.carson.terrariaItems.miscFolder.hats.RuneWizardHat;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class RuneWizard extends CasterAI implements Listener {

    public RuneWizard(Plugin plugin) {
        super(plugin);
    }

    public void spawnRuneWizard(Skeleton skeleton){
        skeleton.setCustomName(lang.get("enemies","rune_wizard.name"));
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(80);
        skeleton.setHealth(80);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"RuneWizard");
        skeleton.setCanPickupItems(false);
        skeleton.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0);
        EntityEquipment equipment=skeleton.getEquipment();
        equipment.setHelmet(RuneWizardHat.getItem(plugin));
        equipment.setChestplate(RuneWizardChestplate.getItem(plugin));
        equipment.setLeggings(RuneWizardLeggings.getItem(plugin));
        equipment.setBoots(null);
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(0f);
        equipment.setChestplateDropChance(0f);
        equipment.setLeggingsDropChance(0f);
        startCasterAI(skeleton,new RuneWizardBolt(plugin),"terraria:magic_use");
    }
}
