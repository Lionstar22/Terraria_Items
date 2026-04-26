package me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons;

import me.carson.terrariaItems.projectilesFolder.projectiles.ThunderZapperBolt;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.Magic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class ThunderZapper extends Magic {

    public ThunderZapper(Plugin plugin) {
        super(plugin,"thunder_zapper.name","#9696FF", Material.RABBIT_HIDE,"thunder_zapper","ThunderZapper",5,2f,9,0,50,6,"thunder_zapper.lore");
    }

    @Override
    public void leftActivate(Player player) {

    }

    @Override
    public void rightActivate(Player player) {
        if(!isThisItem(player.getInventory().getItemInMainHand())){return;}

        if(manaManagerInstance.useMana(player,cost)){
            new ThunderZapperBolt(plugin).createProjectile(player,speed,damage,spread,duration);
            player.getWorld().playSound(player.getLocation(), "terraria:magic_use", 1.0F, 1.0F);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ThunderZapper(plugin).createItem();
    }

}
