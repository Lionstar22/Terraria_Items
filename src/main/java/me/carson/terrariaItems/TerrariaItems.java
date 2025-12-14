package me.carson.terrariaItems;

import me.carson.terrariaItems.blocksFolder.CustomBlockManager;
import me.carson.terrariaItems.listenersHandler.*;
import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.armourFolder.ArmorManager;
import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.recipieManagers.*;
import me.carson.terrariaItems.toolFolder.ToolManager;
import me.carson.terrariaItems.weaponsFolder.WeaponManager;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.Stormbow;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;

public final class TerrariaItems extends JavaPlugin {

    Stormbow stormbow;
    private ManaManager manaManager;


    @Override
    public void onEnable() {
        stormbow=new Stormbow(this);

        AccessoryManager aManager = new AccessoryManager(this);
        //aManager.startAccessoryTask(this);

        ToolManager tManager=new ToolManager(this);
        MaterialManager materialManager=new MaterialManager(this);
        ProjectileManager projectileManager=new ProjectileManager(this);

        ArmorManager armorManager = new ArmorManager(this);
        armorManager.startArmorTask(this);

        WeaponManager weaponManager=new WeaponManager(this);

        CustomBlockManager customBlockManager=new CustomBlockManager(this);

        AccessoryRecipeManager accessoryRecipeManager = new AccessoryRecipeManager(this);
        accessoryRecipeManager.registerRecipes();
        ToolRecipeManager toolRecipeManager = new ToolRecipeManager(this);
        toolRecipeManager.registerRecipes();
        MaterialRecipeManager materialRecipieManager = new MaterialRecipeManager(this);
        materialRecipieManager.registerRecipes();
        WeaponRecipeManager weaponRecipieManager = new WeaponRecipeManager(this);
        weaponRecipieManager.registerRecipes();
        ArmorRecipeManager armorRecipieManager=new ArmorRecipeManager(this);
        armorRecipieManager.registerRecipes();
        BlocksRecipeManager blocksRecipeManager=new BlocksRecipeManager(this);
        blocksRecipeManager.registerRecipes();
        MiscRecipeManager miscRecipeManager=new MiscRecipeManager(this);
        miscRecipeManager.registerRecipes();

        getServer().getPluginManager().registerEvents(aManager, this);
        getServer().getPluginManager().registerEvents(tManager, this);
        getServer().getPluginManager().registerEvents(materialManager, this);
        getServer().getPluginManager().registerEvents(armorManager, this);
        getServer().getPluginManager().registerEvents(weaponManager, this);
        getServer().getPluginManager().registerEvents(customBlockManager, this);
        getServer().getPluginManager().registerEvents(projectileManager, this);
        getServer().getPluginManager().registerEvents(new ResourcePackHandler(), this);

        TTCommand ttCommand = new TTCommand(this);
        new CustomCraftingListener(this);
        getServer().getPluginManager().registerEvents(new ItemPlaceListener(this), this);

        Objects.requireNonNull(getCommand("tt")).setExecutor(ttCommand);
        Objects.requireNonNull(getCommand("tt")).setTabCompleter(ttCommand);

        //Mana stuff
        manaManager = new ManaManager(this);

        // Save mana on shutdown
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onQuit(PlayerQuitEvent event) {
                manaManager.save();
            }
        }, this);

        ManaManager.initialize(this);

        manaManager.startManaRegen(this);
        manaManager.startFallingStartTask(this);
    }

    @Override
    public void onDisable() {
        manaManager.getInstance().save();

    }

    public ManaManager getManaManager() {
        return manaManager;
    }
}
