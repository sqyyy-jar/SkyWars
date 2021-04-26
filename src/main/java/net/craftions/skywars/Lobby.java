package net.craftions.skywars;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Lobby {
    private int i = 0;
    public Lobby(int teams, int teamSize) {
        Skywars.getInstance().createGame(teams, teamSize);
        Game g = Skywars.getInstance().getCurrentGame();
        Bukkit.getScheduler().runTaskTimer(Skywars.getInstance(), () -> {
            if (Skywars.getInstance().getCurrentGame() != null && Skywars.getInstance().getCurrentGame().isStarted()) return;
            int size = Bukkit.getOnlinePlayers().size();
            if (size >= teams * teamSize / 2 && size >= 4 || size == 1) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes
                        ('&', Game.prefix + "&7Game starts."));
                g.setMap(new SkywarsMap(teams, teamSize, new Location(Bukkit.getWorld("world"), -100, 100, -100)) {
                    @Override
                    public Location getSpawnLocation(int team) {
                        return new Location(Bukkit.getWorld("world"), 100, 100, 100);
                    }
                });
                g.getMap().addChest(new Location(Bukkit.getWorld("world"), 100, 103, 100).getBlock());
                boolean b = g.start();
                if (!b) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage("§cGame was not able to start!");
                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
                        out.writeUTF("Connect");
                        out.writeUTF("lobby");
                        p.sendPluginMessage(Skywars.getInstance(), "BungeeCord", out.toByteArray());
                    }
                }
            } else {
                i++;
                if (i == 15) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes
                            ('&', Game.prefix + "&7Waiting for players."));
                    i = 0;
                }

            }
        }, 20, 20);
    }

    private void startCountdown() {

    }

    private void stopCountdown() {

    }
}
