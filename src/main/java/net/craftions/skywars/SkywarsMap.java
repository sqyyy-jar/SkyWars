package net.craftions.skywars;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public abstract class SkywarsMap {

    public static final List<SkywarsMap> maps = new ArrayList<>();
    private final int teams, teamSize;
    private final Location respawn;
    private final List<Block> chests;
    private ChestGenerator gen;

    public SkywarsMap(int teams, int teamSize, Location respawn) {
        this(teams, teamSize, respawn, ChestGenerator.NORMAL);
    }

    public SkywarsMap(int teams, int teamSize, Location respawn, ChestGenerator gen) {
        this.teams = teams;
        this.teamSize = teamSize;
        this.respawn = respawn;
        this.chests = new ArrayList<>();
        this.gen = gen;
    }

    public void setGen(ChestGenerator gen) {
        this.gen = gen;
    }

    public void addChest(Block block) {
        chests.add(block);
    }

    public ChestGenerator getGen() {
        return gen;
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

    public List<Block> getChests() {
        return chests;
    }
}
