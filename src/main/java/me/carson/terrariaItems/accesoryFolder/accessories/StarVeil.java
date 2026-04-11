package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class StarVeil extends Accessory  {

    public StarVeil(Plugin plugin){
        super(plugin,"star_veil.name","#D2A0FF",Material.NETHER_BRICK,"star_veil","StarVeil","star_veil.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.setMaximumNoDamageTicks(60); //default is 20
    }

    @Override
    public void deactivateEffect(Player player) {
        player.setMaximumNoDamageTicks(20); //default is 20
    }

    public static ItemStack getItem(Plugin plugin) {
        return new StarVeil(plugin).createItem();
    }

}
