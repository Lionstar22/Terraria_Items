package me.carson.terrariaItems.materialsFolder.materials.souls;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SoulOfLight extends Material implements Listener {

    private static final Set<Biome> lightBiomes = Set.of(Biome.CRIMSON_FOREST,Biome.SOUL_SAND_VALLEY,Biome.WARPED_FOREST);

    public SoulOfLight(Plugin plugin) {
        super(plugin,"Soul of Light","#FFC896", org.bukkit.Material.PINK_DYE,"soul_of_light","SoulOfLight", new ArrayList<>(List.of(ChatColor.GRAY+"The essence of light creatures")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SoulOfLight(plugin).createItem();
    }

    @EventHandler
    public void onLightDeath(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();
        Biome deathBiome=entity.getLocation().getBlock().getBiome();
        if(lightBiomes.contains(deathBiome)){
            if(Math.random()<0.2){
                ItemStack soul = SoulOfLight.getItem(plugin);
                event.getDrops().add(soul);
            }
        }
    }
}
