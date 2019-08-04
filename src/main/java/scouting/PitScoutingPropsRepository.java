package scouting;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class PitScoutingPropsRepository extends AbstractEntityDatabase<PitScoutingProps> {
    private static final Map<Integer, String> propHebrewNames;
    static {
        try {
            File fXmlFile = new File("src/ScoutingProps_He.xml");
            Map<Integer, String> hebNames = new HashMap<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dbFactory.setNamespaceAware(false);
            Document doc = dBuilder.parse(fXmlFile);
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath()
                    .evaluate("data/PitScoutingProps/prop", doc, XPathConstants.NODESET);
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
        return "PitScoutingProps";
    }

    @Override
    protected PitScoutingProps getDBEntity(PitScoutingProps entity) {
        return entity;
    }

    @Override
    protected String insertEntitySQL(PitScoutingProps entity) {
        return null;
    }

    @Override
    protected String updateEntityRowSQL(PitScoutingProps entity) {
        return null;
    }

    @Override
    protected PitScoutingProps entityFromResultSet(ResultSet rs) throws SQLException {
        PitScoutingProps pitScoutingProps = new PitScoutingProps(rs.getString("prop_desc"), rs.getString("prop_type"),
                rs.getInt("prop_child"));
        pitScoutingProps.setPropId(rs.getInt("prop_id"));
        return pitScoutingProps;
    }

    public static Map<Integer, String> getPropsHebrewNames() {
        return propHebrewNames;
    }
}
