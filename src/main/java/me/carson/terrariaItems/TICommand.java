package me.carson.terrariaItems;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.armourFolder.armors.GoldenCrown;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusBoots;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusChestplate;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusHelmet;
import me.carson.terrariaItems.armourFolder.armors.cactusArmor.CactusLeggings;
import me.carson.terrariaItems.armourFolder.armors.hallowedArmor.*;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import me.carson.terrariaItems.listenersHandler.MessageHandler;
import me.carson.terrariaItems.listenersHandler.PlayerDataHandler;
import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.FallenStar;
import me.carson.terrariaItems.materialsFolder.materials.SoulOfMight;
import me.carson.terrariaItems.miscFolder.BasicItems.PickaxeAxe;
import me.carson.terrariaItems.toolFolder.tools.*;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.DaedalusStormbow;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.HallowedRepeater;
import me.carson.terrariaItems.weaponsFolder.weapons.bowFolder.bows.MoltenFury;
import me.carson.terrariaItems.weaponsFolder.weapons.gunFolder.guns.*;
import me.carson.terrariaItems.weaponsFolder.weapons.magicFolder.magicWeapons.*;
import me.carson.terrariaItems.weaponsFolder.weapons.swordFolder.swords.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TICommand implements CommandExecutor, TabCompleter {

    private final TerrariaItems plugin;

    public TICommand(TerrariaItems plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§eUsage: /ti <subcommand>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give" -> {
                if (args.length < 2) {
                    player.sendMessage("§cUsage: /ti give <item>");
                    return true;
                }

                String itemName = args[1].toLowerCase();
                switch (itemName) {
                    case "cosmolight"-> {
                        player.getInventory().addItem(Cosmolight.getItem(plugin));
                    }
                    case "rod_of_discord"-> {
                        player.getInventory().addItem(RodOfDiscord.getItem(plugin));
                    }
                    case "momentum_capacitor"-> {
                        player.getInventory().addItem(MomentumCapacitor.getItem(plugin));
                    }
                    case "stormbow"-> {
                        player.getInventory().addItem(DaedalusStormbow.getItem(plugin));
                    }
                    case "cloud_bottle"-> {
                        player.getInventory().addItem(CloudInABottle.getItem(plugin));
                    }
                    case "aglet"-> {
                        player.getInventory().addItem(Aglet.getItem(plugin));
                    }
                    case "obsidian_skull"-> {
                        player.getInventory().addItem(ObsidianSkull.getItem(plugin));
                    }
                    case "band_of_regeneration"-> {
                        player.getInventory().addItem(BandOfRegeneration.getItem(plugin));
                    }
                    case "red_balloon"-> {
                        player.getInventory().addItem(RedBalloon.getItem(plugin));
                    }
                    case "lucky_horseshoe"-> {
                        player.getInventory().addItem(LuckyHorseshoe.getItem(plugin));
                    }
                    case "magic_mirror"-> {
                        player.getInventory().addItem(MagicMirror.getItem(plugin));
                    }
                    case "cobalt_shield"-> {
                        player.getInventory().addItem(CobaltShield.getItem(plugin));
                    }
                    case "golden_crown"-> {
                        player.getInventory().addItem(GoldenCrown.getItem(plugin));
                    }
                    case "demonite_bar"-> {
                        player.getInventory().addItem(DemoniteBar.getItem(plugin));
                    }
                    case "lights_bane"-> {
                        player.getInventory().addItem(LightsBane.getItem(plugin));
                    }
                    case "shadow_armour"-> {
                        player.getInventory().addItem(ShadowHelmet.getItem(plugin));
                        player.getInventory().addItem(ShadowScalemail.getItem(plugin));
                        player.getInventory().addItem(ShadowLeggings.getItem(plugin));
                        player.getInventory().addItem(ShadowGreaves.getItem(plugin));
                    }
                    case "molten_armour"-> {
                        player.getInventory().addItem(MoltenHelmet.getItem(plugin));
                        player.getInventory().addItem(MoltenChestplate.getItem(plugin));
                        player.getInventory().addItem(MoltenLeggings.getItem(plugin));
                        player.getInventory().addItem(MoltenBoots.getItem(plugin));
                    }
                    case "counter_scarf"-> {
                        player.getInventory().addItem(CounterScarf.getItem(plugin));
                    }
                    case "molten_fury"-> {
                        player.getInventory().addItem(MoltenFury.getItem(plugin));
                    }
                    case "volcano"-> {
                        player.getInventory().addItem(Volcano.getItem(plugin));
                    }
                    case "hellforge"-> {
                        player.getInventory().addItem(Hellforge.getItem(plugin));
                    }
                    case "bezoar"-> {
                        player.getInventory().addItem(Bezoar.getItem(plugin));
                    }
                    case "blindfold"-> {
                        player.getInventory().addItem(Blindfold.getItem(plugin));
                    }
                    case "fast_clock"-> {
                        player.getInventory().addItem(FastClock.getItem(plugin));
                    }
                    case "vitamins"-> {
                        player.getInventory().addItem(Vitamins.getItem(plugin));
                    }
                    case "molten_elytra"-> {
                        player.getInventory().addItem(MoltenElytra.getItem(plugin));
                    }
                    case "shadow_elytra"-> {
                        player.getInventory().addItem(ShadowElytra.getItem(plugin));
                    }
                    case "ranger_emblem"-> {
                        player.getInventory().addItem(RangerEmblem.getItem(plugin));
                    }
                    case "warrior_emblem"-> {
                        player.getInventory().addItem(WarriorEmblem.getItem(plugin));
                    }
                    case "shackle"-> {
                        player.getInventory().addItem(Shackle.getItem(plugin));
                    }
                    case "might"-> {
                        player.getInventory().addItem(SoulOfMight.getItem(plugin));
                    }
                    case "excalibur"-> {
                        player.getInventory().addItem(Excalibur.getItem(plugin));
                    }
                    case "snowball_cannon"-> {
                        player.getInventory().addItem(SnowballCannon.getItem(plugin));
                    }
                    case "hallowed_repeater"-> {
                        player.getInventory().addItem(HallowedRepeater.getItem(plugin));
                    }
                    case "pickaxe_axe"-> {
                        player.getInventory().addItem(PickaxeAxe.getItem(plugin));
                    }
                    case "hallowed_armour"-> {
                        player.getInventory().addItem(HallowedHelmet.getItem(plugin));
                        player.getInventory().addItem(HallowedChestplate.getItem(plugin));
                        player.getInventory().addItem(HallowedLeggings.getItem(plugin));
                        player.getInventory().addItem(HallowedBoots.getItem(plugin));
                    }
                    case "hallowed_elytra"-> {
                        player.getInventory().addItem(HallowedElytra.getItem(plugin));
                    }
                    case "avenger_emblem"-> {
                        player.getInventory().addItem(AvengerEmblem.getItem(plugin));
                    }
                    case "blade_of_grass"-> {
                        player.getInventory().addItem(BladeOfGrass.getItem(plugin));
                    }
                    case "ice_blade"-> {
                        player.getInventory().addItem(IceBlade.getItem(plugin));
                    }
                    case "blowpipe"-> {
                        player.getInventory().addItem(Blowpipe.getItem(plugin));
                    }
                    case "minishark"-> {
                        player.getInventory().addItem(Minishark.getItem(plugin));
                    }
                    case "handgun"-> {
                        player.getInventory().addItem(Handgun.getItem(plugin));
                    }
                    case "shotgun"-> {
                        player.getInventory().addItem(Shotgun.getItem(plugin));
                    }
                    case "needler"-> {
                        player.getInventory().addItem(Needler.getItem(plugin));
                    }
                    case "christmastreesword"-> {
                        player.getInventory().addItem(ChristmasTreeSword.getItem(plugin));
                    }
                    case "mega_shark"-> {
                        player.getInventory().addItem(Megashark.getItem(plugin));
                    }
                    case "sniper_rifle"-> {
                        player.getInventory().addItem(SniperRifle.getItem(plugin));
                    }
                    case "phoenix_blaster"-> {
                        player.getInventory().addItem(PhoenixBlaster.getItem(plugin));
                    }
                    case "torrential_tear"-> {
                        player.getInventory().addItem(TorrentialTear.getItem(plugin));
                    }
                    case "amethyst_staff"-> {
                        player.getInventory().addItem(AmethystStaff.getItem(plugin));
                    }
                    case "ruby_staff"-> {
                        player.getInventory().addItem(RubyStaff.getItem(plugin));
                    }
                    case "mana_crystal"-> {
                        player.getInventory().addItem(ManaCrystal.getItem(plugin));
                    }
                    case "meteor_staff"-> {
                        player.getInventory().addItem(MeteorStaff.getItem(plugin));
                    }
                    case "bubble_gun"-> {
                        player.getInventory().addItem(BubbleGun.getItem(plugin));
                    }
                    case "water_bolt"-> {
                        player.getInventory().addItem(WaterBolt.getItem(plugin));
                    }
                    case "icicle_staff"-> {
                        player.getInventory().addItem(IcicleStaff.getItem(plugin));
                    }
                    case "neptunes_shell"-> {
                        player.getInventory().addItem(NeptunesShell.getItem(plugin));
                    }
                    case "ancient_fossil"-> {
                        player.getInventory().addItem(AncientFossil.getItem(plugin));
                    }
                    case "cactus_armor"-> {
                        player.getInventory().addItem(CactusHelmet.getItem(plugin));
                        player.getInventory().addItem(CactusChestplate.getItem(plugin));
                        player.getInventory().addItem(CactusLeggings.getItem(plugin));
                        player.getInventory().addItem(CactusBoots.getItem(plugin));
                    }
                    case "terra_blade"-> {
                        player.getInventory().addItem(TerraBlade.getItem(plugin));
                    }
                    case "star_cannon"-> {
                        player.getInventory().addItem(StarCannon.getItem(plugin));
                    }
                    case "fallen_star"-> {
                        ItemStack itemStack=FallenStar.getItem(plugin);
                        itemStack.setAmount(20);
                        player.getInventory().addItem(itemStack);
                    }
                    case "super_star_shooter"-> {
                        player.getInventory().addItem(SuperStarShooter.getItem(plugin));
                    }
                    case "sorcerer_emblem"-> {
                        player.getInventory().addItem(SorcererEmblem.getItem(plugin));
                    }

                    default -> player.sendMessage("§cUnknown item: " + itemName);
                }
            }
            case "toggle_message"->{
                PlayerDataHandler playerData= PlayerDataHandler.getInstance();
                playerData.toggleMsg(player.getUniqueId());
                playerData.save();
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // First argument: subcommands
            List<String> subCommands = Arrays.asList("give","toggle_message");
            StringUtil.copyPartialMatches(args[0], subCommands, completions);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            // Second argument: item names
            List<String> items = Arrays.asList("Cosmolight","warrior_emblem","sorcerer_emblem","super_star_shooter","star_cannon","fallen_star","cactus_armor","terra_blade","icicle_staff","bubble_gun","ancient_fossil","neptunes_shell","water_bolt","mana_crystal","meteor_staff","christmastreesword","ruby_staff","amethyst_staff","torrential_tear","phoenix_blaster","sniper_rifle","mega_shark","needler","minishark","shotgun","handgun","ice_blade","blowpipe","blade_of_grass","avenger_emblem","hallowed_elytra","pickaxe_axe","hallowed_armour","hallowed_repeater","excalibur","snowball_cannon","might","shackle","molten_elytra","ranger_emblem","shadow_elytra","blindfold","vitamins","fast_clock","Rod_of_Discord","bezoar","hellforge","molten_fury","volcano","counter_scarf","molten_armour","lights_bane","shadow_armour","Momentum_Capacitor","Stormbow","demonite_bar","Cloud_bottle","Aglet","Obsidian_Skull","red_balloon","band_of_regeneration","lucky_horseshoe","magic_mirror","cobalt_shield","golden_crown");
            StringUtil.copyPartialMatches(args[1], items, completions);
        }

        return completions;
    }
}