package me.carson.terrariaItems.ListenersHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DatapackDownloader extends JavaPlugin {

    @Override
    public void onEnable() {
        String datapackUrl = "https://example.com/mydatapack.zip"; // Replace with your URL
        try {
            Path datapacksFolder = Path.of(Bukkit.getWorlds().get(0).getWorldFolder().getPath(), "datapacks");
            Files.createDirectories(datapacksFolder);

            Path downloadedZip = datapacksFolder.resolve("mydatapack.zip");

            downloadFile(datapackUrl, downloadedZip);
            unzip(downloadedZip, datapacksFolder);

            getLogger().info("✅ Datapack downloaded and extracted to " + datapacksFolder);

            // Reload datapacks in-game
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "datapack enable \"file/mydatapack\"");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "datapack reload");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downloadFile(String urlString, Path target) throws IOException {
        getLogger().info("⬇️ Downloading datapack from " + urlString);
        try (InputStream in = new URL(urlString).openStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private void unzip(Path zipFile, Path destDir) throws IOException {
        getLogger().info("📦 Extracting datapack...");
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile.toFile()))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path newPath = destDir.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(newPath);
                } else {
                    Files.createDirectories(newPath.getParent());
                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}

