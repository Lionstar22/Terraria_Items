package me.carson.terrariaItems.recipieManagers;

import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.HallowedBar;
import me.carson.terrariaItems.materialsFolder.materials.HellstoneBar;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.HallowedRepeater;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.MoltenFury;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.Blowpipe;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.Minishark;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.SnowballCannon;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;

public class WeaponRecipeManager {

    private final Plugin plugin;

    public WeaponRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerLightsBaneRecipe();
        registerVolcanoRecipe();
        registerMoltenFury();
        registerExcaliburRecipe();
        registerHallowedRepeaterRecipe();
        registerSnowballCannonRecipe();
        registerBladeOfGrassRecipe();
        registerIceBladeRecipe();
        registerBlowpipeRecipe();
        registerMinisharkRecipe();
    }

    private void registerLightsBaneRecipe(){
        ItemStack bane= LightsBane.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "LightsBane");
        ShapedRecipe recipe = new ShapedRecipe(key, bane);
        recipe.shape(" D "," D "," S ");
        recipe.setIngredient('D',DemoniteBar.getItem(plugin));
        recipe.setIngredient('S',Material.STICK);
        Bukkit.addRecipe(recipe);
    }

    private void registerVolcanoRecipe(){
        ItemStack volcano = Volcano.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Volcano");
        ShapelessRecipe recipe = new ShapelessRecipe(key, volcano);
        recipe.addIngredient(Material.DIAMOND_SWORD);
        recipe.addIngredient(HellstoneBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }

    private void registerMoltenFury(){
        ItemStack fury= MoltenFury.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MoltenFury");
        ShapedRecipe recipe =new ShapedRecipe(key,fury);
        recipe.shape(" WS","WNS"," WS");
        recipe.setIngredient('W',Material.STICK);
        recipe.setIngredient('N',HellstoneBar.getItem(plugin));
        recipe.setIngredient('S',Material.STRING);
        Bukkit.addRecipe(recipe);
    }

    private void registerExcaliburRecipe(){
        ItemStack sword= Excalibur.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Excalibur");
        ShapedRecipe recipe =new ShapedRecipe(key,sword);
        recipe.shape(" H "," H "," S ");
        recipe.setIngredient('S',Material.STICK);
        recipe.setIngredient('H', HallowedBar.getItem(plugin));
        Bukkit.addRecipe(recipe);
    }
    private void registerHallowedRepeaterRecipe(){
        ItemStack repeater= HallowedRepeater.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "HallowedRepeater");
        ShapedRecipe recipe =new ShapedRecipe(key,repeater);
        recipe.shape("SHS","TKT"," S ");
        recipe.setIngredient('S',Material.STICK);
        recipe.setIngredient('H', HallowedBar.getItem(plugin));
        recipe.setIngredient('T', Material.STRING);
        recipe.setIngredient('K', Material.TRIPWIRE_HOOK);
        Bukkit.addRecipe(recipe);
    }

    private void registerSnowballCannonRecipe(){
        ItemStack snowball= SnowballCannon.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "SnowballCannon");
        ShapedRecipe recipe =new ShapedRecipe(key,snowball);
        recipe.shape(" IB","SCI","SS ");
        recipe.setIngredient('S',Material.SNOW_BLOCK);
        recipe.setIngredient('C', Material.CROSSBOW);
        recipe.setIngredient('B', Material.BLUE_ICE);
        recipe.setIngredient('I', Material.ICE);
        Bukkit.addRecipe(recipe);
    }

    private void registerBladeOfGrassRecipe(){
        ItemStack item= BladeOfGrass.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "BladeOfGrass");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("BSB","BIB","BSB");
        recipe.setIngredient('B',Material.BAMBOO);
        recipe.setIngredient('S',Material.SPIDER_EYE);
        recipe.setIngredient('I',Material.IRON_SWORD);
        Bukkit.addRecipe(recipe);
    }

    private void registerIceBladeRecipe(){
        ItemStack item= IceBlade.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "IceBlade");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("CCC","CIC","CCC");
        recipe.setIngredient('C',Material.SNOW_BLOCK);
        recipe.setIngredient('I',Material.IRON_SWORD);
        Bukkit.addRecipe(recipe);
    }

    private void registerBlowpipeRecipe(){
        ItemStack item= Blowpipe.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Blowpipe");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("S S","S S","S S");
        recipe.setIngredient('S',Material.STICK);
        Bukkit.addRecipe(recipe);
    }

    private void registerMinisharkRecipe(){
        ItemStack item= Minishark.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Minishark");
        ShapedRecipe recipe =new ShapedRecipe(key,item);
        recipe.shape("   ","FBI","   ");
        recipe.setIngredient('F',Material.COD);
        recipe.setIngredient('B',Material.IRON_BLOCK);
        recipe.setIngredient('I',Material.IRON_INGOT);
        Bukkit.addRecipe(recipe);
    }

}
