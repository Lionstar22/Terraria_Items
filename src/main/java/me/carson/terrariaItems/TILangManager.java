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
        load();
    }

    private void load() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.getDataFolder().mkdirs();
            YamlConfiguration defaultConfig = new YamlConfiguration();
            defaultConfig.set("language", "en_us");
            try {
                defaultConfig.save(configFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create default config.yml: " + e.getMessage());
            }
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        String locale = config.getString("language", "en_us");

        loadLangFile(locale, "accessories");
        loadLangFile(locale, "armor");
        loadLangFile(locale, "blocks");
        loadLangFile(locale, "enemies");
        loadLangFile(locale, "materials");
        loadLangFile(locale, "misc");
        loadLangFile(locale, "tools");
        loadLangFile(locale, "weapons");
        loadLangFile(locale, "commands");
        // Add more files here as needed
    }

    private void loadLangFile(String locale, String fileName) {
        String resourcePath = "lang/" + locale + "/" + fileName + ".yml";
        File langFile = new File(plugin.getDataFolder(), resourcePath);

        if (!langFile.exists()) {
            langFile.getParentFile().mkdirs();
            if (plugin.getResource(resourcePath) != null) {
                plugin.saveResource(resourcePath, false);
            } else {
                // Fallback to en_US
                plugin.getLogger().warning("Could not find '" + resourcePath + "', falling back to en_US.");
                resourcePath = "lang/en_us/" + fileName + ".yml";
                langFile = new File(plugin.getDataFolder(), resourcePath);
                if (!langFile.exists()) plugin.saveResource(resourcePath, false);
            }
        }

        FileConfiguration lang = YamlConfiguration.loadConfiguration(langFile);

        // Merge missing keys from bundled resource
        InputStream defStream = plugin.getResource(resourcePath);
        if (defStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(
                    new InputStreamReader(defStream, StandardCharsets.UTF_8)
            );
            lang.setDefaults(defConfig);
        }

        langFiles.put(fileName, lang);
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