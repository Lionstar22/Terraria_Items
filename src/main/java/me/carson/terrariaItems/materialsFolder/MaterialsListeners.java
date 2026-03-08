package me.carson.terrariaItems.materialsFolder;

import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import me.carson.terrariaItems.materialsFolder.materials.souls.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class MaterialsListeners implements Listener {

    private final Plugin plugin;
    private final WorldDataHandler worldInstance=WorldDataHandler.getInstance();
    private static final Set<Biome> lightBiomes = Set.of(Biome.CRIMSON_FOREST,Biome.SOUL_SAND_VALLEY,Biome.WARPED_FOREST);
    private static final Set<Biome> darkBiomes = Set.of(Biome.NETHER_WASTES,Biome.BASALT_DELTAS);

    public MaterialsListeners(Plugin plugin){
        this.plugin=plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onWitherDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        NamespacedKey key = new NamespacedKey(plugin, "BossWither");
        if(entity.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
            int drops = 20 + (int) (Math.random() * 11);
            ItemStack custom = SoulOfFright.getItem(plugin);
            custom.setAmount(drops);
            event.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onDragonDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        NamespacedKey key = new NamespacedKey(plugin, "BossDragon");
        if(!entity.getPersistentDataContainer().has(key, PersistentDataType.BYTE)){return;}
        int drops=20 + (int)(Math.random() * 11);
        ItemStack custom = SoulOfMight.getItem(plugin);
        custom.setAmount(drops);
        event.getDrops().add(custom);
    }

    @EventHandler
    public void onWardenDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getType() != EntityType.WARDEN){return;}
        NamespacedKey key = new NamespacedKey(plugin, "BossWarden");
        if(entity.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
            int drops = 20 + (int) (Math.random() * 11);
            ItemStack custom = SoulOfSight.getItem(plugin);
            custom.setAmount(drops);
            event.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onLightDeath(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();
        Biome deathBiome=entity.getLocation().getBlock().getBiome();
        if(!worldInstance.getHardmode()){return;}
        if(lightBiomes.contains(deathBiome)){
            if(Math.random()<0.2){
                ItemStack soul = SoulOfLight.getItem(plugin);
                event.getDrops().add(soul);
            }
        }
    }

    @EventHandler
    public void onDarkDeath(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();
        Biome deathBiome=entity.getLocation().getBlock().getBiome();
        if(!worldInstance.getHardmode()){return;}
        if(darkBiomes.contains(deathBiome)){
            if(Math.random()<0.2){
                ItemStack soul = SoulOfNight.getItem(plugin);
                event.getDrops().add(soul);
            }
        }
    }

}
