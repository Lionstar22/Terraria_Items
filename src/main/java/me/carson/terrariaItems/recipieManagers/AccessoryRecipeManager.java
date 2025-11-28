package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class AccessoryRecipeManager {

    private final Plugin plugin;

    public AccessoryRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerAgletRecipe();
        registerObsidianSkullRecipe();
        registerBandOfRegenerationRecipe();
        registerRedBalloonRecipe();
        registerHorseshoeRecipe();
        registerCobaltShieldRecipe();
        registerCounterScarfRecipe();
        registerNeptuneShellRecipie();
    }

    private void registerAgletRecipe(){
        ItemStack aglet=Aglet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "aglet");
        ShapedRecipe recipe = new ShapedRecipe(key, aglet);
        recipe.shape("CCC","C C","CCC");
        recipe.setIngredient('C', Material.COPPER_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void registerObsidianSkullRecipe(){
        ItemStack skull=ObsidianSkull.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "skull");
        ShapedRecipe recipe = new ShapedRecipe(key, skull);
        recipe.shape("OOO","OOO"," O ");
        recipe.setIngredient('O', Material.OBSIDIAN);
        Bukkit.addRecipe(recipe);
    }

    private void registerRedBalloonRecipe(){
        ItemStack balloon=RedBalloon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "balloon");
        ShapedRecipe recipe = new ShapedRecipe(key, balloon);
        recipe.shape(" W "," S "," S ");
        recipe.setIngredient('W', Material.RED_WOOL);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

    private void registerBandOfRegenerationRecipe(){
        ItemStack band=BandOfRegeneration.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "band");
        ShapedRecipe recipe = new ShapedRecipe(key, band);
        recipe.shape("RRR","R R","RRR");
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void registerHorseshoeRecipe(){
        ItemStack horseshoe=LuckyHorseshoe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "LuckyHorseshoe");
        ShapedRecipe recipe = new ShapedRecipe(key, horseshoe);
        recipe.shape("G G","G G","GGG");
        recipe.setIngredient('G', Material.GOLD_BLOCK);
        Bukkit.addRecipe(recipe);
    }
    private void registerCobaltShieldRecipe(){
        ItemStack cobaltShield=CobaltShield.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CobaltShield");
        ShapedRecipe recipe = new ShapedRecipe(key, cobaltShield);
        recipe.shape("LSL","LNL"," L ");
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('S', Material.SHIELD);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(recipe);
    }
    private void registerCounterScarfRecipe(){
        ItemStack counterScarf=CounterScarf.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "CounterScarf");
        ShapedRecipe recipe = new ShapedRecipe(key, counterScarf);
        recipe.shape("ERE","RBR","ERE");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('R', Material.RED_WOOL);
        recipe.setIngredient('B', Material.ENDER_EYE);
        Bukkit.addRecipe(recipe);
    }
    private void registerNeptuneShellRecipie(){
        ItemStack neptunesShell=NeptunesShell.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NeptunesShell");
        ShapelessRecipe recipe = new ShapelessRecipe(key, neptunesShell);
        recipe.addIngredient(Material.BUBBLE_CORAL_BLOCK);
        recipe.addIngredient(Material.FIRE_CORAL_BLOCK);
        recipe.addIngredient(Material.BRAIN_CORAL_BLOCK);
        recipe.addIngredient(Material.HORN_CORAL_BLOCK);
        recipe.addIngredient(Material.TUBE_CORAL_BLOCK);
        recipe.addIngredient(Material.SEA_PICKLE);
        recipe.addIngredient(Material.PRISMARINE_CRYSTALS);
        recipe.addIngredient(Material.PRISMARINE_SHARD);
        recipe.addIngredient(Material.WET_SPONGE);
        Bukkit.addRecipe(recipe);
    }

}
