package me.carson.terrariaItems.toolFolder.tools.summons;

import me.carson.terrariaItems.bossFolder.bosses.MechanicalWarden;
import me.carson.terrariaItems.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MechanicalShrieker extends Tool {

    private NamespacedKey key = new NamespacedKey(plugin, "BossWarden");

    public MechanicalShrieker(Plugin plugin){
        super(plugin,"Mechanical Shrieker","#FFC896", Material.LIGHT_GRAY_DYE,"mechanical_shrieker","MechanicalShrieker",10,new ArrayList<>(List.of(
                ChatColor.GRAY+"Summons the Mechanical Warden")));
    }

    @Override
    public void rightActivate(Player player) {
        World world= player.getWorld();
        if(world.getEnvironment() != World.Environment.NORMAL){
            player.sendMessage(ChatColor.RED + "This Boss must be summoned in the Overworld");
            return;
        }
        for (Entity entity : world.getEntities()) {
            if(entity.getPersistentDataContainer().has(key)){
                player.sendMessage(ChatColor.RED + "There is already one of these bosses active");
                return;
            }
        }
        new MechanicalWarden(plugin).summonBoss(player);
    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MechanicalShrieker(plugin).createItem();
    }

}
