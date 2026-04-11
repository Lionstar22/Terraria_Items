package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class AnkletOfTheWind extends Accessory {

    public AnkletOfTheWind(Plugin plugin){
        super(plugin,"anklet_of_the_wind.name","#FFC896", Material.COPPER_INGOT,"anklet_of_the_wind","AnkletOfTheWind","anklet_of_the_wind.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.setWalkSpeed(player.getWalkSpeed()+0.02f);
    }

    @Override
    public void deactivateEffect(Player player) {
        player.setWalkSpeed(Math.max(player.getWalkSpeed() - 0.02f, 0.2f));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new AnkletOfTheWind(plugin).createItem();
    }

}
