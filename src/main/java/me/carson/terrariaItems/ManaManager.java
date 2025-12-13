package me.carson.terrariaItems;

import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.projectilesFolder.projectiles.FallingStar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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
    private final Map<UUID, Double> maxMana = new HashMap<>();
    private final Map<UUID, Double> manaDelay = new HashMap<>();

    public ManaManager(Plugin plugin) {
        file = new File(plugin.getDataFolder(), "mana.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);

        // Load existing player mana
        for (String key : config.getKeys(false)) {
            UUID uuid = UUID.fromString(key);

            double current = config.getDouble(key + ".current", getMana(uuid));
            double max = config.getDouble(key + ".max", getMaxMana(uuid));

            currentMana.put(uuid, current);
            maxMana.put(uuid, max);
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
        return maxMana.getOrDefault(uuid, 20.0);
    }

    public void setMaxMana(UUID uuid, double amount) {
        maxMana.put(uuid, amount);

        // Ensure current mana is not above new max
        double current = getMana(uuid);
        if (current > amount) {
            currentMana.put(uuid, amount);
        }
    }

    public void addMana(UUID uuid, double amount) {
        setMana(uuid, getMana(uuid) + amount);
    }

    public void removeMana(UUID uuid, double amount) {
        setMana(uuid, getMana(uuid) - amount);
    }

    public void save() {
        for (UUID uuid : currentMana.keySet()) {
            config.set(uuid.toString() + ".current", currentMana.get(uuid));
            config.set(uuid.toString() + ".max", maxMana.get(uuid));
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                if (Math.random() < 0.5&& (player.getWorld().getEnvironment() == World.Environment.NORMAL) && isNight(player.getWorld())) {
                    new FallingStar(plugin).starFall(player);
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
        player.sendActionBar(Component.text(bar));
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
