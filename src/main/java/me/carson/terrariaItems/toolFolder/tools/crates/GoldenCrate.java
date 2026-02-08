package me.carson.terrariaItems.toolFolder.tools.crates;

import me.carson.terrariaItems.accesoryFolder.accessories.Aglet;
import me.carson.terrariaItems.toolFolder.Tool;
import me.carson.terrariaItems.toolFolder.tools.LifeCrystal;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GoldenCrate extends Tool {

    public GoldenCrate(Plugin plugin){
        super(plugin,"Golden Crate","#FFC896", Material.ANGLER_POTTERY_SHERD,"golden_crate","GoldenCrate",0,new ArrayList<>(List.of(
                ChatColor.GRAY+"Right click to open")));
    }

    @Override
    public void rightActivate(Player player) {
        ItemStack crate = player.getInventory().getItemInMainHand();
        crate.setAmount(crate.getAmount()-1);
        double chance=Math.random();

        if(chance<0.13){//treasure loot table
            player.getInventory().addItem(LifeCrystal.getItem(plugin));
        }
        if(chance<0.2){//ore loot table
            int gold = ThreadLocalRandom.current().nextInt(25, 35);
            player.getInventory().addItem(new ItemStack(Material.RAW_GOLD,gold));
            int lapis = ThreadLocalRandom.current().nextInt(25, 35);
            player.getInventory().addItem(new ItemStack(Material.LAPIS_LAZULI,lapis));
        }
        if(chance<0.27){//Ingot Loot table
            int iron = ThreadLocalRandom.current().nextInt(8, 12);
            player.getInventory().addItem(new ItemStack(Material.IRON_INGOT,iron));
            int diamond = ThreadLocalRandom.current().nextInt(8, 12);
            player.getInventory().addItem(new ItemStack(Material.DIAMOND,diamond));
        }
        if(chance<0.33){//Potion loot table
            int x =ThreadLocalRandom.current().nextInt(1, 5);
            ItemStack potion = new ItemStack(Material.POTION);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            switch (x){
                case 1 -> meta.setBasePotionType(PotionType.LONG_FIRE_RESISTANCE);
                case 2 -> meta.setBasePotionType(PotionType.LUCK);
                case 3 -> meta.setBasePotionType(PotionType.LONG_INVISIBILITY);
                case 4 -> meta.setBasePotionType(PotionType.LONG_NIGHT_VISION);
                default -> {
                    return;
                }
            }
            potion.setItemMeta(meta);
            player.getInventory().addItem(potion);
        }
        if(chance<0.5){//Health Potion loot table
            ItemStack potion = new ItemStack(Material.POTION);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            meta.setBasePotionType(PotionType.STRONG_HEALING);
            potion.setItemMeta(meta);
            player.getInventory().addItem(potion);
        }

    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new GoldenCrate(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.setMaxStackSize(Integer.valueOf(64));
        item.setItemMeta(meta);
        return item;
    }

}
