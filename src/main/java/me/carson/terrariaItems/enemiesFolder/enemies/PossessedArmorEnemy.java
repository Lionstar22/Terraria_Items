package me.carson.terrariaItems.enemiesFolder.enemies;

import me.carson.terrariaItems.armourFolder.armors.possessedArmor.*;
import me.carson.terrariaItems.enemiesFolder.CustomEnemy;
import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class PossessedArmorEnemy extends CustomEnemy implements Listener {

    private final WorldDataHandler instance=WorldDataHandler.getInstance();

    public PossessedArmorEnemy(Plugin plugin){
        super(plugin,"Possessed Armor","PossessedArmor");
    }


    @EventHandler
    public void onZombieSpawn(CreatureSpawnEvent event){
        if (event.getEntityType() != EntityType.ZOMBIE) return;
        Zombie zombie = (Zombie) event.getEntity();
        if(!instance.getHardmode()){return;}
        if(Math.random()<.5){return;}
        zombie.setCustomName(name);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        zombie.getPersistentDataContainer().set(key, PersistentDataType.STRING,id);
        zombie.setCanPickupItems(false);
        EntityEquipment equipment=zombie.getEquipment();
        equipment.setHelmet(PossessedHelmet.getItem(plugin));
        equipment.setChestplate(PossessedChestplate.getItem(plugin));
        equipment.setLeggings(PossessedLeggings.getItem(plugin));
        equipment.setBoots(PossessedBoots.getItem(plugin));
        equipment.setHelmetDropChance(0f);
        equipment.setChestplateDropChance(0f);
        equipment.setLeggingsDropChance(0f);
        equipment.setBootsDropChance(0f);
        zombie.setInvisible(true);
    }

}
