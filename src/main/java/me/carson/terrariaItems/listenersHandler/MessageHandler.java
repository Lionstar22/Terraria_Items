package me.carson.terrariaItems.listenersHandler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class MessageHandler implements Listener {

    public MessageHandler(Plugin plugin){

    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(PlayerDataHandler.getInstance().getShowMsg(player.getUniqueId())){
            player.sendMessage(Component.text("Thank you for using Terraria Items! Information about the plugin and items can be found ")
                    .append(
                            Component.text("here")
                                    .color(NamedTextColor.AQUA)
                                    .clickEvent(ClickEvent.openUrl("https://github.com/CarsonWebb/Terraria_Items/wiki"))
                    )
                    .append(Component.text(". This message can be toggled using /ti toggle_message.")));
        }
    }

}
