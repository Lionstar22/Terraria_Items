package me.carson.terrariaItems.materialsFolder.materials.souls;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SoulOfSight extends Material{

    public SoulOfSight(Plugin plugin) {
        super(plugin,"Soul of Sight","#FF96FF", org.bukkit.Material.IRON_INGOT,"soul_of_sight","SoulOfSight", new ArrayList<>(List.of(ChatColor.GRAY+"The essence of the Warden")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SoulOfSight(plugin).createItem();
    }

}
