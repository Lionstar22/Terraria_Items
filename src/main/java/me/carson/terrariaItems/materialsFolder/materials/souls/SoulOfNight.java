package me.carson.terrariaItems.materialsFolder.materials.souls;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SoulOfNight extends Material{

    public SoulOfNight(Plugin plugin) {
        super(plugin,"soul_of_night.name","#FFC896", org.bukkit.Material.PURPLE_DYE,"soul_of_night","SoulOfNight", "soul_of_night.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SoulOfNight(plugin).createItem();
    }
}
