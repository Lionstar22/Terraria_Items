package me.carson.terrariaItems.listenersHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DatapackDownloader {

    private final JavaPlugin plugin;

    public DatapackDownloader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void downloadDatapack(String urlString, String fileName) {

        // Prepare final filename before lambda
        final String finalName = fileName.endsWith(".zip") ? fileName : fileName + ".zip";

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                File datapackFolder = getDatapackFolder();
                File zipFile = new File(datapackFolder, finalName);

                plugin.getLogger().info("Downloading datapack: " + urlString);

                // ==== DOWNLOAD ZIP ====
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    plugin.getLogger().severe("Failed to download datapack (HTTP "
                            + connection.getResponseCode() + ")");
                    return;
                }

                try (InputStream in = connection.getInputStream();
                     FileOutputStream out = new FileOutputStream(zipFile)) {

                    byte[] buffer = new byte[8192];
                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1)
                        out.write(buffer, 0, bytesRead);
                }

                plugin.getLogger().info("Download completed. Unzipping...");

                // ==== UNZIP ====
                unzip(zipFile, datapackFolder);

                plugin.getLogger().info("Datapack unzipped successfully.");

                // Optionally delete ZIP
                zipFile.delete();

            } catch (Exception e) {
                plugin.getLogger().severe("Error downloading/unzipping datapack: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    // Unzips a ZIP file into the target folder
    private void unzip(File zip, File targetFolder) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                File newFile = new File(targetFolder, entry.getName());

                if (entry.isDirectory()) {
                    newFile.mkdirs();
                    continue;
                }

                // Make sure parent directories exist
                newFile.getParentFile().mkdirs();

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
            }
        }
    }

    // Get or create <world>/datapacks folder
    private File getDatapackFolder() throws IOException {
        String worldName = Bukkit.getWorlds().get(0).getName();
        File folder = new File(worldName + File.separator + "datapacks");

        if (!folder.exists()) {
            Files.createDirectories(folder.toPath());
            plugin.getLogger().info("Created datapacks folder: " + folder.getAbsolutePath());
        }

        return folder;
    }
}
