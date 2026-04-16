package me.carson.terrariaItems.handlers;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class MessageHandler implements Listener {

    public MessageHandler(Plugin plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(PlayerDataHandler.getInstance().getShowMsg(player.getUniqueId())){
            TextComponent start=new TextComponent("Thank you for using Terraria Items! Information about the plugin and items can be found ");
            TextComponent link=new TextComponent("here");
            link.setColor(ChatColor.AQUA);
            link.setClickEvent(new net.md_5.bungee.api.chat.ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.OPEN_URL,"https://github.com/CarsonWebb/Terraria_Items/wiki"));
            TextComponent end=new TextComponent(". This message can be toggled using /ti toggle_message.");
            start.addExtra(link);
            start.addExtra(end);
            player.spigot().sendMessage(ChatMessageType.CHAT,start);
        }
    }

}
