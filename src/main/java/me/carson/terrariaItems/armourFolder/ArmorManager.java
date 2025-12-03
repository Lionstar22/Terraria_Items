package me.carson.terrariaItems.armourFolder;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Equippable;
import me.carson.terrariaItems.armourFolder.armors.GoldenCrown;
import me.carson.terrariaItems.armourFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ArmorManager implements Listener {

    private final List<Armor> armorItems = new ArrayList<>();

    public ArmorManager(Plugin plugin) {
        armorItems.add(new GoldenCrown(plugin));
        armorItems.add(new ShadowHelmet(plugin));
        armorItems.add(new ShadowScalemail(plugin));
        armorItems.add(new ShadowLeggings(plugin));
        armorItems.add(new ShadowGreaves(plugin));
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

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new MoltenHelmet(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new HallowedHelmet(plugin), plugin);
    }
    /*
    @EventHandler
    public void onArmorEquip(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        // Only handle clicks in armor slots
        if (event.getSlotType() != InventoryType.SlotType.ARMOR) return;
        ItemStack item = player.getInventory().getHelmet();
        armorEffect(player, item);
    }

    // Detect right-click armor equip (like shift-clicking armor)
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        ItemStack item = event.getItem();
        armorEffect(event.getPlayer(), item);
    }*/


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

        if (!helmet.hasData(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }
        if (!chest.hasData(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }
        if (!legs.hasData(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }
        if (!boots.hasData(DataComponentTypes.EQUIPPABLE)) {
            return false;
        }

        Equippable eqHelmet = helmet.getData(DataComponentTypes.EQUIPPABLE);
        if (eqHelmet.assetId() == null) {
            return false;
        }
        var helmetAssetId = eqHelmet.assetId();
        String helmetKey = helmetAssetId.namespace();

        Equippable eqChest = chest.getData(DataComponentTypes.EQUIPPABLE);
        if (eqChest.assetId() == null) {
            return false;
        }
        var chestAssetId = eqChest.assetId();
        String chestKey = chestAssetId.namespace();

        Equippable eqLegs = legs.getData(DataComponentTypes.EQUIPPABLE);
        if (eqLegs.assetId() == null) {
            return false;
        }
        var legsAssetId = eqLegs.assetId();
        String legsKey = legsAssetId.namespace();

        Equippable eqBoots = boots.getData(DataComponentTypes.EQUIPPABLE);
        if (eqBoots.assetId() == null) {
            return false;
        }
        var bootsAssetId = eqBoots.assetId();
        String bootsKey = bootsAssetId.namespace();
        return helmetKey.equals(chestKey) && helmetKey.equals(legsKey) && helmetKey.equals(bootsKey);
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
