package me.carson.terrariaItems.enemiesFolder.enemies;

import me.carson.terrariaItems.armourFolder.armors.undeadMinerArmor.UndeadMinerChestplate;
import me.carson.terrariaItems.enemiesFolder.CustomEnemy;
import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import me.carson.terrariaItems.miscFolder.BasicItems.BonePickaxe;
import me.carson.terrariaItems.miscFolder.hats.SkeletonArcherHat;
import me.carson.terrariaItems.miscFolder.hats.UndeadMinerHat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
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

public class CustomSkeletons extends CustomEnemy implements Listener {

    private final WorldDataHandler instance=WorldDataHandler.getInstance();

    public CustomSkeletons(Plugin plugin){
        super(plugin);
    }

    @EventHandler
    public void onSkeletonSpawn(CreatureSpawnEvent event){
        if (event.getEntityType() != EntityType.SKELETON) return;
        Skeleton skeleton = (Skeleton) event.getEntity();
        Location location =skeleton.getLocation();
        if(location.getY()>60){return;}
        if(!instance.getHardmode()){
            if(Math.random()<0.2){
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
        equipment.setHelmetDropChance(0.5f);
        equipment.setChestplateDropChance(0f);
        equipment.setItemInMainHandDropChance(0.5f);
    }
}
