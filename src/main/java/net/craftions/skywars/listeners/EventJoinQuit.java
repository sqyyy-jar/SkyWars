package net.craftions.skywars.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.craftions.skywars.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventJoinQuit implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(Skywars.getInstance().getSpawn());
        if (Skywars.getInstance().getCurrentGame() == null) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");
            e.getPlayer().sendPluginMessage(Skywars.getInstance(), "BungeeCord", out.toByteArray());
            return;
        }
        if (Skywars.getInstance().getCurrentGame().isStarted() || Skywars.getInstance().getCurrentGame().isReady()) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");
            e.getPlayer().sendPluginMessage(Skywars.getInstance(), "BungeeCord", out.toByteArray());
            return;
        }
        Skywars.getInstance().getCurrentGame().initPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (Skywars.getInstance().getCurrentGame() == null) {
            e.setQuitMessage(null);
            return;
        }
        Skywars.getInstance().getCurrentGame().disconnect(e.getPlayer());
    }
}
