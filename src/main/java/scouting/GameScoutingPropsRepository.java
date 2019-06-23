package scouting;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class GameScoutingPropsRepository extends AbstractEntityDatabase<GameScoutingProps>{
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
        GameScoutingProps gameScoutingProps = new GameScoutingProps(rs.getString("prop_desc"), rs.getString("prop_type"), rs.getInt("prop_child"));
        gameScoutingProps.setPropId(rs.getInt("prop_id"));
        return gameScoutingProps;
    }
    public String[] getPropNameInHebrew(int propId) throws Exception{
        File fXmlFile = new File("/Users/mkyong/staff.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("TeamScoutingProps");
        for (int i = 0; i < nodeList.getLength(); i++){
            NodeList nodeList1 = doc.getElementsByTagName(nodeList.item(i).getNodeName());
            for (int h = 0; h < nodeList1.getLength(); h++){
                Node node = nodeList1.item(h);
                Element element = (Element) node;
                if (Integer.parseInt(element.getAttribute("id")) == propId){
                    String[] arr = {nodeList.item(i).getNodeName(), element.getAttribute("name")};
                    return arr;
                }
            }

        }
        return null;
    }
    public GameScoutingProps getEntityByPropId(int id) {
        String sql = String.format("select * from %s where prop_id = %s", getEntityTableName(), id);
        return getSingleEntityByQuery(sql);
    }
}
