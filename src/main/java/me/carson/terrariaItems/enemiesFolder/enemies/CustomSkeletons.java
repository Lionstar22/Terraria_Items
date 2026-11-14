package me.carson.terrariaItems.enemiesFolder.enemies;

import me.carson.terrariaItems.armorFolder.armors.iceGolemArmor.IceGolemBoots;
import me.carson.terrariaItems.armorFolder.armors.iceGolemArmor.IceGolemChestplate;
import me.carson.terrariaItems.armorFolder.armors.iceGolemArmor.IceGolemLeggings;
import me.carson.terrariaItems.armorFolder.armors.undeadMinerArmor.UndeadMinerChestplate;
import me.carson.terrariaItems.enemiesFolder.CustomEnemy;
import me.carson.terrariaItems.enemiesFolder.enemies.Casters.RuneWizard;
import me.carson.terrariaItems.enemiesFolder.enemies.Casters.Tim;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.DustDevil;
import me.carson.terrariaItems.enemyProjectilesFolder.mobProjectiles.IceGolemLaser;
import me.carson.terrariaItems.miscFolder.BasicItems.BonePickaxe;
import me.carson.terrariaItems.miscFolder.hats.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
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

    private static final Set<Biome> snowyBiomes = Set.of(Biome.SNOWY_TAIGA,Biome.JAGGED_PEAKS,Biome.FROZEN_PEAKS,Biome.GROVE,Biome.SNOWY_SLOPES,Biome.FROZEN_RIVER,Biome.SNOWY_PLAINS,Biome.ICE_SPIKES,Biome.SNOWY_BEACH);
    private static final Set<Biome> desertBiomes = Set.of(Biome.DESERT,Biome.BADLANDS,Biome.ERODED_BADLANDS);

    public CustomSkeletons(Plugin plugin){
        super(plugin);
    }

    Tim tim=new Tim(plugin);
    RuneWizard runeWizard=new RuneWizard(plugin);
    NamespacedKey key = new NamespacedKey(plugin, "custom_enemy");

    @EventHandler
    public void onSkeletonSpawn(CreatureSpawnEvent event){
        if (event.getEntityType() != EntityType.SKELETON) return;
        Skeleton skeleton = (Skeleton) event.getEntity();
        Location location =skeleton.getLocation();

        double rand=Math.random();
        int yLevel=getLevel(location.getY());

        if(location.getWorld().getEnvironment()== World.Environment.NETHER){
            if(instance.getHardmode()){
                if(rand<0.5) {  //50%
                    spawnSkeletonArcher(skeleton);
                } else if(rand<0.53) {    //3%
                    runeWizard.spawnRuneWizard(skeleton);
                }
            }
            return;
        }

        if(instance.getHardmode()&& instance.getHardmodeEnabled()){//Hardmode
            switch (yLevel){
                case 1 ->{
                    if(skeleton.getWorld().hasStorm()){
                        if(snowyBiomes.contains(location.getBlock().getBiome())){
                            if(Math.random()<0.075){
                                spawnIceGolem(skeleton);
                            }
                        } else if (desertBiomes.contains(location.getBlock().getBiome())) {
                            if(Math.random()<0.075){
                                spawnSandElemental(skeleton);
                            }
                        }
                    }
                }
                case 2->{
                    if(rand<0.1){   //10%
                        spawnUndeadMiner(skeleton);
                    } else if (rand<0.6) {  //50%
                        spawnSkeletonArcher(skeleton);
                    }
                }
                case 3->{
                    if(rand<0.03){  //3%
                        tim.spawnTim(skeleton);
                    }else if(rand<0.06){    //3%
                        runeWizard.spawnRuneWizard(skeleton);
                    }else if(rand<0.16){    //10%
                        spawnUndeadMiner(skeleton);
                    } else if (rand<0.66) {    //50%
                        spawnSkeletonArcher(skeleton);
                    }
                }
            }
        }else if(instance.getPreHardmodeEnabled()){//Pre-Hardmode
            switch (yLevel){
                case 1-> {
                }
                case 2->{
                    if(rand<0.1){//10%
                        spawnUndeadMiner(skeleton);
                    }
                }
                case 3->{
                    if(rand<0.03){//3%
                        tim.spawnTim(skeleton);
                    }else if(rand<0.13){//10%
                        spawnUndeadMiner(skeleton);
                    }
                }
            }
        }

    }

    public void spawnSkeletonArcher(Skeleton skeleton){
        skeleton.setCustomName(lang.get("enemies","skeleton_archer.name"));
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(40);
        skeleton.setHealth(40);
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"SkeletonArcher");
        skeleton.setCanPickupItems(false);
        EntityEquipment equipment=skeleton.getEquipment();
        equipment.setHelmet(SkeletonArcherHat.getItem(plugin));
        equipment.setChestplate(null);
        equipment.setLeggings(null);
        equipment.setBoots(null);
        ItemStack bow =new ItemStack(Material.BOW);
        ItemMeta meta=bow.getItemMeta();
        meta.addEnchant(Enchantment.POWER,6,true);
        meta.addEnchant(Enchantment.FLAME,1,true);
        bow.setItemMeta(meta);
        equipment.setItemInMainHand(bow);
        equipment.setHelmetDropChance(0f);
        equipment.setItemInMainHandDropChance(0f);
    }

    public void spawnUndeadMiner(Skeleton skeleton){
        skeleton.setCustomName(lang.get("enemies","undead_miner.name"));
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(25);
        skeleton.setHealth(25);
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"UndeadMiner");
        skeleton.setCanPickupItems(false);
        EntityEquipment equipment=skeleton.getEquipment();
        equipment.setHelmet(UndeadMinerHat.getItem(plugin));
        equipment.setChestplate(UndeadMinerChestplate.getItem(plugin));
        equipment.setItemInMainHand(BonePickaxe.getItem(plugin));
        equipment.setHelmetDropChance(0.05f);
        equipment.setChestplateDropChance(0f);
        equipment.setItemInMainHandDropChance(0.05f);
    }

    public void spawnIceGolem(Skeleton skeleton){
        skeleton.setCustomName(lang.get("enemies","ice_golem.name"));
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(250);
        skeleton.setHealth(250);
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"IceGolem");
        skeleton.setCanPickupItems(false);
        skeleton.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.17);
        skeleton.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(5);
        skeleton.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(25);
        skeleton.getAttribute(Attribute.SCALE).setBaseValue(2.5);
        skeleton.getAttribute(Attribute.FOLLOW_RANGE).setBaseValue(50);
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


    public void spawnSandElemental(Skeleton skeleton){
        skeleton.setCustomName(lang.get("enemies","sand_elemental.name"));
        skeleton.setCustomNameVisible(false);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(300);
        skeleton.setHealth(300);
        skeleton.getPersistentDataContainer().set(key, PersistentDataType.STRING,"SandElemental");
        skeleton.setCanPickupItems(false);
        skeleton.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.15);
        skeleton.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(5);
        skeleton.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(25);
        skeleton.getAttribute(Attribute.SCALE).setBaseValue(2);
        skeleton.getAttribute(Attribute.FOLLOW_RANGE).setBaseValue(50);
        skeleton.setInvisible(true);
        EntityEquipment equipment=skeleton.getEquipment();
        equipment.setHelmet(SandElementalHat.getItem(plugin));
        equipment.setChestplate(null);
        equipment.setLeggings(null);
        equipment.setBoots(null);
        equipment.setItemInMainHand(null);
        equipment.setHelmetDropChance(0f);
        startAttacks(skeleton,new DustDevil(plugin),"fwoosh");
    }
}
