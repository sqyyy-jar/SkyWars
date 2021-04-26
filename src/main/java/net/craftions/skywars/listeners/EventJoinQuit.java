package net.craftions.skywars.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.craftions.skywars.Skywars;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventJoinQuit implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.setGameMode(GameMode.SURVIVAL);
        p.getInventory().clear();
        p.teleport(Skywars.getInstance().getSpawn());
        if (Skywars.getInstance().getCurrentGame() == null) {
            p.sendMessage("§cNo concurrent game!");
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");
            p.sendPluginMessage(Skywars.getInstance(), "BungeeCord", out.toByteArray());
            return;
        }
        if (Skywars.getInstance().getCurrentGame().isStarted()) {
            p.sendMessage("§cGame has already started!");
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");
            p.sendPluginMessage(Skywars.getInstance(), "BungeeCord", out.toByteArray());
            return;
        }
        boolean b = Skywars.getInstance().getCurrentGame().initPlayer(p);
        if (!b) {
            p.sendMessage("§cGame is full!");
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");
            p.sendPluginMessage(Skywars.getInstance(), "BungeeCord", out.toByteArray());
        }
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
