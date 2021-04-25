package net.craftions.skywars;

import net.craftions.skywars.listeners.EventBlock;
import net.craftions.skywars.listeners.EventDeath;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skywars extends JavaPlugin {

    private static Skywars instance;
    private Game game;
    private Location spawn;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        this.spawn = this.getConfig().getLocation("lobby.spawn");
        this.getServer().getPluginManager().registerEvents(new EventBlock(), this);
        this.getServer().getPluginManager().registerEvents(new EventDeath(), this);
    }

    @Override
    public void onDisable() {
    }

    public void createGame(int teams, int teamSize) {
        this.game = new Game(teams, teamSize);
    }

    public Game getCurrentGame() {
        return game;
    }

    public Location getSpawn() {
        return this.spawn;
    }

    public static Skywars getInstance() {
        return instance;
    }
}
