package scouting;

public class GameScoutingProps {
    private int propId;
    private String propDesc;
    private String propType;
    private Integer propChild;
    private String hebrewName;

    public GameScoutingProps(String propDesc, String propType, Integer propChild, String hebrewName) {
        this.propDesc = propDesc;
        this.propType = propType;
        this.propChild = propChild;
        this.hebrewName = hebrewName;
    }

    public int getPropId() {
        return propId;
    }

    public void setPropId(int propId) {
        this.propId = propId;
    }

    public String getPropDesc() {
        return propDesc;
    }

    public void setPropDesc(String propDesc) {
        this.propDesc = propDesc;
    }

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }

    public Integer getPropChild() {
        return propChild;
    }

    public void setPropChild(Integer propChild) {
        this.propChild = propChild;
    }

    public String getHebrewName() {
        return hebrewName;
    }

    public void setHebrewName(String hebrewName) {
        this.hebrewName = hebrewName;
    }
}
