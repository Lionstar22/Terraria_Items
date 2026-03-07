package me.carson.terrariaItems.accesoryFolder.accessories;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CloudInABottle extends Accessory{

    private boolean jumped=false;

    public CloudInABottle(Plugin plugin){
        super(plugin,"Cloud in a Bottle","#9696FF",Material.FIREWORK_STAR,"cloud_in_a_bottle","CloudInABottle",new ArrayList<>(List.of(ChatColor.GRAY+"Allows the holder to double jump", ChatColor.GRAY+"Must be in accessory inventory")));
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR)) return;
        Player player = event.getPlayer();
        ItemStack glass = player.getInventory().getItemInMainHand();
        double height=player.getLocation().getY();
        if(glass.getType() == Material.GLASS_BOTTLE&&((height>=180)&&(height<=200))){
            glass.setAmount(glass.getAmount() - 1);
            player.getInventory().addItem(createItem());
        }
    }

    @Override
    public void activateEffect(Player player) {
    }

    @Override
    public void deactivateEffect(Player player) {
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CloudInABottle(plugin).createItem();
    }
}
