package me.carson.terrariaItems.weaponsFolder;

import me.carson.terrariaItems.weaponsFolder.weapons.LightsBane;
import me.carson.terrariaItems.weaponsFolder.weapons.MoltenFury;
import me.carson.terrariaItems.weaponsFolder.weapons.Volcano;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class WeaponManager implements Listener {

    private final List<Weapon> weaponItems = new ArrayList<>();

    public WeaponManager(Plugin plugin) {
        weaponItems.add(new MoltenFury(plugin));
        weaponItems.add(new LightsBane(plugin));
        weaponItems.add(new Volcano(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new Volcano(plugin), plugin);
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;
        Player player = event.getPlayer();

        for (Weapon item : weaponItems) {
            if (item.isThisItem(heldItem)) {
                event.setCancelled(true);
                if(!player.hasCooldown(heldItem.getType())){
                    item.leftActivate(player);
                    player.setCooldown(heldItem.getType(), item.cooldown);
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

        for (Weapon item : weaponItems) {
            if (item.isThisItem(heldItem)) {
                event.setCancelled(true);
                if(!player.hasCooldown(heldItem.getType())){
                    item.rightActivate(player);
                    player.setCooldown(heldItem.getType(), item.cooldown);
                }
            }
        }
    }
}
