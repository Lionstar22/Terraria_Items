package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.materialsFolder.materials.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class MaterialRecipieManager {

    private final Plugin plugin;

    public MaterialRecipieManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerDemoniteBarRecipe();
        registerHellstoneRecipe();
        registerRubyRecipe();
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

}
