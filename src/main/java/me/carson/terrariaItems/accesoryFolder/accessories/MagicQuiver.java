package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MagicQuiver extends Accessory {

    public MagicQuiver(Plugin plugin){
        super(plugin,"magic_quiver.name","#FF9696", Material.GOLD_NUGGET,"magic_quiver","MagicQuiver","magic_quiver.lore");
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
