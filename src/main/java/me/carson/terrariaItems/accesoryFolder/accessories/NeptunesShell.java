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

public class NeptunesShell extends Accessory {
    public NeptunesShell(Plugin plugin){
        super(plugin,"neptunes_shell.name","#FF96FF", Material.DEAD_FIRE_CORAL,"neptunes_shell","NeptunesShell","neptunes_shell.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 0, false, false,false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1, false, false,false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
        player.removePotionEffect(PotionEffectType.WATER_BREATHING);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new NeptunesShell(plugin).createItem();
    }
}
