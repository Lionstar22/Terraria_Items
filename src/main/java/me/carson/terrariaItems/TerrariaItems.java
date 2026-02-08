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
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public final class TerrariaItems extends JavaPlugin {

    //private ManaManager manaManagerInstance;

    @Override
    public void onEnable() {
        PlayerDataHandler.initialize(this);
        PlayerDataHandler playerDataHandler=PlayerDataHandler.getInstance();

        AccessoryManager aManager = new AccessoryManager(this);
        //aManager.startAccessoryTask(this);

        ToolManager tManager=new ToolManager(this);

        MaterialManager.initialize(this);

        ProjectileManager.initialize(this);

        ArmorManager armorManager = new ArmorManager(this);
        armorManager.startArmorTask(this);

        WeaponManager weaponManager=new WeaponManager(this);

        CustomBlockManager customBlockManager=new CustomBlockManager(this);

        FishingManager fishingManager=new FishingManager(this);

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
        getServer().getPluginManager().registerEvents(new MessageHandler(this), this);
        getServer().getPluginManager().registerEvents(fishingManager, this);

        TICommand tiCommand = new TICommand(this);
        new CustomCraftingListener(this);
        getServer().getPluginManager().registerEvents(new ItemPlaceListener(this), this);

        Objects.requireNonNull(getCommand("ti")).setExecutor(tiCommand);
        Objects.requireNonNull(getCommand("ti")).setTabCompleter(tiCommand);

        //MessageHandler.initialize(this);
        ManaManager.initialize(this);

        ManaManager manaManagerInstance=ManaManager.getInstance();

        manaManagerInstance.startManaRegen(this);
        manaManagerInstance.startFallingStartTask(this);


    }

    @Override
    public void onDisable() {
        cleanUpProjectiles();
        PlayerDataHandler.getInstance().save();
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
