package me.carson.terrariaItems.bloodMoonManager;

import me.carson.terrariaItems.TILangManager;
import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.plugin.Plugin;

public class BloodMoonManager implements Listener{

    private final Plugin plugin;
    private boolean checked=false;
    private final WorldDataHandler instance=WorldDataHandler.getInstance();
    private final double chance=0.1111;
    public final TILangManager lang =TILangManager.getInstance();

    public BloodMoonManager(Plugin plugin){
        this.plugin=plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        countTime();
    }

    public int getMoonPhase(World world) {
        long days = world.getFullTime() / 24000;
        return (int) (days % 8);
    }

    public void countTime(){
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (World world : Bukkit.getWorlds()) {
                if (world.getEnvironment() != World.Environment.NORMAL) continue;

                long time = world.getTime();

                // Just turned night (13000 ticks) — roll for blood moon
                if ((time >= 13000 && time<18000 )&& !instance.getBloodMoon() && !checked &&getMoonPhase(world)!=4) {
                    checked=true;
                    if (Math.random() < chance) startBloodMoon(world);
                }

                // Approaching dawn (23000 ticks) — end blood moon
                if ((time >= 23000 || time<13000) && instance.getBloodMoon() &&checked) {
                    checked=false;
                    endBloodMoon(world);
                }
            }
        }, 0L, 40L);
    }

    public void startBloodMoon(World world){
        for(Player player:world.getPlayers()){
            player.sendMessage(ChatColor.DARK_RED+lang.get("commands","blood_moon_msg.rising"));
        }
        instance.setBloodMoon(true);
    }

    public void endBloodMoon(World world){
        for(Player player:world.getPlayers()){
            player.sendMessage(ChatColor.DARK_RED+lang.get("commands","blood_moon_msg.falling"));
        }
        instance.setBloodMoon(false);
    }

    @EventHandler
    public void onMonsterSpawn(CreatureSpawnEvent event){
        if(!instance.getBloodMoon()){return;}
        if (event.getEntity().getWorld().getEnvironment() != World.Environment.NORMAL){return;}
        EntityType type=event.getEntity().getType();
        if(type==EntityType.ZOMBIE||type==EntityType.SKELETON||type==EntityType.DROWNED){
            if(Math.random()<0.6){
                event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), type);
            }
            event.getEntity().getAttribute(Attribute.FOLLOW_RANGE).setBaseValue(96);
        }

    }
}
