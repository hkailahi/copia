package com.heneli.copia.db;

import com.heneli.copia.model.Pickup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PickupJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Pickup findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM pickups WHERE PickupId=?",
                new Object[] {id},
                new BeanPropertyRowMapper<>(Pickup.class));
    }

    public List<Pickup> findAll() {
        return jdbcTemplate.query("select * from pickups", new PickupRowMapper());
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from pickups where id=?", new Object[] {
                id
        });
    }
    public int insert(Pickup pickup) {
        return jdbcTemplate.update("insert into pickups (PickupId, FirstName, Categories) " + "values(?,  ?, ?)",
                new Object[] {
                        pickup.getPickupId(), pickup.getFirstName(), pickup.getCategories()
                });
    }
    public int update(Pickup pickup) {
        return jdbcTemplate.update("update pickups " + " set FirstName = ?, Categories = ? " + " where PickupId = ?",
                new Object[] {
                        pickup.getPickupId(), pickup.getFirstName(), pickup.getCategories()
                });
    }

}
