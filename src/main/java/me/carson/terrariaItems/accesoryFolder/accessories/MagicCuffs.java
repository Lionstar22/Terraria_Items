package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class MagicCuffs extends Accessory  {

    public MagicCuffs(Plugin plugin){
        super(plugin,"magic_cuffs.name","#96FF96",Material.NETHER_BRICK,"magic_cuffs","MagicCuffs","magic_cuffs.lore");
    }

    @Override
    public void activateEffect(Player player){
        playerInstance.addExtraMana(player.getUniqueId(),20);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractExtraMana(player.getUniqueId(),20);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {
        manaManagerInstance.addMana(player.getUniqueId(), event.getDamage()*4);
        manaManagerInstance.updateManaBar(player);
    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new MagicCuffs(plugin).createItem();
    }

}
