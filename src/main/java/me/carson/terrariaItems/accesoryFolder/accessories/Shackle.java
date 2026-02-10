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

public class Shackle extends Accessory implements Listener {

    public Shackle(Plugin plugin){
        super(plugin,"Shackle","#9696FF", Material.IRON_INGOT,"shackle","Shackle",new ArrayList<>(List.of(ChatColor.GRAY+"10% decrease in damage taken",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player) {

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new Shackle(plugin).createItem();
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getType() != EntityType.ZOMBIE){return;}

        if(Math.random()<0.02){
            ItemStack custom = Shackle.getItem(plugin);
            e.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onShackleDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (hasItem(player)){
            double newDamage = event.getDamage() * 0.9 ;
            event.setDamage(newDamage);
        }
    }
}
