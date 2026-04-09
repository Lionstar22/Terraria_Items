package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ObsidianHorseshoe extends Accessory{

    public ObsidianHorseshoe(Plugin plugin){
        super(plugin,"Obsidian Horseshoe","#FF9696",Material.NETHER_BRICK,"obsidian_horseshoe","ObsidianHorseshoe",
                new ArrayList<>(List.of(
                        ChatColor.GRAY+"Negates fall damage",
                        ChatColor.GRAY+"Grants immunity to fire blocks",
                        ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianHorseshoe(plugin).createItem();
    }

}
