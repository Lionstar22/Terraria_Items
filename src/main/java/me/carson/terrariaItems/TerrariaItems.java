package me.carson.terrariaItems;

import me.carson.terrariaItems.ListenersHandler.CraftBlockerListener;
import me.carson.terrariaItems.ListenersHandler.CustomCraftingListener;
import me.carson.terrariaItems.ListenersHandler.ItemPlaceListener;
import me.carson.terrariaItems.ListenersHandler.ResourcePackHandler;
import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.armourFolder.ArmorManager;
import me.carson.terrariaItems.recipieManagers.*;
import me.carson.terrariaItems.toolFolder.ToolManager;
import me.carson.terrariaItems.weaponsFolder.WeaponManager;
import me.carson.terrariaItems.weaponsFolder.weapons.Stormbow;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class TerrariaItems extends JavaPlugin {

    Stormbow stormbow;

    @Override
    public void onEnable() {
        stormbow=new Stormbow(this);

        AccessoryManager aManager = new AccessoryManager(this);
        aManager.startAccessoryTask(this);

        ToolManager tManager=new ToolManager(this);

        ArmorManager armorManager = new ArmorManager(this);
        armorManager.startArmorTask(this);

        WeaponManager weaponManager=new WeaponManager(this);

        AccessoryRecipeManager accessoryRecipeManager = new AccessoryRecipeManager(this);
        accessoryRecipeManager.registerRecipes();
        ToolRecipeManager toolRecipeManager = new ToolRecipeManager(this);
        toolRecipeManager.registerRecipes();
        MaterialRecipieManager materialRecipieManager = new MaterialRecipieManager(this);
        materialRecipieManager.registerRecipes();
        WeaponRecipieManager weaponRecipieManager = new WeaponRecipieManager(this);
        weaponRecipieManager.registerRecipes();
        ArmorRecipieManager armorRecipieManager=new ArmorRecipieManager(this);
        armorRecipieManager.registerRecipes();

        getServer().getPluginManager().registerEvents(aManager, this);
        getServer().getPluginManager().registerEvents(tManager, this);
        getServer().getPluginManager().registerEvents(armorManager, this);
        getServer().getPluginManager().registerEvents(weaponManager, this);
        getServer().getPluginManager().registerEvents(new ResourcePackHandler(), this);

        TTCommand ttCommand = new TTCommand(this);
        new CraftBlockerListener(this);
        new CustomCraftingListener(this);
        getServer().getPluginManager().registerEvents(new ItemPlaceListener(this), this);

        Objects.requireNonNull(getCommand("tt")).setExecutor(ttCommand);
        Objects.requireNonNull(getCommand("tt")).setTabCompleter(ttCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
