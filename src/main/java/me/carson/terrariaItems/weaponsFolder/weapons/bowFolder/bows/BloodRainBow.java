package me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows;

import me.carson.terrariaItems.projectilesFolder.projectiles.BloodRainArrow;
import me.carson.terrariaItems.projectilesFolder.projectiles.PulseBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.Bow;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;

import java.util.*;

public class BloodRainBow extends Bow implements Listener {

    public BloodRainBow(Plugin plugin) {
        super(plugin,"blood_rain_bow.name","#FFC896", Material.BOW,"blood_rain_bow","BloodRainBow",5,1.5f,6,5f, "blood_rain_bow.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BloodRainBow(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if (player.getInventory().contains(Material.ARROW)) {
            player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));

            RayTraceResult result= player.getWorld().rayTrace(player.getEyeLocation(),player.getEyeLocation().getDirection(),100, FluidCollisionMode.NEVER,true,0.1, e -> (e!=player));
            if (result == null) {return;}
            Location hit=result.getHitPosition().toLocation(player.getWorld());

            new BloodRainArrow(plugin).createFallingProjectile(player,speed,damage,spread,100f,25,hit.add(Math.random()-0.5,0,Math.random()-0.5));
            new BloodRainArrow(plugin).createFallingProjectile(player,speed,damage,spread,100f,25,hit.add(Math.random()-0.5,0,Math.random()-0.5));
            player.getWorld().playSound(player.getLocation(), "terraria:repeater_use", 0.75F, 1.0F);
        }
    }

}
