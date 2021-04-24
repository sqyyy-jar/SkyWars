package net.craftions.skywars;

public class SkywarsMap {

    private final int teams, teamSize;

    public SkywarsMap(int teams, int teamSize) {
        this.teams = teams;
        this.teamSize = teamSize;
    }

    public final int getTeams() {
        return this.teams;
    }

    public final int getTeamSize() {
        return this.teamSize;
    }
}
