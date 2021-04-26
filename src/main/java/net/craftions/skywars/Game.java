package net.craftions.skywars;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.craftions.skywars.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Game {
    public static final String prefix = "&8[&cSkywars&8] &r";
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

    public int getPlayers() {
        return players.size();
    }

    public boolean initPlayer(Player player) {
        if (this.started) return false;
        if (this.players.size() == this.teamSize * this.teams) return false;
        if (this.players.size() > this.teamSize * this.teams) {
            new Thread(() -> {
                while (this.players.size() > this.teamSize * this.teams) {
                    Player p = this.players.get(this.players.size() - 1);
                    p.sendMessage(ChatColor.translateAlternateColorCodes
                            ('&', prefix + "&cThe game is already full."));
                    removePlayer(p);
                }
            }).start();
            return false;
        }
        this.players.add(player);
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes
                ('&', prefix + "&7A player joined. &8[" + players.size() + "/" + teams * teamSize + "]"));
        return true;
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
        this.alive.remove(player);
        if (this.alive.size() == 1) {
            last(this.alive.get(0));
        } else if (this.alive.size() < 1) {
            last(null);
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("lobby");
        player.sendPluginMessage(Skywars.getInstance(), "BungeeCord", out.toByteArray());
    }

    public void disconnect(Player player) {
        this.players.remove(player);
        if (this.started) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes
                    ('&', prefix + "&7A player left."));
            this.alive.remove(player);
            if (this.alive.size() == 1) {
                last(this.alive.get(0));
            } else if (this.alive.size() < 1) {
                last(null);
            }
        } else {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes
                    ('&', prefix + "&7A player left. &8[" + players.size() + "/" + teams * teamSize + "]"));
        }
    }

    public void kill(Player player) {
        this.alive.remove(player);
        player.sendMessage(ChatColor.translateAlternateColorCodes
                ('&', prefix + "&7You died."));
        Bukkit.getScheduler().scheduleSyncDelayedTask(Skywars.getInstance(), () -> {
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(this.map.getRespawnLocation());
            var i = 0;
        });
        if (this.alive.size() == 1) {
            last(player);
        } else if (this.alive.size() < 1) {
            last(null);
        }
    }

    public void last(Player player) {
        if (player != null)
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes
                ('&', prefix + "&7The game has ended. " + player.getName() + " and his team won."));
        Bukkit.getScheduler().scheduleSyncDelayedTask(Skywars.getInstance(), () -> {
            for (Player p : this.players) {
                p.sendMessage("§cServer restarting!");
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("lobby");
                p.sendPluginMessage(Skywars.getInstance(), "BungeeCord", out.toByteArray());
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Skywars.getInstance(), () -> Skywars.getInstance().getServer().spigot().restart(), 20);
        }, 100);
    }

    public boolean isAlive(Player player) {
        return this.alive.contains(player);
    }

    public boolean start() {
        if (isReady() || true) {
            fillChests();
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
                    p.teleport(this.map.getSpawnLocation(t));
                }
            });
        }
        return started;
    }

    private void fillChests() {
        Random r = new Random();
        for (Block chest : this.map.getChests()) {
            BlockState state = chest.getState();
            if (!(state instanceof InventoryHolder)) {
                System.out.println("Chest at " + chest.getLocation() + " is not a Container!");
                continue;
            }
            InventoryHolder container = (InventoryHolder) state;
            Inventory inv = container.getInventory();
            inv.clear();
            for (int i = 0; i < inv.getContents().length; i++) {
                if (r.nextInt(100) <= 28) {
                    inv.setItem(i, randomItem());
                }
            }
        }
    }

    private final Random rc = new Random();

    private ItemStack randomItem() {
        int i = rc.nextInt(this.map.getGen().getItemStacks().length);
        ItemBuilder b = new ItemBuilder(this.map.getGen().getItemStacks()[i].getMaterial(),
                this.map.getGen().getItemStacks()[i].getAmount());
        double i1 = rc.nextDouble();
        if (this.map.getGen().getItemStacks()[i].getEnchs().length >= 1) {
            int i2 = rc.nextInt(this.map.getGen().getItemStacks()[i].getEnchs().length);
            if (i1 < 0.5 && this.map.getGen().getItemStacks()[i].getEnchs().length > 0) {
                b.addEnchant(this.map.getGen().getItemStacks()[i].getEnchs()[i2].getEnch(),
                        this.map.getGen().getItemStacks()[i].getEnchs()[i2].getLvl());
            }
        }
        return b.build();
    }

    public boolean isStarted() {
        return started;
    }

    public SkywarsMap getMap() {
        return map;
    }
}
