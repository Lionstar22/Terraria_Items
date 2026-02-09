package me.carson.terrariaItems.listenersHandler;

import me.carson.terrariaItems.accesoryFolder.accessories.NeptunesShell;
import me.carson.terrariaItems.toolFolder.tools.crates.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class FishingManager implements Listener {

    private final Plugin plugin;

    private static final Set<Biome> jungleBiomes = Set.of(Biome.JUNGLE,Biome.BAMBOO_JUNGLE,Biome.SPARSE_JUNGLE);
    private static final Set<Biome> desertBiomes = Set.of(Biome.DESERT,Biome.BADLANDS,Biome.WOODED_BADLANDS,Biome.ERODED_BADLANDS);
    private static final Set<Biome> frozenBiomes = Set.of(Biome.SNOWY_TAIGA,Biome.JAGGED_PEAKS,Biome.FROZEN_PEAKS,Biome.GROVE,Biome.SNOWY_SLOPES,Biome.FROZEN_RIVER,Biome.SNOWY_PLAINS,Biome.ICE_SPIKES);
    private static final Set<Biome> oceanBiomes = Set.of(Biome.BEACH,Biome.OCEAN,Biome.DEEP_OCEAN,Biome.WARM_OCEAN,Biome.LUKEWARM_OCEAN,Biome.DEEP_LUKEWARM_OCEAN,Biome.COLD_OCEAN,Biome.DEEP_COLD_OCEAN,Biome.FROZEN_OCEAN,Biome.DEEP_FROZEN_OCEAN,Biome.MUSHROOM_FIELDS,Biome.SNOWY_BEACH,Biome.STONY_SHORE);

    public FishingManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFish(PlayerFishEvent event){
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;
        Player player = event.getPlayer();
        Item caughtItem = (Item) event.getCaught();
        if(Math.random()<0.05){
            caughtItem.setItemStack(selectCrate(player));
        }
    }

    public ItemStack selectCrate(Player player){
        double crateChance =Math.random();
        if(selectBiomeCrate(player)!=null){//Biome Crate Loot table
            if(crateChance<0.1){ //10% Gold
                return GoldenCrate.getItem(plugin);
            } else if (crateChance<0.3) { //20% Biome
                return selectBiomeCrate(player);
            }else if(crateChance<0.5){ //20% Iron
                return IronCrate.getItem(plugin);
            }else { //50% Wood
                return WoodenCrate.getItem(plugin);
            }
        }else{
            if(crateChance<0.1){ //10% Gold
                return GoldenCrate.getItem(plugin);
            } else if (crateChance<0.4) { //30% Iron
                return IronCrate.getItem(plugin);
            }else{ //60% Wood
                return WoodenCrate.getItem(plugin);
            }
        }
    }

    public ItemStack selectBiomeCrate(Player player){
        Biome biome= player.getLocation().getBlock().getBiome();
        double height=player.getLocation().getY();

        if(height>=175){
            return SkyCrate.getItem(plugin);
        }else if(jungleBiomes.contains(biome)){
            return JungleCrate.getItem(plugin);
        } else if (desertBiomes.contains(biome)) {
            return OasisCrate.getItem(plugin);
        } else if (frozenBiomes.contains(biome)) {
            return FrozenCrate.getItem(plugin);
        } else if (oceanBiomes.contains(biome)) {
            return OceanCrate.getItem(plugin);
        }else{
            return null;
        }
    }
}
