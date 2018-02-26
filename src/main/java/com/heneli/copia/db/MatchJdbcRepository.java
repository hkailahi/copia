package com.heneli.copia.db;

import com.heneli.copia.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class MatchJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Match findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM matches WHERE MatchId=?",
                new Object[] {id},
                new BeanPropertyRowMapper<>(Match.class));
    }

    public int insert(Match match) {
        switch (match.getDeliveries()) {
            case 1:
                return jdbcTemplate.update("insert into matches (Deliveries, Distance, PickupId, RecipientId1) " + "values(?, ?, ?, ?)",
                        match.getDeliveries(),
                        match.getDistance(),
                        match.getPickupId(),
                        match.getRecipientId1());
            case 2:
                return jdbcTemplate.update("insert into matches (Deliveries, Distance, PickupId, RecipientId1, RecipientId2) " + "values(?, ?, ?, ?, ?)",
                        match.getDeliveries(),
                        match.getDistance(),
                        match.getPickupId(),
                        match.getRecipientId1(),
                        match.getRecipientId2());
            case 3:
                return jdbcTemplate.update("insert into matches (Deliveries, Distance, PickupId, RecipientId1, RecipientId2, RecipientId3) " + "values(?, ?, ?, ?, ?, ?)",
                        match.getDeliveries(),
                        match.getDistance(),
                        match.getPickupId(),
                        match.getRecipientId1(),
                        match.getRecipientId2(),
                        match.getRecipientId3());
            case 4:
                return jdbcTemplate.update("insert into matches (Deliveries, Distance, PickupId, RecipientId1, RecipientId2, RecipientId3, RecipientId4) " + "values(?, ?, ?, ?, ?, ?, ?)",
                        match.getDeliveries(),
                        match.getDistance(),
                        match.getPickupId(),
                        match.getRecipientId1(),
                        match.getRecipientId2(),
                        match.getRecipientId3(),
                        match.getRecipientId4());
            case 5:
                return jdbcTemplate.update("insert into matches (Deliveries, Distance, PickupId, RecipientId1, RecipientId2, RecipientId3, RecipientId4, RecipientId5) " + "values(?, ?, ?, ?, ?, ?, ?, ?)",
                        match.getDeliveries(),
                        match.getDistance(),
                        match.getPickupId(),
                        match.getRecipientId1(),
                        match.getRecipientId2(),
                        match.getRecipientId3(),
                        match.getRecipientId4(),
                        match.getRecipientId5());
            default:
                return jdbcTemplate.update("insert into matches (Deliveries, Distance, PickupId, RecipientId1, RecipientId2, RecipientId3, RecipientId4, RecipientId5, RecipientId6) " + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        match.getDeliveries(),
                        match.getDistance(),
                        match.getPickupId(),
                        match.getRecipientId1(),
                        match.getRecipientId2(),
                        match.getRecipientId3(),
                        match.getRecipientId4(),
                        match.getRecipientId5(),
                        match.getRecipientId6());
        }
    }

    public void exportMatchesToCSV() {
        deleteOldCSVIfExists("matches.csv");

        jdbcTemplate.update("CALL CSVWRITE('matches.csv', 'SELECT * FROM matches');");
    }

    public void exportSortedMatchesToCSV() {
        deleteOldCSVIfExists("sorted_matches.csv");

        jdbcTemplate.update("CALL CSVWRITE('sorted_matches.csv', 'SELECT * FROM matches "
                + "ORDER BY PickupId, Deliveries, Distance')");
    }

    private void deleteOldCSVIfExists(String fileName) {
        String filePath = System.getProperty("user.dir")
                                                + "/"
                                                + fileName;

        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }


}
