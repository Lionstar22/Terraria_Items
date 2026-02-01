package me.carson.terrariaItems;

import me.carson.terrariaItems.blocksFolder.CustomBlockManager;
import me.carson.terrariaItems.listenersHandler.*;
import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.armourFolder.ArmorManager;
import me.carson.terrariaItems.materialsFolder.MaterialManager;
import me.carson.terrariaItems.projectilesFolder.ProjectileManager;
import me.carson.terrariaItems.recipeManagers.*;
import me.carson.terrariaItems.toolFolder.ToolManager;
import me.carson.terrariaItems.weaponsFolder.WeaponManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public final class TerrariaItems extends JavaPlugin {

    private ManaManager manaManager;

    @Override
    public void onEnable() {
        AccessoryManager aManager = new AccessoryManager(this);
        //aManager.startAccessoryTask(this);

        ToolManager tManager=new ToolManager(this);

        MaterialManager.initialize(this);

        ProjectileManager.initialize(this);

        ArmorManager armorManager = new ArmorManager(this);
        armorManager.startArmorTask(this);

        WeaponManager weaponManager=new WeaponManager(this);

        CustomBlockManager customBlockManager=new CustomBlockManager(this);

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

        getServer().getPluginManager().registerEvents(aManager, this);
        getServer().getPluginManager().registerEvents(tManager, this);
        getServer().getPluginManager().registerEvents(armorManager, this);
        getServer().getPluginManager().registerEvents(weaponManager, this);
        getServer().getPluginManager().registerEvents(customBlockManager, this);
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
        cleanUpProjectiles();
        manaManager.getInstance().save();
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

    public ManaManager getManaManager() {
        return manaManager;
    }
}
