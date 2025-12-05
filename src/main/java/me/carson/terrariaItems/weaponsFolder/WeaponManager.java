package me.carson.terrariaItems.weaponsFolder;

import me.carson.terrariaItems.weaponsFolder.weapons.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WeaponManager implements Listener {

    private final List<Weapon> weaponItems = new ArrayList<>();
    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public WeaponManager(Plugin plugin) {
        weaponItems.add(new MoltenFury(plugin));
        weaponItems.add(new LightsBane(plugin));
        weaponItems.add(new Volcano(plugin));
        weaponItems.add(new SnowballCannon(plugin));
        weaponItems.add(new Excalibur(plugin));
        weaponItems.add(new HallowedRepeater(plugin));
        weaponItems.add(new BladeOfGrass(plugin));
        weaponItems.add(new IceBlade(plugin));
        weaponItems.add(new Blowpipe(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new Volcano(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new SnowballCannon(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new BladeOfGrass(plugin), plugin);
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;
        Player player = event.getPlayer();

        long currentTime = System.currentTimeMillis();
        long lastTime = lastClickTime.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastTime < 10) {
            return;
        }
        lastClickTime.put(player.getUniqueId(), currentTime);

        for (Weapon item : weaponItems) {
            if (item.isThisItem(heldItem)) {
                event.setCancelled(true);
                if(!player.hasCooldown(heldItem)){
                    item.leftActivate(player);
                    player.setCooldown(heldItem, item.cooldown);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;
        Player player = event.getPlayer();

        long currentTime = System.currentTimeMillis();
        long lastTime = lastClickTime.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastTime < 10) {
            return;
        }
        lastClickTime.put(player.getUniqueId(), currentTime);

        for (Weapon item : weaponItems) {
            if (item.isThisItem(heldItem)) {
                event.setCancelled(true);
                if(!player.hasCooldown(heldItem)){
                    item.rightActivate(player);
                    player.setCooldown(heldItem, item.cooldown);
                    break;
                }
            }
        }
    }
}
