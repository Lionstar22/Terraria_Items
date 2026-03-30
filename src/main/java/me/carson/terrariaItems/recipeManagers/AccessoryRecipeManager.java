package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfFright;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfMight;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfSight;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
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
        //registerRedBalloonRecipe();
        //registerHorseshoeRecipe();
        registerCobaltShieldRecipe();
        registerCounterScarfRecipe();
        registerNeptuneShellRecipe();
        registerBezoarRecipe();
        registerBlindfoldRecipe();
        registerFastClockRecipe();
        registerVitaminsRecipe();
        registerAvengerEmblemRecipe();
        registerNightVisionHelmetRecipe();
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
    private void registerNeptuneShellRecipe(){
        ItemStack item=NeptunesShell.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NeptunesShell");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("STS","BHF","SUS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( SoulOfFright.getItem(plugin)));
        recipe.setIngredient('T', Material.TUBE_CORAL_BLOCK);
        recipe.setIngredient('B', Material.BRAIN_CORAL_BLOCK);
        recipe.setIngredient('H', Material.HORN_CORAL_BLOCK);
        recipe.setIngredient('F', Material.FIRE_CORAL_BLOCK);
        recipe.setIngredient('U', Material.BUBBLE_CORAL_BLOCK);
        Bukkit.addRecipe(recipe);
    }
    private void registerBezoarRecipe(){
        ItemStack bezoar=Bezoar.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Bezoar");
        ShapedRecipe recipe = new ShapedRecipe(key, bezoar);
        recipe.shape(" M ","MSM"," M ");
        recipe.setIngredient('M', Material.MOSS_BLOCK);
        recipe.setIngredient('S', Material.SPIDER_EYE);
        Bukkit.addRecipe(recipe);
    }
    private void registerBlindfoldRecipe(){
        ItemStack blind=Blindfold.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Blindfold");
        ShapedRecipe recipe = new ShapedRecipe(key, blind);
        recipe.shape("SSS","WEW","SSS");
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('W', Material.WHITE_WOOL);
        recipe.setIngredient('E', Material.ECHO_SHARD);
        Bukkit.addRecipe(recipe);
    }

    private void registerFastClockRecipe(){
        ItemStack clock=FastClock.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "FastClock");
        ShapedRecipe recipe = new ShapedRecipe(key, clock);
        recipe.shape("SSS","SCS","SSS");
        recipe.setIngredient('S', Material.SUGAR);
        recipe.setIngredient('C', Material.CLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void registerVitaminsRecipe(){
        ItemStack vitamin=Vitamins.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Vitamins");
        ShapelessRecipe recipe = new ShapelessRecipe(key, vitamin);
        recipe.addIngredient(Material.POTION);
        recipe.addIngredient(Material.SHORT_GRASS);
        recipe.addIngredient(Material.DRIED_KELP);
        recipe.addIngredient(Material.SWEET_BERRIES);
        recipe.addIngredient(Material.POTATO);
        recipe.addIngredient(Material.CARROT);
        recipe.addIngredient(Material.BEETROOT);
        recipe.addIngredient(Material.MILK_BUCKET);
        recipe.addIngredient(Material.HONEY_BOTTLE);
        Bukkit.addRecipe(recipe);
    }

    private void registerNightVisionHelmetRecipe(){
        ItemStack item=NightVisionHelmet.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "NightVisionHelmet");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("DDD","DCD","   ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('C', Material.GOLDEN_CARROT);
        Bukkit.addRecipe(recipe);
    }

    private void registerAvengerEmblemRecipe(){
        ItemStack item=AvengerEmblem.getItem(plugin);
        NamespacedKey key1 = new NamespacedKey(plugin, "AvengerEmblem1");
        ShapelessRecipe recipe1 = new ShapelessRecipe(key1, item);
        recipe1.addIngredient(new RecipeChoice.ExactChoice(WarriorEmblem.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe1.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        Bukkit.addRecipe(recipe1);

        NamespacedKey key2 = new NamespacedKey(plugin, "AvengerEmblem2");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, item);
        recipe2.addIngredient(new RecipeChoice.ExactChoice(RangerEmblem.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe2.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        Bukkit.addRecipe(recipe2);

        NamespacedKey key3 = new NamespacedKey(plugin, "AvengerEmblem3");
        ShapelessRecipe recipe3 = new ShapelessRecipe(key3, item);
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SorcererEmblem.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfMight.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfFright.getItem(plugin)));
        recipe3.addIngredient(new RecipeChoice.ExactChoice(SoulOfSight.getItem(plugin)));
        Bukkit.addRecipe(recipe3);
    }

}
