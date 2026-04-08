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


public class ManaRegenerationBand extends Accessory  {

    public ManaRegenerationBand(Plugin plugin){
        super(plugin,"Mana Regeneration Band","#9696FF",Material.NETHER_BRICK,"mana_regeneration_band","ManaRegenerationBand",new ArrayList<>(List.of(ChatColor.GRAY+"Increases maximum mana by 20",ChatColor.GRAY+"Increases Regeneration",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addExtraMana(player.getUniqueId(),20);
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 0, false, false,false));
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),20);
        player.removePotionEffect(PotionEffectType.REGENERATION);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ManaRegenerationBand(plugin).createItem();
    }

}
