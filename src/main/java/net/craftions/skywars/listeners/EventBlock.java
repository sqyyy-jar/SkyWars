package net.craftions.skywars.listeners;

import net.craftions.skywars.Skywars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventBlock implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (Skywars.getInstance().getCurrentGame() == null) {
            e.setCancelled(true);
            return;
        }
        e.setCancelled(Skywars.getInstance().getCurrentGame().isStarted());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

    }
}
