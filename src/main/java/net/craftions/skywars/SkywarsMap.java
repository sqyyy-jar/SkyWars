package net.craftions.skywars;

import org.bukkit.Location;

public abstract class SkywarsMap {

    private final int teams, teamSize;
    private final Location respawn;

    public SkywarsMap(int teams, int teamSize, Location respawn) {
        this.teams = teams;
        this.teamSize = teamSize;
        this.respawn = respawn;
    }

    public final int getTeams() {
        return this.teams;
    }

    public final int getTeamSize() {
        return this.teamSize;
    }

    public final Location getRespawnLocation() {
        return this.respawn;
    }

    public abstract Location getSpawnLocation(int team);
}
