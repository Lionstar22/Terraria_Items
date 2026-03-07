package me.carson.terrariaItems.enemiesFolder.enemies;

import me.carson.terrariaItems.armourFolder.armors.possessedArmor.*;
import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.plugin.Plugin;

public class PossessedArmorEnemy implements Listener {

    private final WorldDataHandler instance=WorldDataHandler.getInstance();
    private final Plugin plugin;

    public PossessedArmorEnemy(Plugin plugin1){
        plugin=plugin1;
    }

    /*
    @EventHandler
    public void onZombieSpawn(CreatureSpawnEvent event){
        if(!(event.getEntity() instanceof Zombie zombie)){return;}
        if(!instance.getHardmode()){return;}
        if(Math.random()<.5){return;}
        zombie.setCustomName("Possessed Armor");
        zombie.setCustomNameVisible(true);
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
     */
}
