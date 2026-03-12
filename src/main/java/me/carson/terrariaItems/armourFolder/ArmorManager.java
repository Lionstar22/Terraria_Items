package me.carson.terrariaItems.armourFolder;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusBoots;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusChestplate;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusHelmet;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusLeggings;
import me.carson.terrariaItems.armourFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.spawner.SpawnerEntry;
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
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArmorManager implements Listener {

    private final HashMap<String, Armor> armorList = new HashMap<>();
    private final List<Armor> armorItems = new ArrayList<>();
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
        armorList.put("HallowedChestplate",new HallowedChestplate(plugin));
        armorList.put("HallowedLeggings",new HallowedLeggings(plugin));
        armorList.put("HallowedBoots",new HallowedBoots(plugin));
        armorList.put("HallowedElytra",new HallowedElytra(plugin));
        armorList.put("CactusHelmet",new CactusHelmet(plugin));
        armorList.put("CactusChestplate",new CactusChestplate(plugin));
        armorList.put("CactusLeggings",new CactusLeggings(plugin));
        armorList.put("CactusBoots",new CactusBoots(plugin));

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new MoltenHelmet(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new HallowedHelmet(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CactusHelmet(plugin), plugin);
    }

    public Armor getArmorPiece(ItemStack item){
        if(item==null|| !item.hasItemMeta()){return null;}
        String armorId= item.getItemMeta().getPersistentDataContainer().get(armorKey, PersistentDataType.STRING);
        return armorList.get(armorId);
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

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
            if (!(event.getWhoClicked() instanceof Player player)) return;
            if (event.isCancelled()) return;

            ItemStack oldArmor = null;
            ItemStack newArmor = null;

            if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
                oldArmor = event.getCurrentItem();
                newArmor = event.getCursor();
            }
            else if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                ItemStack item = event.getCurrentItem();
                if (getArmorPiece(item)!=null) {
                    oldArmor = null; // nothing was there
                    newArmor = item; // being equipped
                }
            }
            if(getArmorPiece(oldArmor)!=null){getArmorPiece(oldArmor).deactivateArmorEffect(player);}
            if(getArmorPiece(newArmor)!=null){getArmorPiece(newArmor).activateArmorEffect(player);}
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (event.isCancelled()) return;

        for (int slot : event.getRawSlots()) {
            if (isArmorSlot(slot)) {
                ItemStack oldArmor = event.getOldCursor();
                ItemStack newArmor = event.getNewItems().get(slot);

                if(getArmorPiece(oldArmor)!=null){getArmorPiece(oldArmor).deactivateArmorEffect(player);}
                if(getArmorPiece(newArmor)!=null){getArmorPiece(newArmor).activateArmorEffect(player);}
            }
        }
    }

    private boolean isArmorSlot(int rawSlot) {
        return rawSlot >= 36 && rawSlot <= 39;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        for (ItemStack armor : player.getInventory().getArmorContents()) {
            if(getArmorPiece(armor)!=null){getArmorPiece(armor).deactivateArmorEffect(player);}
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Player player = event.getPlayer();
            for (ItemStack armor : player.getInventory().getArmorContents()) {
                if(getArmorPiece(armor)!=null){getArmorPiece(armor).activateArmorEffect(player);}
            }
        }, 10L);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getHand() != EquipmentSlot.HAND) return;
        Player player = event.getPlayer();

        ItemStack newArmor = event.getItem();
        if(getArmorPiece(newArmor)!=null){getArmorPiece(newArmor).activateArmorEffect(player);}

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            ItemStack oldArmor=player.getInventory().getItemInMainHand();
            if(getArmorPiece(oldArmor)!=null){getArmorPiece(oldArmor).deactivateArmorEffect(player);}
        }, 1L);
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new ArmorManager(plugin);
    }

    public static ArmorManager getInstance() {
        return instance;
    }
}
