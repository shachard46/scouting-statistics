package scouting;

public class EventMatch {
    private String compLevel;
    private int gameId;
    private int alliance;
    private int teamId;

    public EventMatch(String compLevel, int gameId, int alliance, int teamId) {
        this.compLevel = compLevel;
        this.gameId = gameId;
        this.alliance = alliance;
        this.teamId = teamId;
    }

    public String getCompLevel() {
        return compLevel;
    }

    public void setCompLevel(String compLevel) {
        this.compLevel = compLevel;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getAlliance() {
        return alliance;
    }

    public void setAlliance(int alliance) {
        this.alliance = alliance;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
