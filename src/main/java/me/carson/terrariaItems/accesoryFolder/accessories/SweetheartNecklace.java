package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class SweetheartNecklace extends Accessory  {

    public SweetheartNecklace(Plugin plugin){
        super(plugin,"Sweetheart Necklace","#FFC896",Material.NETHER_BRICK,"sweetheart_necklace","SweetheartNecklace",new ArrayList<>(List.of(ChatColor.GRAY+"Increases movement speed when damaged",ChatColor.GRAY+"Douses the user in honey when damaged",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new SweetheartNecklace(plugin).createItem();
    }

}
