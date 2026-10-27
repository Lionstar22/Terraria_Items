package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.EmeraldBolt;
import me.carson.terrariaItems.projectilesFolder.projectiles.SapphireBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class EmeraldStaff extends Magic {

    public EmeraldStaff(Plugin plugin) {
        super(plugin,"emerald_staff.name","#9696FF", Material.AMETHYST_SHARD,"emerald_staff","EmeraldStaff",18,1,6,0,120,8,"emerald_staff.lore");
    }

    @Override
    public void rightActivate(Player player) {
        if(manaManagerInstance.useMana(player,cost)){
            new EmeraldBolt(plugin).createEmeraldBolt(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_use", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new EmeraldStaff(plugin).createItem();
    }

}
