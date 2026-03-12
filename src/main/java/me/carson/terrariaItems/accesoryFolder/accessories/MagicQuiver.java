package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MagicQuiver extends Accessory {

    public MagicQuiver(Plugin plugin){
        super(plugin,"Magic Quiver","#FF9696", Material.GOLD_NUGGET,"magic_quiver","MagicQuiver",new ArrayList<>(List.of(ChatColor.GRAY+"10% increased ranged damage",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addBonusRanged(player.getUniqueId(),0.1);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractBonusRanged(player.getUniqueId(),0.1);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MagicQuiver(plugin).createItem();
    }

}
