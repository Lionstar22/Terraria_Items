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
        super(plugin,"Flipper","#9696FF", Material.NAUTILUS_SHELL,"flipper","Flipper",new ArrayList<>(List.of(ChatColor.GRAY+"Grants the ability to swim faster",ChatColor.GRAY+"Shift Right Click to Activate")));
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
