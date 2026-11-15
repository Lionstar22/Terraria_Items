package me.carson.terrariaItems;

import com.github.retrooper.packetevents.PacketEvents;
import com.google.gson.*;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.carson.terrariaItems.accesoryFolder.AccessoryListeners;
import me.carson.terrariaItems.blocksFolder.CustomBlockListeners;
import me.carson.terrariaItems.blocksFolder.CustomBlockManager;
import me.carson.terrariaItems.handlers.BloodMoonManager;
import me.carson.terrariaItems.enemiesFolder.EnemyManager;
import me.carson.terrariaItems.handlers.*;
import me.carson.terrariaItems.listeners.*;
import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.armorFolder.ArmorManager;
import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.toolFolder.ToolManager;
import me.carson.terrariaItems.weaponsFolder.WeaponListeners;
import me.carson.terrariaItems.weaponsFolder.WeaponManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public final class TerrariaItems extends JavaPlugin{



    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {

        TILangManager.initialize(this);

        PacketEvents.getAPI().init();

        ItemRegistry.initialize(this);
        WorldDataHandler.initialize(this);
        PlayerDataHandler.initialize(this);
        CustomPotionHandler.initialize(this);
        MaterialManager.initialize(this);
        ProjectileManager.initialize(this);
        ManaManager.initialize(this);
        AccessoryManager.initialize(this);
        ArmorManager.initialize(this);
        ResetHandler.initialize(this);
        BloodMoonManager.initialize(this);
        ToolManager.initialize(this);
        StealthManager.initialize(this);
        VanityManager.initialize(this);


        CustomRecipeDiscoverManager.initialize(this);

        CustomRecipeManager customRecipeManager = new CustomRecipeManager(this);
        customRecipeManager.registerAll();
        Bukkit.getPluginManager().registerEvents(new RecipeValidationListener(customRecipeManager), this);

        CustomRecipeDiscoverManager.getInstance().freeze();

        new WeaponManager(this);
        new ResourcePackHandler(this);
        new CustomBlockManager(this);
        new FishingManager(this);
        new ItemPlaceListener(this);
        new MessageHandler(this);
        new EnemyManager(this);
        new AccessoryListeners(this);
        new CustomBlockListeners(this);
        new WeaponListeners(this);
        new PlayerDamageHandler(this);
        new ArmorChangeDetector(this);
        new VillagerTradingListeners(this);
        new MobDeathListeners(this);
        new CustomCraftingListener(this);




        TICommand tiCommand = new TICommand(this);
        Objects.requireNonNull(getCommand("ti")).setExecutor(tiCommand);
        Objects.requireNonNull(getCommand("ti")).setTabCompleter(tiCommand);

        ManaManager manaManagerInstance=ManaManager.getInstance();
        manaManagerInstance.startManaRegen(this);
        manaManagerInstance.startFallingStartTask(this);

        StealthManager stealthManager=StealthManager.getInstance();
        stealthManager.startStealthRegen(this);
        stealthManager.startMovementChecks(this);
    }

    @Override
    public void onDisable() {
        for(Player player:Bukkit.getOnlinePlayers()){
            CustomPotionHandler.getInstance().removePotionAttributes(player);
            CustomPotionHandler.getInstance().removeCustomPotionEffects(player);
            CustomPotionHandler.getInstance().removeCustomFoodEffect(player);
        }
        cleanUpProjectiles();
        PlayerDataHandler.getInstance().save();
        PacketEvents.getAPI().terminate();
    }

    public void cleanUpProjectiles(){
        for (World world : Bukkit.getWorlds()) {
            if (world.getEnvironment() == World.Environment.NORMAL) {
                List<ItemDisplay> itemDisplays = (List<ItemDisplay>) world.getEntitiesByClass(ItemDisplay.class);
                for (ItemDisplay display : itemDisplays) {
                    display.remove();
                }
            }
        }
    }

    public static void regroupMappings(File in, File out, Collection<ItemStack> allItems) throws IOException {
        // item_model -> base material, taken from the real stacks
        Map<String, String> modelToMat = new HashMap<>();
        for (ItemStack item : allItems) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasItemModel()) {
                modelToMat.put(meta.getItemModel().toString(),
                        item.getType().getKey().toString()); // "minecraft:iron_sword"
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject root = gson.fromJson(Files.readString(in.toPath()), JsonObject.class);
        JsonArray paper = root.getAsJsonObject("items").getAsJsonArray("minecraft:paper");

        JsonObject regrouped = new JsonObject();
        for (JsonElement el : paper) {
            String model = el.getAsJsonObject().get("model").getAsString();
            String mat = modelToMat.get(model);
            if (mat == null) {
                Bukkit.getLogger().warning("No item found for model " + model);
                continue;
            }
            if (!regrouped.has(mat)) regrouped.add(mat, new JsonArray());
            regrouped.getAsJsonArray(mat).add(el);
        }
        root.add("items", regrouped);
        Files.writeString(out.toPath(), gson.toJson(root));
    }

}
