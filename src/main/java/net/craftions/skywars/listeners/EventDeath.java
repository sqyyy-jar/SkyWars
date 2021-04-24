package net.craftions.skywars.listeners;

import net.craftions.skywars.Game;
import net.craftions.skywars.Skywars;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Skywars sw = Skywars.getInstance();
        Game game = sw.getCurrentGame();
        if (game == null) {
            e.getEntity().teleport(sw.getSpawn() == null ? sw.getSpawn() : new Location(e.getEntity().getWorld(), 0, 100, 0));
            e.setDeathMessage(null);
            e.getEntity().setHealth(20);
            return;
        }
    }
}
