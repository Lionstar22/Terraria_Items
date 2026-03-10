package me.carson.terrariaItems.enemiesFolder.enemies;

import me.carson.terrariaItems.armourFolder.armors.frozenZombieArmor.FrozenZombieChestplate;
import me.carson.terrariaItems.armourFolder.armors.possessedArmor.*;
import me.carson.terrariaItems.armourFolder.armors.raincoatZombieArmor.RaincoatZombieChestplate;
import me.carson.terrariaItems.enemiesFolder.CustomEnemy;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.MermanBolt;
import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import me.carson.terrariaItems.miscFolder.hats.FrozenZombieHat;
import me.carson.terrariaItems.miscFolder.hats.RaincoatZombieHat;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class CustomZombies extends CustomEnemy implements Listener {

    private final WorldDataHandler instance=WorldDataHandler.getInstance();
    private static final Set<Biome> snowyBiomes = Set.of(Biome.SNOWY_TAIGA,Biome.JAGGED_PEAKS,Biome.FROZEN_PEAKS,Biome.GROVE,Biome.SNOWY_SLOPES,Biome.FROZEN_RIVER,Biome.SNOWY_PLAINS,Biome.ICE_SPIKES,Biome.SNOWY_BEACH);

    public CustomZombies(Plugin plugin){
        super(plugin);
    }


    @EventHandler
    public void onZombieSpawn(CreatureSpawnEvent event){
        if (event.getEntityType() != EntityType.ZOMBIE) return;
        Zombie zombie = (Zombie) event.getEntity();
        Location location = zombie.getLocation();
        Boolean isRaining= zombie.getWorld().hasStorm();
        if(location.getY()<60){return;}
        if(instance.getHardmode()){
            if(Math.random()<0.5){
                spawnPossessedArmor(zombie);
            }else if(snowyBiomes.contains(location.getBlock().getBiome())){
                spawnFrozenZombie(zombie);
            }else if(isRaining){
                spawnRaincoatZombie(zombie);
            }
        }else if(snowyBiomes.contains(location.getBlock().getBiome())){
            spawnFrozenZombie(zombie);
        }else if(isRaining){
            spawnRaincoatZombie(zombie);
        }
    }

    public void spawnPossessedArmor(Zombie zombie){
        zombie.setCustomName("Possessed Armor");
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        zombie.getPersistentDataContainer().set(key, PersistentDataType.STRING,"PossessedArmor");
        zombie.setCanPickupItems(false);
        zombie.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(6);
        EntityEquipment equipment=zombie.getEquipment();
        equipment.setHelmet(PossessedHelmet.getItem(plugin));
        equipment.setChestplate(PossessedChestplate.getItem(plugin));
        equipment.setLeggings(PossessedLeggings.getItem(plugin));
        equipment.setBoots(PossessedBoots.getItem(plugin));
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(0f);
        equipment.setChestplateDropChance(0f);
        equipment.setLeggingsDropChance(0f);
        equipment.setBootsDropChance(0f);
        zombie.setInvisible(true);
    }

    public void spawnFrozenZombie(Zombie zombie){
        zombie.setCustomName("Frozen Zombie");
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        zombie.getPersistentDataContainer().set(key, PersistentDataType.STRING,"FrozenZombie");
        zombie.setCanPickupItems(false);
        EntityEquipment equipment=zombie.getEquipment();
        equipment.setHelmet(FrozenZombieHat.getItem(plugin));
        equipment.setChestplate(FrozenZombieChestplate.getItem(plugin));
        equipment.setHelmetDropChance(0f);
        equipment.setChestplateDropChance(0f);
    }

    public void spawnRaincoatZombie(Zombie zombie){
        zombie.setCustomName("Raincoat Zombie");
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        zombie.getPersistentDataContainer().set(key, PersistentDataType.STRING,"RaincoatZombie");
        zombie.setCanPickupItems(false);
        EntityEquipment equipment=zombie.getEquipment();
        equipment.setHelmet(RaincoatZombieHat.getItem(plugin));
        equipment.setChestplate(RaincoatZombieChestplate.getItem(plugin));
        equipment.setHelmetDropChance(0f);
        equipment.setChestplateDropChance(0f);
    }
}
