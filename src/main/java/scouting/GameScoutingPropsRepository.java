package scouting;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class GameScoutingPropsRepository extends AbstractEntityDatabase<GameScoutingProps> {

    private static final Map<Integer, String> propHebrewNames;
    static {
        try {
            File fXmlFile = new File("src/ScoutingProps_He.xml");
            HashMap<Integer, String> hebNames = new HashMap<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dbFactory.setNamespaceAware(false);
            Document doc = dBuilder.parse(fXmlFile);
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath()
                    .evaluate("data/TeamScoutingProps/*/prop", doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node n = nodeList.item(i);
                hebNames.put(Integer.parseInt(n.getAttributes().getNamedItem("id").getNodeValue()),
                        n.getAttributes().getNamedItem("name").getNodeValue());
            }
            propHebrewNames = hebNames;
        } catch (Exception e) {
            throw new RuntimeException("Could not load propHebrewNames", e);
        }
    }

    @Override
    protected String getEntityTableName() {
        return "TeamScoutingProps";
    }

    @Override
    protected GameScoutingProps getDBEntity(GameScoutingProps entity) {
        return entity;
    }

    @Override
    protected String insertEntitySQL(GameScoutingProps entity) {
        return null;
    }

    @Override
    protected String updateEntityRowSQL(GameScoutingProps entity) {
        return null;
    }

    @Override
    protected GameScoutingProps entityFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("prop_id");
        GameScoutingProps gameScoutingProps = new GameScoutingProps(rs.getString("prop_desc"),
                rs.getString("prop_type"), rs.getInt("prop_child"), propHebrewNames.get(id));
        gameScoutingProps.setPropId(id);
        return gameScoutingProps;
    }

    public static Map<Integer, String> getPropNameInHebrew() throws Exception {
        return propHebrewNames;
    }

    public GameScoutingProps getEntityByPropId(int id) {
        String sql = String.format("select * from %s where prop_id = %s", getEntityTableName(), id);
        return getSingleEntityByQuery(sql);
    }

    public Map<Integer, Map<String, String>> getPropsAsMap() throws Exception {
        Map<Integer, Map<String, String>> propsMap = new HashMap<>();
        for (int propId : getPropNameInHebrew().keySet()) {
            Map<String, String> entry = new HashMap<>();
            entry.put("propType", getEntityByPropId(propId).getPropType());
            entry.put("name", getEntityByPropId(propId).getHebrewName());
            propsMap.put(propId, entry);
        }
        return propsMap;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getPropNameInHebrew());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
