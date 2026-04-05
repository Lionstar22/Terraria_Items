package me.carson.terrariaItems;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.carson.terrariaItems.accesoryFolder.AccessoryListeners;
import me.carson.terrariaItems.blocksFolder.CustomBlockListeners;
import me.carson.terrariaItems.blocksFolder.CustomBlockManager;
import me.carson.terrariaItems.enemiesFolder.EnemyManager;
import me.carson.terrariaItems.listenersHandler.*;
import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.armourFolder.ArmorManager;
import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.materialsFolder.MaterialsListeners;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.recipeManagers.*;
import me.carson.terrariaItems.toolFolder.ToolManager;
import me.carson.terrariaItems.weaponsFolder.WeaponListeners;
import me.carson.terrariaItems.weaponsFolder.WeaponManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public final class TerrariaItems extends JavaPlugin{

    @Override
    public void onEnable() {

        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
        PacketEvents.getAPI().init();

        WorldDataHandler.initialize(this);
        PlayerDataHandler.initialize(this);
        MaterialManager.initialize(this);
        ProjectileManager.initialize(this);
        ManaManager.initialize(this);
        AccessoryManager.initialize(this);
        ArmorManager.initialize(this);
        ResetHandler.initialize(this);

        AccessoryRecipeManager accessoryRecipeManager = new AccessoryRecipeManager(this);
        accessoryRecipeManager.registerRecipes();
        ToolRecipeManager toolRecipeManager = new ToolRecipeManager(this);
        toolRecipeManager.registerRecipes();
        MaterialRecipeManager materialRecipeManager = new MaterialRecipeManager(this);
        materialRecipeManager.registerRecipes();
        WeaponRecipeManager weaponRecipeManager = new WeaponRecipeManager(this);
        weaponRecipeManager.registerRecipes();
        ArmorRecipeManager armorRecipeManager=new ArmorRecipeManager(this);
        armorRecipeManager.registerRecipes();
        BlocksRecipeManager blocksRecipeManager=new BlocksRecipeManager(this);
        blocksRecipeManager.registerRecipes();
        MiscRecipeManager miscRecipeManager=new MiscRecipeManager(this);
        miscRecipeManager.registerRecipes();

        new WeaponManager(this);
        new ResourcePackHandler(this);
        new CustomCraftingListener(this);
        new CustomBlockManager(this);
        new CustomCraftingListener(this);
        new FishingManager(this);
        new ItemPlaceListener(this);
        new MessageHandler(this);
        new VanityManager(this);
        new ToolManager(this);
        new EnemyManager(this);
        new AccessoryListeners(this);
        new CustomBlockListeners(this);
        new MaterialsListeners(this);
        new WeaponListeners(this);
        new PlayerDamageHandler(this);
        new ArmorChangeDetector(this);
        new VillagerTradingListeners(this);

        TICommand tiCommand = new TICommand(this);
        Objects.requireNonNull(getCommand("ti")).setExecutor(tiCommand);
        Objects.requireNonNull(getCommand("ti")).setTabCompleter(tiCommand);

        ManaManager manaManagerInstance=ManaManager.getInstance();
        manaManagerInstance.startManaRegen(this);
        manaManagerInstance.startFallingStartTask(this);
    }

    @Override
    public void onDisable() {
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

}
