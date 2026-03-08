package me.carson.terrariaItems.armourFolder;

import me.carson.terrariaItems.miscFolder.BasicItems.GoldenCrown;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusBoots;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusChestplate;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusHelmet;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusLeggings;
import me.carson.terrariaItems.armourFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ArmorManager implements Listener {

    private final List<Armor> armorItems = new ArrayList<>();

    public ArmorManager(Plugin plugin) {

        armorItems.add(new ShadowHelmet(plugin));
        armorItems.add(new ShadowScalemail(plugin));
        armorItems.add(new ShadowLeggings(plugin));
        armorItems.add(new ShadowGreaves(plugin));
        armorItems.add(new ShadowElytra(plugin));
        armorItems.add(new MoltenHelmet(plugin));
        armorItems.add(new MoltenChestplate(plugin));
        armorItems.add(new MoltenLeggings(plugin));
        armorItems.add(new MoltenBoots(plugin));
        armorItems.add(new MoltenElytra(plugin));
        armorItems.add(new HallowedBoots(plugin));
        armorItems.add(new HallowedLeggings(plugin));
        armorItems.add(new HallowedChestplate(plugin));
        armorItems.add(new HallowedHelmet(plugin));
        armorItems.add(new HallowedElytra(plugin));
        armorItems.add(new CactusHelmet(plugin));
        armorItems.add(new CactusChestplate(plugin));
        armorItems.add(new CactusLeggings(plugin));
        armorItems.add(new CactusBoots(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new MoltenHelmet(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new HallowedHelmet(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CactusHelmet(plugin), plugin);
    }

    public void startArmorTask(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                armorEffect(player, player.getInventory().getHelmet());
            }
        }, 0L, 100L); // Runs every five seconds
    }

    public Boolean hasSetBonus(Player player) {
        PlayerInventory inv = player.getInventory();

        ItemStack helmet = inv.getHelmet();
        ItemStack chest = inv.getChestplate();
        ItemStack legs = inv.getLeggings();
        ItemStack boots = inv.getBoots();
        if(helmet==null||chest==null||legs==null||boots==null){return false;}
        ItemMeta hMeta = helmet.getItemMeta();
        ItemMeta cMeta = chest.getItemMeta();
        ItemMeta lMeta = legs.getItemMeta();
        ItemMeta bMeta = boots.getItemMeta();
        if(hMeta==null||cMeta==null||lMeta==null||bMeta==null){return false;}
        String hKey=hMeta.getEquippable().getModel().getKey();
        String cKey=cMeta.getEquippable().getModel().getKey();
        String lKey=lMeta.getEquippable().getModel().getKey();
        String bKey=bMeta.getEquippable().getModel().getKey();

        return hKey.equals(cKey)&&hKey.equals(lKey)&&hKey.equals(bKey);
    }

    public void armorEffect(Player player, ItemStack item) {
        for (Armor armor : armorItems) {
            if (armor.isThisItem(item) && hasSetBonus(player)) {
                armor.activateArmorEffect(player);
            } else {
                armor.deactivateArmorEffect(player);
            }
        }

    }

}
