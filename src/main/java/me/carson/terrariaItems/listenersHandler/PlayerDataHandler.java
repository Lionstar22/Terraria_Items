package me.carson.terrariaItems.listenersHandler;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataHandler {

    private static PlayerDataHandler instance;
    private final File file;
    private final YamlConfiguration config;

    public PlayerDataHandler(Plugin plugin){
        file = new File(plugin.getDataFolder(), "playerData.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public Boolean getShowMsg(UUID id){
        return config.getBoolean(id +".show_message",true);
    }
    public void setShowMsg(UUID id,Boolean set){
        config.set(id +".show_message",set);
    }
    public void toggleMsg(UUID id){
        setShowMsg(id,!getShowMsg(id));
    }

    public double getMaxMana(UUID id){
        return config.getDouble(id +".max_mana",20);
    }
    public void setMaxMana(UUID id, double x){
        config.set(id +".max_mana",x);
    }

    public double getMana(UUID id){
        return config.getDouble(id +".current_mana",20);
    }
    public void setMana(UUID id,double x){
        config.set(id +".current_mana",x);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new PlayerDataHandler(plugin);
    }

    public static PlayerDataHandler getInstance() {
        return instance;
    }

}
