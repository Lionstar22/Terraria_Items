package me.carson.terrariaItems.materialsFolder.materials;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SoulOfSight extends Material implements Listener {

    public SoulOfSight(Plugin plugin) {
        super(plugin,"Soul of Sight","#FF96FF", org.bukkit.Material.IRON_INGOT,"soul_of_sight","SoulOfSight", new ArrayList<>(List.of(ChatColor.GRAY+"The essence of the Warden")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SoulOfSight(plugin).createItem();
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        Bukkit.getLogger().info("DEATH EVENT");
        LivingEntity entity = event.getEntity();
        if (entity.getType() != EntityType.WARDEN){return;}
        int drops=20 + (int)(Math.random() * 11);
        ItemStack custom = SoulOfSight.getItem(plugin);
        custom.setAmount(drops);
        event.getDrops().add(custom);
    }
}
