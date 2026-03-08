package me.carson.terrariaItems.listenersHandler;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class WorldDataHandler implements Listener {

    private static WorldDataHandler instance;
    private final File file;
    private final YamlConfiguration config;

    public WorldDataHandler(Plugin plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
        file = new File(plugin.getDataFolder(), "worldData.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public Boolean getHardmode(){return config.getBoolean(".hardmode",false);}
    public void setHardmode(Boolean set){
        config.set(".hardmode",set);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new WorldDataHandler(plugin);
    }

    public static WorldDataHandler getInstance() {
        return instance;
    }

    @EventHandler
    public void onDragonKill(EntityDeathEvent event){
        if(!(event.getEntity() instanceof EnderDragon)){return;}
        if(getHardmode()){return;}
        setHardmode(true);
        save();
        String light=(net.md_5.bungee.api.ChatColor.of("#FF96FF")+"light"+ChatColor.WHITE);
        String dark=(net.md_5.bungee.api.ChatColor.of("#B428FF")+"dark"+ChatColor.WHITE);
        String bar="The ancient spirits of "+light+" and "+dark+" have been released";
        for(Player player: Bukkit.getOnlinePlayers()){
            player.spigot().sendMessage(TextComponent.fromLegacy(bar));
        }
    }
}
