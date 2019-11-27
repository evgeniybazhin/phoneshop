package com.es.core.model.phone;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Component
public class JdbcColorDao implements ColorDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SAVE_COLOR = "insert into colors (code) values (?)";

    @Override
    public Set<Color> get(Long key) {
        return new HashSet<Color>((Collection<? extends Color>) jdbcTemplate.query("select * from colors inner join phone2color on phone2color.colorId = colors.id where phone2color.phoneId = " + key,
                                  new BeanPropertyRowMapper(Color.class)));
    }

    @Override
    public void save(Color color) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_SAVE_COLOR);
                ps.setString(1, color.getCode());
                return ps;
            }
        },keyHolder);
    }

    @Override
    public void save(Set<Color> colors, Long key) {
        for(Color color : colors){
            save(color);
            jdbcTemplate.update("insert into phone2color (phoneId, colorId) values (?, ?)", key, color.getId());
        }
    }

    @Override
    public List<Color> findAll() {
        return jdbcTemplate.query("select * from colors", new BeanPropertyRowMapper(Color.class));
    }
}
