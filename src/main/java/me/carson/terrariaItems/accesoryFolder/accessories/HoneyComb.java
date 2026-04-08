package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class HoneyComb extends Accessory  {

    public HoneyComb(Plugin plugin){
        super(plugin,"Honey Comb","#96FF96",Material.NETHER_BRICK,"honey_comb","HoneyComb",new ArrayList<>(List.of(ChatColor.GRAY+"Douses the user in honey when damaged",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new HoneyComb(plugin).createItem();
    }

}
