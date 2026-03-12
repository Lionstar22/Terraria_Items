package me.carson.terrariaItems.enemiesFolder.enemies;

import me.carson.terrariaItems.armourFolder.armors.iceGolemArmor.IceGolemBoots;
import me.carson.terrariaItems.armourFolder.armors.iceGolemArmor.IceGolemChestplate;
import me.carson.terrariaItems.armourFolder.armors.iceGolemArmor.IceGolemLeggings;
import me.carson.terrariaItems.armourFolder.armors.timArmor.TimChestplate;
import me.carson.terrariaItems.armourFolder.armors.timArmor.TimLeggings;
import me.carson.terrariaItems.armourFolder.armors.undeadMinerArmor.UndeadMinerChestplate;
import me.carson.terrariaItems.enemiesFolder.CustomEnemy;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.IceGolemLaser;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.TimBolt;
import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import me.carson.terrariaItems.miscFolder.BasicItems.BonePickaxe;
import me.carson.terrariaItems.miscFolder.hats.IceGolemHat;
import me.carson.terrariaItems.miscFolder.hats.SkeletonArcherHat;
import me.carson.terrariaItems.miscFolder.hats.UndeadMinerHat;
import me.carson.terrariaItems.miscFolder.hats.WizardHat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class CustomSkeletons extends CustomEnemy implements Listener {

    private final WorldDataHandler instance=WorldDataHandler.getInstance();
    private static final Set<Biome> snowyBiomes = Set.of(Biome.SNOWY_TAIGA,Biome.JAGGED_PEAKS,Biome.FROZEN_PEAKS,Biome.GROVE,Biome.SNOWY_SLOPES,Biome.FROZEN_RIVER,Biome.SNOWY_PLAINS,Biome.ICE_SPIKES,Biome.SNOWY_BEACH);

    public CustomSkeletons(Plugin plugin){
        super(plugin);
    }

    @EventHandler
    public void onSkeletonSpawn(CreatureSpawnEvent event){
        if (event.getEntityType() != EntityType.SKELETON) return;
        Skeleton skeleton = (Skeleton) event.getEntity();
        Location location =skeleton.getLocation();
        if(location.getY()>60){
            if(instance.getHardmode()){
                if(snowyBiomes.contains(location.getBlock().getBiome())&&skeleton.getWorld().hasStorm()){
                    if(Math.random()<0.9){
                        spawnIceGolem(skeleton);
                    }
                }
            }
        }else{
            if(!instance.getHardmode()){
                if(location.getY()<0){
                    if(Math.random()<0.5){
                        spawnTim(skeleton);
                    }else if(Math.random()<0.2){
                        spawnUndeadMiner(skeleton);
                    }
                } else if(Math.random()<0.2){
                    spawnUndeadMiner(skeleton);
                }
            }else {
                double rand=Math.random();
                if(rand<0.1){
                    spawnUndeadMiner(skeleton);
                } else if (rand<0.6) {
                    spawnSkeletonArcher(skeleton);
                }
            }
        }

    }

    public void spawnSkeletonArcher(Skeleton skeleton){
        skeleton.setCustomName("Skeleton Archer");
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(40);
        skeleton.setHealth(40);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"SkeletonArcher");
        skeleton.setCanPickupItems(false);
        EntityEquipment equipment=skeleton.getEquipment();
        equipment.setHelmet(SkeletonArcherHat.getItem(plugin));
        equipment.setChestplate(null);
        equipment.setLeggings(null);
        equipment.setBoots(null);
        ItemStack bow =new ItemStack(Material.BOW);
        ItemMeta meta=bow.getItemMeta();
        meta.addEnchant(Enchantment.POWER,2,true);
        meta.addEnchant(Enchantment.FLAME,1,true);
        bow.setItemMeta(meta);
        equipment.setItemInMainHand(bow);
        equipment.setHelmetDropChance(0f);
        equipment.setItemInMainHandDropChance(0f);
    }

    public void spawnUndeadMiner(Skeleton skeleton){
        skeleton.setCustomName("Undead Miner");
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(25);
        skeleton.setHealth(25);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"UndeadMiner");
        skeleton.setCanPickupItems(false);
        EntityEquipment equipment=skeleton.getEquipment();
        equipment.setHelmet(UndeadMinerHat.getItem(plugin));
        equipment.setChestplate(UndeadMinerChestplate.getItem(plugin));
        equipment.setItemInMainHand(BonePickaxe.getItem(plugin));
        equipment.setHelmetDropChance(0.1f);
        equipment.setChestplateDropChance(0f);
        equipment.setItemInMainHandDropChance(0.1f);
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
        startAttacks(skeleton,new TimBolt(plugin),"terraria:water_bolt_use");
    }

    public void spawnIceGolem(Skeleton skeleton){
        skeleton.setCustomName("Ice Golem");
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(150);
        skeleton.setHealth(150);
        NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"IceGolem");
        skeleton.setCanPickupItems(false);
        skeleton.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.17);
        skeleton.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(5);
        skeleton.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(20);
        skeleton.getAttribute(Attribute.SCALE).setBaseValue(2.5);
        EntityEquipment equipment=skeleton.getEquipment();
        equipment.setHelmet(IceGolemHat.getItem(plugin));
        equipment.setChestplate(IceGolemChestplate.getItem(plugin));
        equipment.setLeggings(IceGolemLeggings.getItem(plugin));
        equipment.setBoots(IceGolemBoots.getItem(plugin));
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(0f);
        equipment.setChestplateDropChance(0f);
        equipment.setLeggingsDropChance(0f);
        equipment.setBootsDropChance(0f);
        startAttacks(skeleton,new IceGolemLaser(plugin),"terraria:laser");
    }
}
