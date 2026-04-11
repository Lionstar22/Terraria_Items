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

public class Flipper extends Accessory {

    public Flipper(Plugin plugin){
        super(plugin,"flipper.name","#9696FF", Material.NAUTILUS_SHELL,"flipper","Flipper","flipper.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 0, false, false,false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Flipper(plugin).createItem();
    }

}
