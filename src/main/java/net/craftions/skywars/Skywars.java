package net.craftions.skywars;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.craftions.skywars.listeners.EventBlock;
import net.craftions.skywars.listeners.EventDeath;
import net.craftions.skywars.listeners.EventJoinQuit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public final class Skywars extends JavaPlugin {

    private static Skywars instance;
    private Game game;
    private Location spawn;

    @Override
    public void onEnable() {
        this.getServer().getWorld("world").setDifficulty(Difficulty.PEACEFUL);
        this.getServer().getWorld("world").setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        this.getServer().getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        this.getServer().getWorld("world").setTime(6000);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessageListener() {
            @Override
            public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
                System.out.println("BungeeCord -> " + s + "; " + player.getName() + "; " + new String(bytes));
            }
        });
        this.saveDefaultConfig();
        instance = this;
        this.spawn = this.getConfig().getLocation("lobby.spawn");
        if (this.spawn == null) this.spawn = new Location(this.getServer().getWorld("world"), 0, 100, 0);
        this.getServer().getPluginManager().registerEvents(new EventBlock(), this);
        this.getServer().getPluginManager().registerEvents(new EventDeath(), this);
        this.getServer().getPluginManager().registerEvents(new EventJoinQuit(), this);
        Lobby lobby = new Lobby(2, 1);
    }

    @Override
    public void onDisable() {
        for (Player p : this.getServer().getOnlinePlayers()) {
            p.kickPlayer("Server closed.");
        }
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
