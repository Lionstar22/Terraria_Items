package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new SorcererEmblem(plugin).createItem();
    }


}
