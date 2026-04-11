package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class BeeCloak extends Accessory  {

    public BeeCloak(Plugin plugin){
        super(plugin,"bee_cloak.name","#FF9696",Material.NETHER_BRICK,"bee_cloak","BeeCloak","bee_cloak.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new BeeCloak(plugin).createItem();
    }

}
