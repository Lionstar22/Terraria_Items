package me.carson.terrariaItems.toolFolder.tools.crates;

import me.carson.terrariaItems.accesoryFolder.accessories.LuckyHorseshoe;
import me.carson.terrariaItems.accesoryFolder.accessories.RedBalloon;
import me.carson.terrariaItems.toolFolder.Tool;
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

public class SkyCrate extends Tool {

    public SkyCrate(Plugin plugin){
        super(plugin,"Sky Crate","#96FF96", Material.ANGLER_POTTERY_SHERD,"sky_crate","SkyCrate",0,new ArrayList<>(List.of(
                ChatColor.GRAY+"Right click to open")));
    }

    @Override
    public void rightActivate(Player player) {
        ItemStack crate = player.getInventory().getItemInMainHand();
        crate.setAmount(crate.getAmount()-1);
        double chance=Math.random();

        //treasure loot table
        int treasure = ThreadLocalRandom.current().nextInt(1, 3);
        switch (treasure){
            case 1 -> player.getInventory().addItem(LuckyHorseshoe.getItem(plugin));
            case 2 -> player.getInventory().addItem(RedBalloon.getItem(plugin));
            default -> {
                return;
            }
        }
        if(chance<0.16){//ore loot table
            int gold = ThreadLocalRandom.current().nextInt(12, 22);
            player.getInventory().addItem(new ItemStack(Material.RAW_GOLD,gold));
            int iron = ThreadLocalRandom.current().nextInt(12, 22);
            player.getInventory().addItem(new ItemStack(Material.RAW_IRON,iron));
        }if(chance<0.20){//Ingot Loot table
            int gold = ThreadLocalRandom.current().nextInt(4, 8);
            player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT,gold));
            int iron = ThreadLocalRandom.current().nextInt(4, 8);
            player.getInventory().addItem(new ItemStack(Material.IRON_INGOT,iron));
        }
        if(chance<0.25){//Potion loot table
            int x =ThreadLocalRandom.current().nextInt(1, 5);
            ItemStack potion = new ItemStack(Material.POTION);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            switch (x){
                case 1 -> meta.setBasePotionType(PotionType.FIRE_RESISTANCE);
                case 2 -> meta.setBasePotionType(PotionType.STRENGTH);
                case 3 -> meta.setBasePotionType(PotionType.NIGHT_VISION);
                case 4 -> meta.setBasePotionType(PotionType.REGENERATION);
                default -> {
                    return;
                }
            }
            potion.setItemMeta(meta);
            player.getInventory().addItem(potion);
        }
        if(chance<0.50){//Health Potion loot table
            ItemStack potion = new ItemStack(Material.POTION);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            meta.setBasePotionType(PotionType.HEALING);
            potion.setItemMeta(meta);
            player.getInventory().addItem(potion);
        }
        if(chance<0.33){//Misc Loot table
            int bamboo = ThreadLocalRandom.current().nextInt(20, 51);
            player.getInventory().addItem(new ItemStack(Material.BAMBOO,bamboo));
        }

    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        ItemStack item=new SkyCrate(plugin).createItem();
        ItemMeta meta=item.getItemMeta();
        meta.setMaxStackSize(Integer.valueOf(64));
        item.setItemMeta(meta);
        return item;
    }

}
