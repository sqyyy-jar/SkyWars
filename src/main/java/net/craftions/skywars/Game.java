package net.craftions.skywars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.*;

public class Game {
    private static final String prefix = "&8[&cSkywars&8] &r";
    private final int teams, teamSize;
    private boolean started;
    private SkywarsMap map;
    private final List<Player> players;
    private final Map<Integer, List<Player>> teamsMap;
    private final List<Player> alive;

    public Game(int teams, int teamSize) {
        this.teams = teams < 2 ? 2 : Math.min(teams, 16);
        this.teamSize = teamSize < 1 ? 1 : Math.min(teamSize, 4);
        this.started = false;
        this.players = new ArrayList<>();
        this.teamsMap = new HashMap<>();
        this.alive = new ArrayList<>();
    }

    public boolean isReady() {
        if (this.map == null) return false;
        return !this.players.isEmpty() && this.players.size() >= teamSize * 2;
    }

    public int getTeams() {
        return teams;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public boolean setMap(SkywarsMap map) {
        if (map.getTeams() == this.teams && map.getTeamSize() == this.teamSize) {
            this.map = map;
            return true;
        }
        return false;
    }

    public boolean initPlayer(Player player) {
        if (this.started) return false;
        if (this.players.size() == this.teamSize * this.teams)
            return false;
        if (this.players.size() > this.teamSize * this.teams) {
            new Thread(() -> {
                while (this.players.size() > this.teamSize * this.teams) {
                    Player p = this.players.get(this.players.size() - 1);
                    p.sendMessage(ChatColor.translateAlternateColorCodes
                            ('&', "&cThe game is already full."));
                    removePlayer(p);
                }
            }).start();
            return false;
        }
        this.players.add(player);
        return true;
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void kill(Player player) {
        this.alive.remove(player);
        player.sendMessage(ChatColor.translateAlternateColorCodes
                ('&', "&7You died."));
        Bukkit.getScheduler().scheduleSyncDelayedTask(Skywars.getInstance(), () -> {
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(this.map.getRespawnLocation());
        });
    }

    public boolean isAlive(Player player) {
        return this.alive.contains(player);
    }

    public boolean start() {
        if (isReady()) {
            started = true;
            Collections.shuffle(this.players);
            int team = 0;
            for (int i = 0; i < this.players.size(); i++) {
                if (i == 0 || teamSize % i == 0) {
                    team++;
                    this.teamsMap.put(team, new ArrayList<>());
                }
                this.teamsMap.get(team).add(this.players.get(i));
                this.players.get(i).sendMessage(ChatColor.translateAlternateColorCodes
                        ('&', prefix + "&7The game has started."));
                this.alive.add(this.players.get(i));
            }
            this.teamsMap.forEach((t, ps) -> {
                for (Player p : ps) {
                    p.setPlayerListName(ChatColor.translateAlternateColorCodes
                            ('&', "&aT" + t + ": " + p.getName()));
                    p.setPlayerListHeader(ChatColor.translateAlternateColorCodes
                            ('&', "&c&lCraftions Skywars&r - &aTeam " + t));
                }
            });
        }
        return started;
    }
}
