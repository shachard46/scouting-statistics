package scouting;


public class GameScouting {
    private int gameId;
    private int teamId;
    private int propId;
    private String propValue;

    public GameScouting(int gameId, int teamId, int propId, String propValue) {
        this.gameId = gameId;
        this.teamId = teamId;
        this.propId = propId;
        this.propValue = propValue;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getPropId() {
        return propId;
    }

    public void setPropId(int propId) {
        this.propId = propId;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    @Override
    public String toString() {
        return "GameScouting{" +
                "gameId=" + gameId +
                ", teamId=" + teamId +
                ", propId=" + propId +
                ", propValue='" + propValue + '\'' +
                '}';
    }
}