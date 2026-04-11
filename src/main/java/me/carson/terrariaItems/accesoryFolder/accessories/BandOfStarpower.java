package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class BandOfStarpower extends Accessory  {

    public BandOfStarpower(Plugin plugin) {
        super(plugin, "band_of_starpower.name", "#9696FF", Material.NETHER_BRICK, "band_of_starpower", "BandOfStarpower", "band_of_starpower.lore");
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addExtraMana(player.getUniqueId(),20);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),20);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BandOfStarpower(plugin).createItem();
    }

}
