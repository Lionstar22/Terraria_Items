package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.listenersHandler.ManaManager;
import me.carson.terrariaItems.projectilesFolder.projectiles.LaserRifleProjectile;
import me.carson.terrariaItems.projectilesFolder.projectiles.RubyBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LaserRifle extends Magic {

    public LaserRifle(Plugin plugin) {
        super(plugin,"Laser Rifle","#FF9696", Material.PRISMARINE_SHARD,"laser_rifle","LaserRifle",5,2.5f,8,0,75,8, new ArrayList<>(List.of(ChatColor.GRAY+"Fires fast-moving purple lasers",ChatColor.GRAY+"Costs 8 Mana",ChatColor.GRAY+"8 Damage")));
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(!isThisItem(player.getInventory().getItemInMainHand())){return;}
        UUID id=player.getUniqueId();
        ManaManager instance = ManaManager.getInstance();
        if(instance.getMana(id)<cost){return;}
        new LaserRifleProjectile(plugin).createProjectile(player,speed,damage,spread,duration);
        player.getWorld().playSound(player.getLocation(), "terraria:laser_2", 1.0F, 1.0F);
        instance.removeMana(id, cost);
        instance.updateManaBar(player);
        instance.startManaRegenDelay(player,instance);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new LaserRifle(plugin).createItem();
    }

}
