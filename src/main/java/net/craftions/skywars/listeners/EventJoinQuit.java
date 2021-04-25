package net.craftions.skywars.listeners;

import net.craftions.skywars.Skywars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventJoinQuit implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(Skywars.getInstance().getSpawn());
        if (Skywars.getInstance().getCurrentGame() == null) {
            e.getPlayer().sendMessage("");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

    }
}
