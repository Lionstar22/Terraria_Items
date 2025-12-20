package me.carson.terrariaItems.musicFolder;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Equippable;
import io.papermc.paper.datacomponent.item.JukeboxPlayable;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Jukebox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.JukeboxPlayableComponent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public abstract class MusicDisc {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final Material baseMaterial;
    protected final String texture;
    protected final String id;
    protected final String music;
    protected final ArrayList<String> lore;
    private final NamespacedKey customItemKey;
    private final NamespacedKey musicKey;
    //private JukeboxPlayableComponent jukeboxPlayableComponent;

    public MusicDisc(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, String music, ArrayList<String> lore){
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.id = id;
        this.music = music;
        customItemKey=new NamespacedKey(plugin, "customItem");
        musicKey=new NamespacedKey(plugin, "custom_music_disc");
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(baseMaterial);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria",texture));
        meta.getPersistentDataContainer().set(customItemKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(musicKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(1));
        //JukeboxPlayableComponent jukeboxPlayableComponent;
        //jukeboxPlayableComponent.setSongKey(new NamespacedKey("terraria",music));
        //meta.setJukeboxPlayable(jukeboxPlayableComponent);
        //JukeboxPlayable jukeboxPlayable = JukeboxPlayable.jukeboxPlayable((JukeboxSong) Key.key("terraria", music)).build();
        //item.setData(DataComponentTypes.JUKEBOX_PLAYABLE,jukeboxPlayable);
        item.setItemMeta(meta);
        return item;
    }

    public boolean isThisItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        String storedId = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return id.equals(storedId);
    }

}
