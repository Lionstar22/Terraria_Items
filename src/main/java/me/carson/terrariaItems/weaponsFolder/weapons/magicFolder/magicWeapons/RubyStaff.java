package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.listenersHandler.ManaManager;
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

public class RubyStaff extends Magic {

    public RubyStaff(Plugin plugin) {
        super(plugin,"Ruby Staff","#9696FF", Material.DIAMOND,"ruby_staff","RubyStaff",12,1.5f,6,0,50,7, new ArrayList<>(List.of(ChatColor.GRAY+"Fires a ruby bolt",ChatColor.GRAY+"Costs 7 Mana",ChatColor.GRAY+"6 Damage")));
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
        new RubyBolt(plugin).createProjectile(player,speed,damage,spread,duration);
        player.getWorld().playSound(player.getLocation(), "terraria:magic_use", 1.0F, 1.0F);
        instance.removeMana(id, cost);
        instance.updateManaBar(player);
        instance.startManaRegenDelay(player,instance);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new RubyStaff(plugin).createItem();
    }

}
