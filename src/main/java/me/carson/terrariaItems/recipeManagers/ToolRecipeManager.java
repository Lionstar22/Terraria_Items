package me.carson.terrariaItems.recipeManagers;

import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.materialsFolder.materials.Ruby;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfLight;
import me.carson.terrariaItems.materialsFolder.materials.souls.SoulOfNight;
import me.carson.terrariaItems.toolFolder.tools.*;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalEgg;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalShrieker;
import me.carson.terrariaItems.toolFolder.tools.summons.MechanicalSkull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ToolRecipeManager implements Listener {

    private final Plugin plugin;
    private final List<NamespacedKey> preHardmodeRecipes;
    private final List<NamespacedKey> HardmodeRecipes;
    private final WorldDataHandler worldInstance=WorldDataHandler.getInstance();

    public ToolRecipeManager(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        preHardmodeRecipes = List.of(
                new NamespacedKey(plugin, "MagicMirror"),
                new NamespacedKey(plugin, "MomentumCapacitor"),
                new NamespacedKey(plugin, "RodOfDiscord"),
                new NamespacedKey(plugin, "LifeCrystal"),
                new NamespacedKey(plugin, "ManaCrystal"),
                new NamespacedKey(plugin, "TorrentialTear")
        );

        HardmodeRecipes = List.of(
                new NamespacedKey(plugin, "Cosmolight"),
                new NamespacedKey(plugin, "MechanicalShrieker"),
                new NamespacedKey(plugin, "MechanicalEgg"),
                new NamespacedKey(plugin, "MechanicalSkull")
        );
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().discoverRecipes(preHardmodeRecipes);
        if(worldInstance.getHardmode()){
            event.getPlayer().discoverRecipes(HardmodeRecipes);
        }
    }

    public void registerRecipes() {
        registerMirrorRecipe();
        registerCosmolightRecipe();
        registerCapacitorRecipe();
        registerRodRecipe();
        registerLifeCrystalRecipe();
        registerManaCrystalRecipe();
        registerTorrentialTearRecipe();
        registerMechanicalShriekerRecipe();
        registerMechanicalEggRecipe();
        registerMechanicalSkullRecipe();
    }

    private void registerMirrorRecipe(){
        ItemStack mirror=MagicMirror.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MagicMirror");
        ShapedRecipe recipe = new ShapedRecipe(key, mirror);
        recipe.shape("IDI","IGI","IDI");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setCategory(CraftingBookCategory.MISC);Bukkit.addRecipe(recipe);
    }
    private void registerCosmolightRecipe(){
        ItemStack cosmolight=Cosmolight.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "Cosmolight");
        ShapedRecipe recipe = new ShapedRecipe(key, cosmolight);
        recipe.shape("LSL","SCS","NSN");
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }
    private void registerCapacitorRecipe(){
        ItemStack capacitor=MomentumCapacitor.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MomentumCapacitor");
        ShapedRecipe recipe = new ShapedRecipe(key, capacitor);
        recipe.shape("WWW","WNW","WWW");
        recipe.setIngredient('W', Material.WIND_CHARGE);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setCategory(CraftingBookCategory.MISC);
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
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerLifeCrystalRecipe(){
        ItemStack lifeCrystal=LifeCrystal.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "LifeCrystal");
        ShapedRecipe recipe = new ShapedRecipe(key, lifeCrystal);
        recipe.shape("DCD","DDD"," D ");
        recipe.setIngredient('D', new RecipeChoice.ExactChoice( Ruby.getItem(plugin)));
        recipe.setIngredient('C', Material.COBBLESTONE);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerManaCrystalRecipe(){
        ItemStack crystal= ManaCrystal.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "ManaCrystal");
        ShapedRecipe recipe = new ShapedRecipe(key, crystal);
        recipe.shape(" S ","SSS"," S ");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice( FallenStar.getItem(plugin)));
        recipe.setCategory(CraftingBookCategory.MISC);
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
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }
    private void registerMechanicalShriekerRecipe(){
        ItemStack item= MechanicalShrieker.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MechanicalShrieker");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NSN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', Material.SCULK_SHRIEKER);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerMechanicalEggRecipe(){
        ItemStack item= MechanicalEgg.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MechanicalEgg");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NCN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('C', Material.CHORUS_FRUIT);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }

    private void registerMechanicalSkullRecipe(){
        ItemStack item= MechanicalSkull.getItem(plugin);
        NamespacedKey key = new NamespacedKey(plugin, "MechanicalSkull");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" L ","NSN"," L ");
        recipe.setIngredient('L', new RecipeChoice.ExactChoice( SoulOfLight.getItem(plugin)));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice( SoulOfNight.getItem(plugin)));
        recipe.setIngredient('S', Material.WITHER_SKELETON_SKULL);
        recipe.setCategory(CraftingBookCategory.MISC);
        Bukkit.addRecipe(recipe);
    }
}
