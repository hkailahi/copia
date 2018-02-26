package com.heneli.copia.db;

import com.heneli.copia.model.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipientJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Recipient findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM recipients WHERE RecipientId=?",
                new Object[] {id},
                new BeanPropertyRowMapper<>(Recipient.class));
    }

    public List<Recipient> findAll() {
        return jdbcTemplate.query("select * from recipients", new RecipientRowMapper());
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from recipients where id=?", id);
    }
    public int insert(Recipient recipient) {
        return jdbcTemplate.update("insert into recipients (RecipientId, FirstName, Restrictions) " + "values(?,  ?, ?)", recipient.getRecipientId(), recipient.getFirstName(), recipient.getRestrictions());
    }
    public int update(Recipient recipient) {
        return jdbcTemplate.update("update recipients " + " set FirstName = ?, Restrictions = ? " + " where RecipientId = ?", recipient.getRecipientId(), recipient.getFirstName(), recipient.getRestrictions());
    }
}
