package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ObsidianSkull extends Accessory implements Listener {

    public ObsidianSkull(Plugin plugin){
        super(plugin,"Obsidian Skull","#96FF96", Material.OBSIDIAN,"obsidian_skull","ObsidianSkull",new ArrayList<>(List.of(ChatColor.GRAY+"Grants immunity to fire damage",ChatColor.GRAY+"Grants 4% Damage Reduction", ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addDamageReduction(player.getUniqueId(),0.04);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractDamageReduction(player.getUniqueId(),0.04);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianSkull(plugin).createItem();
    }

}
