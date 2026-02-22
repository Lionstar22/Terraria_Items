package me.carson.terrariaItems.listenersHandler;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

    
    public List<ItemStack> getInventory(UUID id){
        return (List<ItemStack>) config.getList(id+".accessory_inv");
    }
    public void setInventory(UUID id,List<ItemStack> list){
        config.set(id+".accessory_inv",list);
    }

    public List<ItemStack> getVanity(UUID id){
        return (List<ItemStack>) config.getList(id+".vanity_inv");
    }
    public void setVanity(UUID id,List<ItemStack> list){
        config.set(id+".vanity_inv",list);
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
