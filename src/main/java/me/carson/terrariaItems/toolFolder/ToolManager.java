package me.carson.terrariaItems.toolFolder;

import me.carson.terrariaItems.toolFolder.tools.*;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToolManager implements Listener {
    private final HashMap<String,Tool> toolList = new HashMap<>();
    private final NamespacedKey toolKey;

    public ToolManager(Plugin plugin) {
        toolKey = new NamespacedKey(plugin, "custom_item_id");

        toolList.put("Cosmolight",new Cosmolight(plugin));
        toolList.put("MomentumCapacitor",new MomentumCapacitor(plugin));
        toolList.put("RodOfDiscord",new RodOfDiscord(plugin));
        toolList.put("MagicMirror",new MagicMirror(plugin));
        toolList.put("LifeCrystal",new LifeCrystal(plugin));
        toolList.put("TorrentialTear",new TorrentialTear(plugin));
        toolList.put("ManaCrystal",new ManaCrystal(plugin));
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;
        Player player = event.getPlayer();

        String toolId=heldItem.getItemMeta().getPersistentDataContainer().get(toolKey, PersistentDataType.STRING);
        Tool tool = toolList.get(toolId);
        if(tool==null){return;}

        if(!player.hasCooldown(heldItem)){
            tool.rightActivate(player);
            player.setCooldown(heldItem, tool.cooldown);
        }else{
            tool.cooldownEffect(player);
        }
    }
}