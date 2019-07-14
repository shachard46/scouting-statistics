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
import java.util.HashMap;

public class GameScoutingPropsRepository extends AbstractEntityDatabase<GameScoutingProps> {
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
        GameScoutingProps gameScoutingProps = new GameScoutingProps(rs.getString("prop_desc"),
                rs.getString("prop_type"), rs.getInt("prop_child"));
        gameScoutingProps.setPropId(rs.getInt("prop_id"));
        return gameScoutingProps;
    }

    public HashMap<Integer, String> getPropNameInHebrew() throws Exception {
        File fXmlFile = new File("src/ScoutingProps_He.xml");
        HashMap<Integer, String> hebNames = new HashMap<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        dbFactory.setNamespaceAware(false);
        Document doc = dBuilder.parse(fXmlFile);
        NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("data/TeamScoutingProps/*/prop",
                doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            hebNames.put(Integer.parseInt(n.getAttributes().getNamedItem("id").getNodeValue()),
                    n.getAttributes().getNamedItem("name").getNodeValue());
        }
        return hebNames;
    }

    public GameScoutingProps getEntityByPropId(int id) {
        String sql = String.format("select * from %s where prop_id = %s", getEntityTableName(), id);
        return getSingleEntityByQuery(sql);
    }

    public static void main(String[] args) {
        try {
            System.out.println(DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
