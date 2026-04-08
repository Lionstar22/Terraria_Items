package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class MagicCuffs extends Accessory  {

    public MagicCuffs(Plugin plugin){
        super(plugin,"Magic Cuffs","#96FF96",Material.NETHER_BRICK,"magic_cuffs","MagicCuffs",new ArrayList<>(List.of(ChatColor.GRAY+"Increases maximum mana by 20",ChatColor.GRAY+"Restores mana when damaged",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addExtraMana(player.getUniqueId(),20);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),20);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MagicCuffs(plugin).createItem();
    }

}
