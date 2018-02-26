package com.heneli.copia.db;

import com.heneli.copia.model.Recipient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class RecipientRowMapper implements RowMapper {
    @Override
    public Recipient mapRow(ResultSet rs, int i) throws SQLException {
        Recipient recipient = new Recipient();
        recipient.setRecipientId(rs.getInt("RecipientId"));
        recipient.setFirstName(rs.getString("FirstName"));
        recipient.setLastName(rs.getString("LastName"));
        recipient.setStreet(rs.getString("Street"));
        recipient.setCity(rs.getString("City"));
        recipient.setState(rs.getString("State"));
        recipient.setPostal(rs.getInt("Postal"));
        recipient.setCountry(rs.getString("Country"));
        recipient.setEmail(rs.getString("Email"));
        recipient.setPhone(rs.getString("Phone"));
        recipient.setLatitude(rs.getDouble("Latitude"));
        recipient.setLongitude(rs.getDouble("Longitude"));

        recipient.setRestrictions(rs.getInt("Restrictions"));

        recipient.setSunday(rs.getInt("Sunday"));
        recipient.setMonday(rs.getInt("Monday"));
        recipient.setTuesday(rs.getInt("Tuesday"));
        recipient.setWednesday(rs.getInt("Wednesday"));
        recipient.setThursday(rs.getInt("Thursday"));
        recipient.setFriday(rs.getInt("Friday"));
        recipient.setSaturday(rs.getInt("Saturday"));

        return recipient;
    }
}
