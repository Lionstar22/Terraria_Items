package me.carson.terrariaItems.listenersHandler;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.plugin.Plugin;

public class ArmorChangeDetector implements Listener {

    // Armor slot indices in the player's inventory
    //private static final int HELMET_SLOT     = 39;
    //private static final int CHESTPLATE_SLOT = 38;
    //private static final int LEGGINGS_SLOT   = 37;
    //private static final int BOOTS_SLOT      = 36;
    private final Plugin plugin;

    public ArmorChangeDetector(Plugin plugin){
        this.plugin=plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        InventoryAction action = event.getAction();

        if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
            EquipmentSlot equipSlot = rawSlotToArmorSlot(event.getRawSlot());
            if (equipSlot == null) return;
            ItemStack newItem;
            ItemStack oldItem =event.getCurrentItem();

            if (action == InventoryAction.PLACE_ALL || action == InventoryAction.PLACE_ONE
                    || action == InventoryAction.PLACE_SOME || action == InventoryAction.SWAP_WITH_CURSOR) {
                newItem = nullToAir(event.getCursor());
            } else if (action == InventoryAction.PICKUP_ALL || action == InventoryAction.PICKUP_HALF
                    || action == InventoryAction.PICKUP_ONE || action == InventoryAction.PICKUP_SOME) {
                newItem = new ItemStack(Material.AIR);
            } else {
                return;
            }

            fireAndCancel(event, player, equipSlot, oldItem, newItem);
            return;
        }

        if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            ItemStack item = event.getCurrentItem();
            if (item == null) return;
            if (event.getClickedInventory() == null) return;
            if (event.getView().getTopInventory().getType() != InventoryType.CRAFTING) return;

            EquipmentSlot targetSlot = getArmorSlotForItem(item);
            if (targetSlot == null) return;

            ItemStack currentlyWearing = getArmorInSlot(player.getInventory(), targetSlot);
            ItemStack oldItem = nullToAir(currentlyWearing);
            ItemStack newItem = item.clone();

            fireAndCancel(event, player, targetSlot, oldItem, newItem);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getHand() != EquipmentSlot.HAND) return;
        ItemStack newItem=event.getItem();
        if(!isArmor(newItem)){return;}
        EquipmentSlot targetSlot=getArmorSlotForItem(newItem);
        Player player=event.getPlayer();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            ItemStack oldItem= event.getPlayer().getInventory().getItemInMainHand();
            fireAndCancel(event, player, targetSlot, oldItem, newItem);
        }, 1L);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        for (int slot : event.getRawSlots()) {
            if (isArmorSlot(slot)) {
                ItemStack oldItem = event.getOldCursor();
                ItemStack newItem = event.getNewItems().get(slot);
                fireAndCancel(event, player, rawSlotToArmorSlot(slot), oldItem, newItem);
            }
        }
    }

    private void fireAndCancel(Cancellable trigger, Player player,
                               EquipmentSlot slot,
                               ItemStack oldItem, ItemStack newItem) {
        ArmorChangeEvent armorEvent = new ArmorChangeEvent(player, slot, oldItem, newItem);
        player.getServer().getPluginManager().callEvent(armorEvent);

        if (armorEvent.isCancelled()) {
            trigger.setCancelled(true);
        }
    }

    private boolean isArmorSlot(int rawSlot) {
        return rawSlot >= 36 && rawSlot <= 39;
    }

    private ItemStack nullToAir(ItemStack item) {
        return (item == null) ? new ItemStack(Material.AIR) : item;
    }

    private EquipmentSlot rawSlotToArmorSlot(int rawSlot) {
        return switch (rawSlot) {
            case 39 -> EquipmentSlot.HEAD;
            case 38 -> EquipmentSlot.CHEST;
            case 37 -> EquipmentSlot.LEGS;
            default -> EquipmentSlot.FEET;
        };
    }

    private EquipmentSlot getArmorSlotForItem(ItemStack item) {
        if (item == null||!item.getItemMeta().hasEquippable()) return null;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.HEAD)
            return EquipmentSlot.HEAD;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.CHEST)
            return EquipmentSlot.CHEST;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.LEGS)
            return EquipmentSlot.LEGS;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.FEET)
            return EquipmentSlot.FEET;
        return null;
    }

    private ItemStack getArmorInSlot(PlayerInventory inv, EquipmentSlot slot) {
        return switch (slot) {
            case HEAD     -> inv.getHelmet();
            case CHEST -> inv.getChestplate();
            case LEGS   -> inv.getLeggings();
            case FEET      -> inv.getBoots();
            case HAND, SADDLE, BODY, OFF_HAND -> null;
        };
    }

    private Boolean isArmor(ItemStack item){
        if(item==null){return false;}
        if(!(item.getItemMeta() instanceof ArmorMeta)){return false;}
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.HEAD)
            return true;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.CHEST)
            return true;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.LEGS)
            return true;
        if (item.getItemMeta().getEquippable().getSlot()== EquipmentSlot.FEET)
            return true;
        return false;
    }
}