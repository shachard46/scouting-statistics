package scouting;

public class PitScoutingProps {
    private int propId;
    private String propDescription;
    private String propType;
    private Integer propChild;

    public PitScoutingProps(String propDescription, String propType, Integer propChild) {
        this.propDescription = propDescription;
        this.propType = propType;
        this.propChild = propChild;
    }

    public int getPropId() {
        return propId;
    }

    public void setPropId(int propId) {
        this.propId = propId;
    }

    public String getPropDescription() {
        return propDescription;
    }

    public void setPropDescription(String propDescription) {
        this.propDescription = propDescription;
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
}
