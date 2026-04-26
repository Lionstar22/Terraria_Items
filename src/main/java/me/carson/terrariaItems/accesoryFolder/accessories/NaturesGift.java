package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class NaturesGift extends Accessory{

    public NaturesGift(Plugin plugin){
        super(plugin,"natures_gift.name","#FFC896", Material.NETHER_BRICK,"natures_gift","NaturesGift","natures_gift.lore");
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addManaReduction(player.getUniqueId(),0.06);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractManaReduction(player.getUniqueId(),0.06);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }
    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new NaturesGift(plugin).createItem();
    }

}
