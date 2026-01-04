package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.materialsFolder.materials.Ruby;
import me.carson.terrariaItems.toolFolder.tools.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class ToolRecipeManager {

    private final Plugin plugin;

    public ToolRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerMirrorRecipe();
        registerCosmolightRecipe();
        registerCapacitorRecipe();
        registerRodRecipe();
        registerLifeCrystalRecipe();
        registerManaCrystalRecipe();
        registerTorrentialTearRecipe();
    }

    private void registerMirrorRecipe(){
        ItemStack mirror=MagicMirror.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MagicMirror");
        ShapedRecipe recipe = new ShapedRecipe(key, mirror);
        recipe.shape("IDI","IGI","IDI");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }
    private void registerCosmolightRecipe(){
        ItemStack cosmolight=Cosmolight.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Cosmolight");
        ShapedRecipe recipe = new ShapedRecipe(key, cosmolight);
        recipe.shape("EGN","GCG","PGS");
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('E', Material.END_STONE);
        recipe.setIngredient('N', Material.NETHERRACK);
        recipe.setIngredient('S', Material.SUNFLOWER);
        recipe.setIngredient('P', Material.ENDER_PEARL);
        Bukkit.addRecipe(recipe);
    }
    private void registerCapacitorRecipe(){
        ItemStack capacitor=MomentumCapacitor.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MomentumCapacitor");
        ShapedRecipe recipe = new ShapedRecipe(key, capacitor);
        recipe.shape("WWW","WNW","WWW");
        recipe.setIngredient('W', Material.WIND_CHARGE);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(recipe);
    }
    private void registerRodRecipe(){
        ItemStack rod=RodOfDiscord.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "RodOfDiscord");
        ShapedRecipe recipe = new ShapedRecipe(key, rod);
        recipe.shape("ECE","ENE","ENE");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('C', Material.CHORUS_FRUIT);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(recipe);
    }

    private void registerLifeCrystalRecipe(){
        ItemStack lifeCrystal=LifeCrystal.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "LifeCrystal");
        ShapedRecipe recipe = new ShapedRecipe(key, lifeCrystal);
        recipe.shape("DCD","DDD"," D ");
        recipe.setIngredient('D', Ruby.getItem(plugin));
        recipe.setIngredient('C', Material.COBBLESTONE);
        Bukkit.addRecipe(recipe);
    }

    private void registerManaCrystalRecipe(){
        ItemStack crystal= ManaCrystal.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ManaCrystal");
        ShapedRecipe recipe = new ShapedRecipe(key, crystal);
        recipe.shape(" S ","SSS"," S ");
        recipe.setIngredient('S', FallenStar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerTorrentialTearRecipe(){
        ItemStack item= TorrentialTear.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "TorrentialTear");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" D ","WCW"," D ");
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('D', Material.SPONGE);
        recipe.setIngredient('W', Material.WET_SPONGE);
        Bukkit.addRecipe(recipe);
    }
}
