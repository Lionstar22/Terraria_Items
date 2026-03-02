package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TestingStick extends Tool {

    public TestingStick(Plugin plugin){
        super(plugin,"Testing Stick","#FF96FF", Material.STICK,"rod_of_discord","TestingStick",0,new ArrayList<>(List.of(ChatColor.GRAY+"Testing Stick")));
    }

    @Override
    public void rightActivate(Player player) {
        player.sendMessage(player.getLocation().getBlock().getBiome()+"");
    }

    @Override
    public void cooldownEffect(Player player) {

    }

    public static ItemStack getItem(Plugin plugin) {
        return new TestingStick(plugin).createItem();
    }

}
