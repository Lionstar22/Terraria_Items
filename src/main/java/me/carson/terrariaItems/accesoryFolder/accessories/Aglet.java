package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;


public class Aglet extends Accessory  {

    public Aglet(Plugin plugin){
        super(plugin,"Aglet","#9696FF",Material.COPPER_INGOT,"aglet","Aglet",new ArrayList<>(List.of(ChatColor.GRAY+"5% increased movement speed",ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @Override
    public void activateEffect(Player player){
        player.setWalkSpeed(player.getWalkSpeed()+0.01f);
    }

    @Override
    public void deactivateEffect(Player player) {
        player.setWalkSpeed(Math.max(player.getWalkSpeed() - 0.01f, 0.2f));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Aglet(plugin).createItem();
    }

}
