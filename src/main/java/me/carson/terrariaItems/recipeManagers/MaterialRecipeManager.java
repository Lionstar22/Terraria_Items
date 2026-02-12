package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.materialsFolder.materials.*;
import me.carson.terrariaItems.materialsFolder.materials.bullets.BubonicRound;
import me.carson.terrariaItems.materialsFolder.materials.bullets.EmptyBullet;
import me.carson.terrariaItems.materialsFolder.materials.bullets.ExplodingBullet;
import me.carson.terrariaItems.materialsFolder.materials.bullets.MusketBall;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfFright;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfMight;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfSight;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
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
        registerHallowedBarRecipe();
        registerMusketBallRecipe();
        registerEmptyBulletRecipe();
        registerExplodingBulletRecipe();
        registerBubonicRoundRecipe();
    }

    private void registerDemoniteBarRecipe(){
        ItemStack demoniteBar=DemoniteBar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "DemoniteBar");
        ShapedRecipe recipe = new ShapedRecipe(key, demoniteBar);
        recipe.shape(" S ","SIS"," S ");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.SOUL_SAND);
        Bukkit.addRecipe(recipe);
    }

    private void registerHellstoneRecipe(){
        ItemStack hellstone=Hellstone.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Hellstone");
        ShapelessRecipe recipe = new ShapelessRecipe(key, hellstone);
        recipe.addIngredient(Material.NETHERITE_SCRAP);
        recipe.addIngredient(Material.LAVA_BUCKET);
        recipe.addIngredient(Material.OBSIDIAN);
        Bukkit.addRecipe(recipe);
    }

    private void registerRubyRecipe(){
        ItemStack ruby=Ruby.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Ruby");
        ShapedRecipe recipe = new ShapedRecipe(key,ruby);
        recipe.shape("RRR","RDR","RRR");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }

    private void registerHellstoneBarRecipe(){
        ItemStack bar=HellstoneBar.getItem(plugin);
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

    private void registerHallowedBarRecipe(){
        ItemStack hallow=HallowedBar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedBar");
        ShapelessRecipe recipe = new ShapelessRecipe(key, hallow);
        recipe.addIngredient(SoulOfFright.getItem(plugin));
        recipe.addIngredient(SoulOfSight.getItem(plugin));
        recipe.addIngredient(SoulOfMight.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerMusketBallRecipe(){
        ItemStack bullet= MusketBall.getItem(plugin);
        bullet.setAmount(9);
        NamespacedKey key = new NamespacedKey(plugin, "MusketBall");
        ShapelessRecipe recipe = new ShapelessRecipe(key, bullet);
        recipe.addIngredient(Material.IRON_NUGGET);
        Bukkit.addRecipe(recipe);
    }

    private void registerEmptyBulletRecipe(){
        ItemStack item= EmptyBullet.getItem(plugin);
        item.setAmount(8);
        NamespacedKey key = new NamespacedKey(plugin, "EmptyBullet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ","I I"," I ");
        recipe.setIngredient('I', Material.IRON_NUGGET);
        Bukkit.addRecipe(recipe);
    }

    private void registerExplodingBulletRecipe(){
        ItemStack item= ExplodingBullet.getItem(plugin);
        item.setAmount(50);
        ItemStack emptyBullet=EmptyBullet.getItem(plugin);
        emptyBullet.setAmount(8);
        NamespacedKey key = new NamespacedKey(plugin, "ExplodingBullet");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(emptyBullet);
        recipe.addIngredient(Material.GUNPOWDER);
        Bukkit.addRecipe(recipe);
    }

    private void registerBubonicRoundRecipe(){
        ItemStack item= BubonicRound.getItem(plugin);
        item.setAmount(50);
        ItemStack emptyBullet=EmptyBullet.getItem(plugin);
        emptyBullet.setAmount(8);
        NamespacedKey key = new NamespacedKey(plugin, "BubonicRound");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item);
        recipe.addIngredient(emptyBullet);
        recipe.addIngredient(Material.WITHER_ROSE);
        Bukkit.addRecipe(recipe);
    }


}
