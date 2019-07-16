package scouting;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
