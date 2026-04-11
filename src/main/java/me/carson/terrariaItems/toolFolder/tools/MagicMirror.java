package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MagicMirror extends Tool {

    public MagicMirror(Plugin plugin){
        super(plugin,"magic_mirror.name","#9696FF", Material.LIGHT_GRAY_DYE,"magic_mirror","MagicMirror",20,"magic_mirror.lore");
    }

    @Override
    public void rightActivate(Player player) {
        Location loc=player.getRespawnLocation();
        if(loc!=null){
            player.setFallDistance(0);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_mirror_use", 1.0F, 1.0F);
            player.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_mirror_use", 1.0F, 1.0F);
        }

    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MagicMirror(plugin).createItem();
    }
}
