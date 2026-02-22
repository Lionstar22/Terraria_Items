package me.carson.terrariaItems.listenersHandler;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class VanityManager implements Listener {

    private final NamespacedKey unmovableKey;
    private final PlayerDataHandler playerDataInstance = PlayerDataHandler.getInstance();

    public VanityManager(Plugin plugin){
        unmovableKey=new NamespacedKey(plugin, "unmovable");
        if (isProtocolLibPresent()) {
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }
    }

    public boolean isProtocolLibPresent() {
        try {
            Class.forName("com.comphenix.protocol.ProtocolLibrary");
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getView().title().equals(Component.text("Vanity Inventory"))) {return;}
        Player player = (Player) event.getPlayer();
        Inventory inv= event.getInventory();
        List<ItemStack> invList = new ArrayList<>();
        for(int i =0;i<4;i++){
            invList.add(i,inv.getItem(i+2));
        }

        playerDataInstance.setVanity(player.getUniqueId(), invList);
        playerDataInstance.save();
        applyVanity(invList,player);
    }

    public void applyVanity(List<ItemStack> invList,Player target){
        List<Pair<EnumWrappers.ItemSlot, ItemStack>> list = new ArrayList<>();

        if(invList.get(0)!=null){
            list.add(new Pair<>(EnumWrappers.ItemSlot.HEAD, invList.get(0)));
        }
        if(invList.get(1)!=null){
            list.add(new Pair<>(EnumWrappers.ItemSlot.CHEST, invList.get(1)));
        }
        if(invList.get(2)!=null){
            list.add(new Pair<>(EnumWrappers.ItemSlot.LEGS, invList.get(2)));
        }
        if(invList.get(3)!=null){
            list.add(new Pair<>(EnumWrappers.ItemSlot.FEET, invList.get(3)));
        }
        if (list.isEmpty()){return;}

        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT);
        packet.getIntegers().write(0, target.getEntityId());
        packet.getSlotStackPairLists().write(0, list);

        for (Player viewer : Bukkit.getOnlinePlayers()) {
            try {
                ProtocolLibrary.getProtocolManager().sendServerPacket(viewer, packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void openVanity(Player player){
        Inventory gui = Bukkit.createInventory(null, 9, Component.text("Vanity Inventory"));

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(""));
        meta.getPersistentDataContainer().set(unmovableKey, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);

        gui.setItem(0, item);
        gui.setItem(1, item);
        gui.setItem(6, item);
        gui.setItem(7, item);
        gui.setItem(8, item);

        List<ItemStack> invList= playerDataInstance.getVanity(player.getUniqueId());
        if(invList!=null) {
            for (int i = 0; i < 4; i++) {
                gui.setItem(i + 2, invList.get(i));
            }
        }
        player.openInventory(gui);
    }

    //Prevents players from removing the gray stained-glass
    @EventHandler
    public void onVanityClick(InventoryClickEvent event) {
        if (!event.getView().title().equals(Component.text("Vanity Inventory"))) return;

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null) return;
        ItemMeta clickedMeta=clicked.getItemMeta();

        if (clickedMeta.getPersistentDataContainer().has(unmovableKey)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        List<ItemStack> invList= playerDataInstance.getVanity(player.getUniqueId());
        applyVanity(invList,player);
    }
}
