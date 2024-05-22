package com.example.pizdasuka.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

interface ToDb{
    // Маппер, превращающий строку из таблицы БД в объект класса Human
    RowMapper<Human> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Human(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("last_name"));
    };

    List<Human> findAll();

    Human findOne(int id);

    void delete(int id);

    void add(Human human);
}

@Component
public class HumanConfToDB implements ToDb{


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HumanConfToDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Human> findAll() {
        return jdbcTemplate.query("SELECT * FROM human", ROW_MAPPER);
    }

    @Override
    public Human findOne(int id) {
        Human human = null;
        try {
            human = jdbcTemplate.queryForObject("SELECT * FROM human WHERE id = ?", new Object[]{id}, ROW_MAPPER);
        } catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException.getMessage());
        }

        return human;
    }


    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM human WHERE id = ?", id);
    }

    @Override
    public void add(Human human) {
        jdbcTemplate.update(
                "INSERT INTO human (name, last_name) VALUES (?,?)",
                human.getName(), human.getLast_name()
        );
    }
}
