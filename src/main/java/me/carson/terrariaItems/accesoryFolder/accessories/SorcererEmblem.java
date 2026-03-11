package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SorcererEmblem extends Accessory{

    public SorcererEmblem(Plugin plugin){
        super(plugin,"Sorcerer Emblem","#FF9696", Material.GOLD_NUGGET,"sorcerer_emblem","SorcererEmblem",new ArrayList<>(List.of(ChatColor.GRAY+"20% increased magic damage",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addBonusMagic(player.getUniqueId(),0.2);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractBonusMagic(player.getUniqueId(),0.2);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SorcererEmblem(plugin).createItem();
    }


}
