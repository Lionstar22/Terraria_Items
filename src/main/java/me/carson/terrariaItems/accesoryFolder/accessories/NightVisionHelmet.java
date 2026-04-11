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

public class NightVisionHelmet extends Accessory {

    public NightVisionHelmet(Plugin plugin){
        super(plugin,"night_vision_helmet.name","#96FF96", Material.GHAST_TEAR,"night_vision_helmet","NightVisionHelmet","night_vision_helmet.lore");
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 0, false, false,false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new NightVisionHelmet(plugin).createItem();
    }

}
