package me.carson.terrariaItems.listenersHandler;

import me.carson.terrariaItems.projectilesFolder.projectiles.FallingStar;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ManaManager {
    private static ManaManager instance;

    private final File file;
    private final YamlConfiguration config;

    // In-memory player data
    private final Map<UUID, Double> currentMana = new HashMap<>();
    private final Map<UUID, Double> manaDelay = new HashMap<>();

    public ManaManager(Plugin plugin) {

        file = new File(plugin.getDataFolder(), "playerData.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);

        for (String key : config.getKeys(false)) {
            UUID uuid = UUID.fromString(key);

            double current = config.getDouble(key + ".current_mana", getMana(uuid));

            currentMana.put(uuid, current);
        }

    }

    public Double getMana(UUID uuid) {
        return currentMana.getOrDefault(uuid, 20.0);
    }

    public void setMana(UUID uuid, double amount) {
        double max = getMaxMana(uuid);
        currentMana.put(uuid, Math.min(amount, max));
    }

    public Double getMaxMana(UUID uuid) {
        PlayerDataHandler instance= PlayerDataHandler.getInstance();
        return instance.getMaxMana(uuid);
    }

    public void addMana(UUID uuid, double amount) {
        setMana(uuid, getMana(uuid) + amount);
    }

    public void removeMana(UUID uuid, double amount) {
        setMana(uuid, getMana(uuid) - amount);
    }

    public void startManaRegen(Plugin plugin){
        ManaManager instance = ManaManager.getInstance();
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UUID id = player.getUniqueId();
                if(instance.getManaDelay(id)<=0&&(instance.getMaxMana(id)>instance.getMana(id))){
                    instance.addMana(id,instance.getManaRegenRate(player,instance));
                    instance.updateManaBar(player);
                }else if(instance.getManaDelay(id)>-1){
                    instance.reduceManaDelay(player,1.0);
                }
            }
        }, 0L, 1L); // Runs every tick second
    }
    //if(instance.getManaDelay(id)>-1)
    public boolean isNight(World world) {
        long t = world.getTime();
        return t >= 12000 && t < 24000;
    }

    public void startFallingStartTask(Plugin plugin){
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.getLocation().getY()>45){
                    if (Math.random() < 0.25&& (player.getWorld().getEnvironment() == World.Environment.NORMAL) && isNight(player.getWorld())) {
                        new FallingStar(plugin).starFall(player);
                    }
                }

            }
        }, 0L, 300);
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new ManaManager(plugin);
    }

    public static ManaManager getInstance() {
        return instance;
    }

    public void updateManaBar(Player player) {
        UUID id = player.getUniqueId();
        double mana=instance.getMana(id);
        String formattedValue = String.format("%.1f", mana);
        String bar = ChatColor.AQUA + "Mana: " + formattedValue + ChatColor.GRAY + " / " + instance.getMaxMana(id);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(bar));
    }

    public double getRegenFactor(Player player,ManaManager instance){
        UUID id = player.getUniqueId();
        return (( instance.getMana(id) /instance.getMaxMana(id))*0.8+0.2);
    }

    public double getManaRegenRate(Player player,ManaManager instance){
        UUID id = player.getUniqueId();
        return (((instance.getMaxMana(id) /3)+1)*1.15*instance.getRegenFactor(player,instance))/20;
    }

    public void startManaRegenDelay(Player player, ManaManager instance){
        UUID id = player.getUniqueId();
        double x =1-( instance.getMana(id) / instance.getMaxMana(id));
        double delay= (0.7*(x*240+45))/2;
        manaDelay.put(id,delay);
    }

    public Double getManaDelay(UUID id){
        return manaDelay.getOrDefault(id, 0.0);
    }

    public void reduceManaDelay(Player player,Double amount){
        UUID id = player.getUniqueId();
        manaDelay.put(id,getManaDelay(id)-amount);
    }
}
