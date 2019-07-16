package scouting;

public class PitScouting {
    private int teamId;
    private int propId;
    private String propValue;

    public PitScouting(int teamId, int propId, String propValue) {
        this.teamId = teamId;
        this.propId = propId;
        this.propValue = propValue;
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
}
