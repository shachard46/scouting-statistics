package scouting;

public class GameScoutingProps {
    private int propId;
    private String propDesc;
    private String propType;
    private Integer propChild;

    public GameScoutingProps(String propDesc, String propType, Integer propChild) {
        this.propDesc = propDesc;
        this.propType = propType;
        this.propChild = propChild;
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
}
