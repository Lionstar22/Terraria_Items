package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Shackle extends Accessory{

    public Shackle(Plugin plugin){
        super(plugin,"Shackle","#9696FF", Material.IRON_INGOT,"shackle","Shackle",new ArrayList<>(List.of(ChatColor.GRAY+"10% decrease in damage taken",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {
        playerInstance.addDamageReduction(player.getUniqueId(),0.1);
    }

    @Override
    public void deactivateEffect(Player player) {
        playerInstance.subtractDamageReduction(player.getUniqueId(),0.1);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Shackle(plugin).createItem();
    }


}
