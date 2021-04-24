package net.craftions.skywars.listeners;

import net.craftions.skywars.Game;
import net.craftions.skywars.Skywars;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Skywars sw = Skywars.getInstance();
        Game game = sw.getCurrentGame();
        if (game == null) {
            p.teleport(sw.getSpawn() == null ? sw.getSpawn() : new Location(p.getWorld(), 0, 100, 0));
            e.setDeathMessage(null);
            p.setHealth(20);
            return;
        }
        if (game.isAlive(p)) {
            game.kill(p);
        }
    }
}
