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
        super(plugin,"Anklet Of The Wind","#FFC896", Material.COPPER_INGOT,"anklet_of_the_wind","AnkletOfTheWind",new ArrayList<>(List.of(ChatColor.GRAY+"10% increased movement speed",ChatColor.GRAY+"Must be in accessory inventory")));
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
