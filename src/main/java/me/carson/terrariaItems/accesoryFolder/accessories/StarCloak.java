package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class StarCloak extends Accessory  {

    public StarCloak(Plugin plugin){
        super(plugin,"Star Cloak","#FF9696",Material.NETHER_BRICK,"star_cloak","StarCloak",new ArrayList<>(List.of(ChatColor.GRAY+"Causes stars to fall after taking damage",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new StarCloak(plugin).createItem();
    }

}
