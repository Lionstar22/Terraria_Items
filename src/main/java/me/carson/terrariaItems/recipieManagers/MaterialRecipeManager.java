package me.carson.terrariaItems.recipieManagers;

import io.papermc.paper.registry.RegistryAccess;
import me.carson.terrariaItems.materialsFolder.materials.*;
import me.carson.terrariaItems.weaponsFolder.weapons.Volcano;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class MaterialRecipeManager implements Listener {

    private final Plugin plugin;

    public MaterialRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerDemoniteBarRecipe();
        registerHellstoneRecipe();
        registerRubyRecipe();
        registerHellstoneBarRecipe();
    }

    private void registerDemoniteBarRecipe(){
        ItemStack demoniteBar=new DemoniteBar(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "DemoniteBar");
        ShapedRecipe recipe = new ShapedRecipe(key, demoniteBar);
        recipe.shape(" S ","SIS"," S ");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.SOUL_SAND);
        Bukkit.addRecipe(recipe);
    }

    private void registerHellstoneRecipe(){
        ItemStack hellstone=new Hellstone(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "Hellstone");
        ShapelessRecipe recipe = new ShapelessRecipe(key, hellstone);
        recipe.addIngredient(Material.NETHERITE_SCRAP);
        recipe.addIngredient(Material.LAVA_BUCKET);
        recipe.addIngredient(Material.OBSIDIAN);
        Bukkit.addRecipe(recipe);
    }

    private void registerRubyRecipe(){
        ItemStack ruby=new Ruby(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "Ruby");
        ShapedRecipe recipe = new ShapedRecipe(key,ruby);
        recipe.shape("RRR","RDR","RRR");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }

    private void registerHellstoneBarRecipe(){
        ItemStack bar=new HellstoneBar(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "HellstoneBar");
        FurnaceRecipe recipe = new FurnaceRecipe(
                key,
                bar, // Result
                Material.MAGMA_BLOCK,                   // Ingredient (simple)
                0.35f,                               // XP
                50                                  // Cook time (10s)
        );
        Bukkit.addRecipe(recipe);
    }

}
