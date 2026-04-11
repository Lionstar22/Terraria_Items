package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class AnkhCharm extends Accessory  {

    public AnkhCharm(Plugin plugin){
        super(plugin,"ankh_charm.name","#D2A0FF",Material.NETHER_BRICK,"ankh_charm","AnkhCharm","ankh_charm.lore");
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new AnkhCharm(plugin).createItem();
    }

}
