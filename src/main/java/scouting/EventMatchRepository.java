package scouting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EventMatchRepository extends AbstractEntityDatabase<EventMatch> {
    @Override
    protected String getEntityTableName() {
        return "EventMatches";
    }

    @Override
    protected EventMatch getDBEntity(EventMatch entity) {
        return entity;
    }

    @Override
    protected String insertEntitySQL(EventMatch entity) {
        return null;
    }

    @Override
    protected String updateEntityRowSQL(EventMatch entity) {
        return null;
    }

    @Override
    protected EventMatch entityFromResultSet(ResultSet rs) throws SQLException {
        return new EventMatch(rs.getString("comp_level"), rs.getInt("match_id"), rs.getInt("alliance"),
                rs.getInt("teamId"));
    }

    public List<EventMatch> getTeamsByGameAndCompType(String gameId, String compLevel) {
        return getEntitiesByQuery("select * from EventMatches where match_id=" + gameId + " and comp_level='"
                + compLevel + "' order by alliance;");
    }

    public void importFromTBA(String eventKey) throws Exception {
        Runtime.getRuntime().exec(new String[]{"zsh", "/K", "Start"});
        Runtime.getRuntime().exec("python BlueAllianceScraper.py -e " + eventKey + " -d ");
    }
}
