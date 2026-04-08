package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class PanicNecklace extends Accessory  {

    public PanicNecklace(Plugin plugin){
        super(plugin,"Panic Necklace","#9696FF",Material.NETHER_BRICK,"panic_necklace","PanicNecklace",new ArrayList<>(List.of(ChatColor.GRAY+"Increases movement speed after taking damage",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
    }

    @Override
    public void deactivateEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new PanicNecklace(plugin).createItem();
    }

}
