package me.carson.terrariaItems.toolFolder.tools;

import me.carson.terrariaItems.bossFolder.bosses.MechanicalWarden;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TestSummon extends Tool {

    public TestSummon(Plugin plugin){
        super(plugin,"Test Summon","#9696FF", Material.LIGHT_GRAY_DYE,"magic_mirror","TestSummon",10,new ArrayList<>(List.of(
                ChatColor.GRAY+"Summons a test boss")));
    }

    @Override
    public void rightActivate(Player player) {
        new MechanicalWarden(plugin).summonBoss(player);
    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new TestSummon(plugin).createItem();
    }

}
