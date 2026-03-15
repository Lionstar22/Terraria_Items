package me.carson.terrariaItems.listenersHandler;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ArmorChangeEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;

    private final Player player;
    private final ItemStack oldItem;
    private final ItemStack newItem;
    private final EquipmentSlot slot;


    public enum ArmorSlot {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS
    }

    public ArmorChangeEvent(Player player, EquipmentSlot slot, ItemStack oldItem, ItemStack newItem) {
        this.player = player;
        this.slot = slot;
        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    public Player getPlayer()       { return player; }
    public EquipmentSlot getSlot()  { return slot; }
    public ItemStack getOldItem()   { return oldItem; }
    public ItemStack getNewItem()   { return newItem; }

    @Override
    public boolean isCancelled()            { return cancelled; }
    @Override
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    @Override
    public HandlerList getHandlers()                  { return HANDLERS; }
    public static HandlerList getHandlerList()        { return HANDLERS; }

}
