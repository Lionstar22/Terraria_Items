package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Blindfold extends Accessory{

    public Blindfold(Plugin plugin){
        super(plugin,"Blindfold","#FF9696", Material.ECHO_SHARD,"blindfold","Blindfold",new ArrayList<>(List.of(ChatColor.GRAY+"Immunity to Darkness/Blindness",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new Blindfold(plugin).createItem();
    }
}
