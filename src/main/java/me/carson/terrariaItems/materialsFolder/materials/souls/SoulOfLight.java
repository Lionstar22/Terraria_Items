package me.carson.terrariaItems.materialsFolder.materials.souls;

import me.carson.terrariaItems.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SoulOfLight extends Material{

    public SoulOfLight(Plugin plugin) {
        super(plugin,"soul_of_light.name","#FFC896", org.bukkit.Material.PINK_DYE,"soul_of_light","SoulOfLight","soul_of_light.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new SoulOfLight(plugin).createItem();
    }


}
