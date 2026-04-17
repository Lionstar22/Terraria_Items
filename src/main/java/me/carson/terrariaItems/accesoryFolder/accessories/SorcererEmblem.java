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

public class SorcererEmblem extends Accessory{

    public SorcererEmblem(Plugin plugin){
        super(plugin,"sorcerer_emblem.name","#FF9696", Material.GOLD_NUGGET,"sorcerer_emblem","SorcererEmblem","sorcerer_emblem.lore");
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addBonusMagic(player.getUniqueId(),0.2);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractBonusMagic(player.getUniqueId(),0.2);
    }

    @Override
    public void onPlayerHit(Player player, EntityDamageEvent event) {

    }

    @Override
    public void onPlayerEffect(Player player, EntityPotionEffectEvent event) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new SorcererEmblem(plugin).createItem();
    }


}
