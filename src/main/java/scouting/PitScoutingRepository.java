package scouting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PitScoutingRepository extends AbstractEntityDatabase<PitScouting> {
    @Override
    protected String getEntityTableName() {
        return "PitScouting";
    }

    @Override
    protected PitScouting getDBEntity(PitScouting entity) {
        return entity;
    }

    @Override
    protected String insertEntitySQL(PitScouting entity) {
        return null;
    }

    @Override
    protected String updateEntityRowSQL(PitScouting entity) {
        return null;
    }

    @Override
    protected PitScouting entityFromResultSet(ResultSet rs) throws SQLException {
        return new PitScouting(rs.getInt("team_id"), rs.getInt("prop_id"), rs.getString("prop_value"));
    }

    public Map<String, String> getPitScoutingByTeam(int teamId) {
        List<PitScouting> entities = getEntitiesByQuery("select * from PitScouting where team_id=" + teamId);
        System.out.println(entities.size());
        Map<String, String> props = new HashMap<>();
        for (PitScouting entity : entities) {
            props.put(PitScoutingPropsRepository.getPropsHebrewNames().get(entity.getPropId()), entity.getPropValue());
        }
        return props;
    }

    public static void main(String[] args) {
        System.out.println(DatabaseManager.get().getPitScoutingRepository().getPitScoutingByTeam(1576));
    }
}
