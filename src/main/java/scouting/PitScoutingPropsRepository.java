package scouting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PitScoutingPropsRepository extends AbstractEntityDatabase<PitScoutingProps>{

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
        PitScoutingProps pitScoutingProps = new PitScoutingProps(rs.getString("prop_desc"),
                rs.getString("prop_type"), rs.getInt("prop_child"));
        pitScoutingProps.setPropId(rs.getInt("prop_id"));
        return pitScoutingProps;
    }
}
