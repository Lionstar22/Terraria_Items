package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class CrossNecklace extends Accessory  {

    public CrossNecklace(Plugin plugin){
        super(plugin,"cross_necklace.name","#FF9696",Material.NETHER_BRICK,"cross_necklace","CrossNecklace","cross_necklace.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.setMaximumNoDamageTicks(60); //default is 20
    }

    @Override
    public void deactivateEffect(Player player) {
        player.setMaximumNoDamageTicks(20);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CrossNecklace(plugin).createItem();
    }

}
