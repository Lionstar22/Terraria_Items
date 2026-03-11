package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LuckyHorseshoe extends Accessory{

    public LuckyHorseshoe(Plugin plugin){
        super(plugin,"Lucky Horseshoe","#9696FF",Material.GOLD_BLOCK,"lucky_horseshoe","LuckyHorseshoe",
                new ArrayList<>(List.of(
                        ChatColor.GRAY+"Negates fall damage",
                        ChatColor.GRAY+"'Said to bring good fortune and keep evil spirits at bay'",
                        ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new LuckyHorseshoe(plugin).createItem();
    }

}
