package me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.melee;

import me.carson.terrariaItems.projectilesFolder.projectiles.VampireKnife;
import me.carson.terrariaItems.weaponsFolder.weapons.meleeFolder.Sword;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class VampireKnives extends Sword {

    public VampireKnives(Plugin plugin) {
        super(plugin,"vampire_knives.name","#FFFF0A", Material.SPIDER_EYE,"vampire_knives","VampireKnives",5,2,8,0.25f,10,"vampire_knives.lore");
    }

    public static ItemStack getItem(Plugin plugin) {
        return new VampireKnives(plugin).createItem();
    }

    @Override
    public void leftActivate(Player player) {
        for(int i=0;i<4;i++){
            new VampireKnife(plugin).createProjectile(player,speed,damage,spread,duration);
        }
    }

    @Override
    public void rightActivate(Player player) {

    }

}
