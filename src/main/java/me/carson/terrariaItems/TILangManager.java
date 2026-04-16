package me.carson.terrariaItems;


import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TILangManager {

    private static TILangManager instance;
    private final Plugin plugin;
    private final Map<String, FileConfiguration> langFiles = new HashMap<>();

    public TILangManager(Plugin plugin) {
        this.plugin = plugin;
        reload();
    }

    private void load() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.getDataFolder().mkdirs();
            YamlConfiguration defaultConfig = new YamlConfiguration();
            defaultConfig.set("language", "en_US");
            try {
                defaultConfig.save(configFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create default config.yml: " + e.getMessage());
            }
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        String locale = config.getString("language", "en_US");

        loadLangFile(locale, "accessories");
        loadLangFile(locale, "armor");
        loadLangFile(locale, "blocks");
        loadLangFile(locale, "enemies");
        loadLangFile(locale, "materials");
        loadLangFile(locale, "misc");
        loadLangFile(locale, "tools");
        loadLangFile(locale, "weapons");
        loadLangFile(locale, "commands");
    }

    private void loadLangFile(String locale, String fileName) {
        String resourcePath = "lang/" + locale + "/" + fileName + ".yml";
        File langFile = new File(plugin.getDataFolder(), resourcePath);

        langFile.getParentFile().mkdirs();

        if (plugin.getResource(resourcePath) != null) {
            plugin.saveResource(resourcePath, true); // always overwrite on startup
        } else {
            plugin.getLogger().warning("Resource not found in jar: " + resourcePath);
            // fallback to en_US
            resourcePath = "lang/en_US/" + fileName + ".yml";
            langFile = new File(plugin.getDataFolder(), resourcePath);
            langFile.getParentFile().mkdirs();
            if (plugin.getResource(resourcePath) != null) {
                plugin.saveResource(resourcePath, true);
            } else {
                plugin.getLogger().severe("Could not find fallback lang file: " + resourcePath);
                return;
            }
        }

        FileConfiguration lang = YamlConfiguration.loadConfiguration(langFile);

        // Merge any missing keys from the bundled resource as a safety net
        InputStream defStream = plugin.getResource(resourcePath);
        if (defStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(
                    new InputStreamReader(defStream, StandardCharsets.UTF_8)
            );
            lang.setDefaults(defConfig);
        }

        langFiles.put(fileName, lang);
        plugin.getLogger().info("Loaded lang file: " + resourcePath);
    }

    public void reload() {
        langFiles.clear();
        load();
    }

    public String get(String file, String path, String... replacements) {
        FileConfiguration lang = langFiles.get(file);
        if (lang == null) return "&c[Missing file: " + file + "]";
        String value = lang.getString(path, "&c[Missing: " + path + "]");
        for (int i = 0; i + 1 < replacements.length; i += 2) {
            value = value.replace("{" + replacements[i] + "}", replacements[i + 1]);
        }
        return ChatColor.translateAlternateColorCodes('&', value);
    }

    public List<String> getList(String file, String path) {
        FileConfiguration lang = langFiles.get(file);
        if (lang == null) return List.of("&c[Missing file: " + file + "]");
        List<String> colored = new ArrayList<>();
        for (String line : lang.getStringList(path)) {
            colored.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return colored;
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new TILangManager(plugin);
    }

    public static TILangManager getInstance() {
        return instance;
    }
}