package me.carson.terrariaItems;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.armourFolder.armors.GoldenCrown;
import me.carson.terrariaItems.armourFolder.armors.moltenArmor.*;
import me.carson.terrariaItems.armourFolder.armors.shadowArmor.*;
import me.carson.terrariaItems.blocksFolder.blocks.Hellforge;
import me.carson.terrariaItems.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaItems.materialsFolder.materials.SoulOfMight;
import me.carson.terrariaItems.miscFolder.BasicItems.PickaxeAxe;
import me.carson.terrariaItems.toolFolder.tools.Cosmolight;
import me.carson.terrariaItems.toolFolder.tools.MagicMirror;
import me.carson.terrariaItems.toolFolder.tools.MomentumCapacitor;
import me.carson.terrariaItems.toolFolder.tools.RodOfDiscord;
import me.carson.terrariaItems.weaponsFolder.weapons.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TTCommand implements CommandExecutor, TabCompleter {

    private final TerrariaItems plugin;

    public TTCommand(TerrariaItems plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§eUsage: /tt <subcommand>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give" -> {
                if (args.length < 2) {
                    player.sendMessage("§cUsage: /tt give <item>");
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
                        player.getInventory().addItem(plugin.stormbow.createItem());
                    }
                    case "cloud_bottle"-> {
                        player.getInventory().addItem(CloudInBottle.getItem(plugin));
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

                    default -> player.sendMessage("§cUnknown item: " + itemName);
                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // First argument: subcommands
            List<String> subCommands = Arrays.asList("give");
            StringUtil.copyPartialMatches(args[0], subCommands, completions);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            // Second argument: item names
            List<String> items = Arrays.asList("Cosmolight","warrior_emblem","pickaxe_axe","hallowed_repeater","excalibur","snowball_cannon","might","shackle","molten_elytra","ranger_emblem","shadow_elytra","blindfold","vitamins","fast_clock","Rod_of_Discord","bezoar","hellforge","molten_fury","volcano","counter_scarf","molten_armour","lights_bane","shadow_armour","Momentum_Capacitor","Stormbow","demonite_bar","Cloud_bottle","Aglet","Obsidian_Skull","red_balloon","band_of_regeneration","lucky_horseshoe","magic_mirror","cobalt_shield","golden_crown");
            StringUtil.copyPartialMatches(args[1], items, completions);
        }

        return completions;
    }
}