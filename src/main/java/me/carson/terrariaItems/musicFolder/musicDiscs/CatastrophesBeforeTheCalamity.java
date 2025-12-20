package me.carson.terrariaItems.musicFolder.musicDiscs;

import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.musicFolder.MusicDisc;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CatastrophesBeforeTheCalamity extends MusicDisc {

    public CatastrophesBeforeTheCalamity(Plugin plugin) {
        super(plugin,"Music Disc","#B428FF", Material.MUSIC_DISC_5,"calamity_disc","CatastrophesBeforeTheCalamity","catastrophes_before_the_calamity", new ArrayList<>(List.of(ChatColor.GRAY+"DM DOKURO - Catastrophes Before The Calamity")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CatastrophesBeforeTheCalamity(plugin).createItem();
    }

}
