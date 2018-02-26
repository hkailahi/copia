package com.heneli.copia.db;

import com.heneli.copia.model.Pickup;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class PickupRowMapper implements RowMapper {

    @Override
    public Pickup mapRow(ResultSet rs, int i) throws SQLException {
        Pickup pickup = new Pickup();
        pickup.setPickupId(rs.getInt("PickupId"));
        pickup.setFirstName(rs.getString("FirstName"));
        pickup.setLastName(rs.getString("LastName"));
        pickup.setStreet(rs.getString("Street"));
        pickup.setCity(rs.getString("City"));
        pickup.setState(rs.getString("State"));
        pickup.setPostal(rs.getInt("Postal"));
        pickup.setCountry(rs.getString("Country"));
        pickup.setEmail(rs.getString("Email"));
        pickup.setPhone(rs.getString("Phone"));
        pickup.setLatitude(rs.getDouble("Latitude"));
        pickup.setLongitude(rs.getDouble("Longitude"));
        pickup.setCategories(rs.getInt("Categories"));

        pickup.setPickupAt(rs.getTimestamp("PickupAt").toLocalDateTime()); // WTF
        pickup.setTimeZoneId(ZoneId.of(rs.getString("TimeZoneId")));

        pickup.setZonedDateTime(ZonedDateTime.of(pickup.getPickupAt(), pickup.getTimeZoneId()));

        return pickup;
    }
}
