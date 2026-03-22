package me.carson.terrariaItems.armourFolder;

import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusBoots;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusChestplate;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusHelmet;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusLeggings;
import me.carson.terrariaItems.armourFolder.armors.forbiddenArmor.ForbiddenLeggings;
import me.carson.terrariaItems.armourFolder.armors.forbiddenArmor.ForbiddenMask;
import me.carson.terrariaItems.armourFolder.armors.forbiddenArmor.ForbiddenRobes;
import me.carson.terrariaItems.armourFolder.armors.forbiddenArmor.ForbiddenTreads;
import me.carson.terrariaItems.armourFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armourFolder.armors.jungleArmor.JungleHat;
import me.carson.terrariaItems.armourFolder.armors.jungleArmor.JungleLeggings;
import me.carson.terrariaItems.armourFolder.armors.jungleArmor.JunglePants;
import me.carson.terrariaItems.armourFolder.armors.jungleArmor.JungleShirt;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armourFolder.armors.necroArmor.NecroBreastplate;
import me.carson.terrariaItems.armourFolder.armors.necroArmor.NecroGreaves;
import me.carson.terrariaItems.armourFolder.armors.necroArmor.NecroHelmet;
import me.carson.terrariaItems.armourFolder.armors.necroArmor.NecroLeggings;
import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import me.carson.terrariaItems.listenersHandler.ArmorChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ArmorManager implements Listener {

    private final HashMap<String, Armor> armorList = new HashMap<>();
    private final NamespacedKey armorKey;
    protected final Plugin plugin;
    private static ArmorManager instance;

    public ArmorManager(Plugin plugin) {
        armorKey = new NamespacedKey(plugin, "custom_item_id");
        this.plugin=plugin;

        armorList.put("ShadowHelmet",new ShadowHelmet(plugin));
        armorList.put("ShadowScalemail",new ShadowScalemail(plugin));
        armorList.put("ShadowLeggings",new ShadowLeggings(plugin));
        armorList.put("ShadowGreaves",new ShadowGreaves(plugin));
        armorList.put("ShadowElytra",new ShadowElytra(plugin));
        armorList.put("MoltenHelmet",new MoltenHelmet(plugin));
        armorList.put("MoltenChestplate",new MoltenChestplate(plugin));;
        armorList.put("MoltenLeggings",new MoltenLeggings(plugin));
        armorList.put("MoltenBoots",new MoltenBoots(plugin));
        armorList.put("MoltenElytra",new MoltenElytra(plugin));
        armorList.put("HallowedHelmet",new HallowedHelmet(plugin));
        armorList.put("HallowedMask",new HallowedMask(plugin));
        armorList.put("HallowedHeadgear",new HallowedHeadgear(plugin));
        armorList.put("HallowedChestplate",new HallowedChestplate(plugin));
        armorList.put("HallowedLeggings",new HallowedLeggings(plugin));
        armorList.put("HallowedBoots",new HallowedBoots(plugin));
        armorList.put("HallowedElytra",new HallowedElytra(plugin));
        armorList.put("CactusHelmet",new CactusHelmet(plugin));
        armorList.put("CactusChestplate",new CactusChestplate(plugin));
        armorList.put("CactusLeggings",new CactusLeggings(plugin));
        armorList.put("CactusBoots",new CactusBoots(plugin));
        armorList.put("JungleHat",new JungleHat(plugin));
        armorList.put("JungleShirt",new JungleShirt(plugin));
        armorList.put("JungleLeggings",new JungleLeggings(plugin));
        armorList.put("JunglePants",new JunglePants(plugin));
        armorList.put("NecroHelmet",new NecroHelmet(plugin));
        armorList.put("NecroBreastplate",new NecroBreastplate(plugin));
        armorList.put("NecroLeggings",new NecroLeggings(plugin));
        armorList.put("NecroGreaves",new NecroGreaves(plugin));
        armorList.put("ForbiddenMask",new ForbiddenMask(plugin));
        armorList.put("ForbiddenRobes",new ForbiddenRobes(plugin));
        armorList.put("ForbiddenLeggings",new ForbiddenLeggings(plugin));
        armorList.put("ForbiddenTreads",new ForbiddenTreads(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new MoltenHelmet(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new HallowedMask(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CactusHelmet(plugin), plugin);
    }

    public Armor getArmorPiece(ItemStack item){
        if(item==null|| !item.hasItemMeta()){return null;}
        String armorId= item.getItemMeta().getPersistentDataContainer().get(armorKey, PersistentDataType.STRING);
        return armorList.get(armorId);
    }

    @EventHandler
    public void onArmorChange(ArmorChangeEvent event){
        Player player = event.getPlayer();
        ItemStack old = event.getOldItem();
        ItemStack current = event.getNewItem();
        if(getArmorPiece(old)!=null){getArmorPiece(old).deactivateArmorEffect(player);}
        if(getArmorPiece(current)!=null){getArmorPiece(current).activateArmorEffect(player);}
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(player.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)){return;}
        for (ItemStack armor : player.getInventory().getArmorContents()) {
            if(getArmorPiece(armor)!=null){
                getArmorPiece(armor).deactivateArmorEffect(player);}
        }
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new ArmorManager(plugin);
    }

    public static ArmorManager getInstance() {
        return instance;
    }
}
