package com.es.core.model.phone;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JdbcStockDao implements StockDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Stock getCountInStock(Long key) {
        return jdbcTemplate.queryForObject("select * from stocks where phoneId = " + key, Stock.class);
    }

    @Override
    public void addPhoneInStock(Long key, Integer stock, Integer reserved) {
        jdbcTemplate.update("insert into stocks (phoneId, stock, reserved) values (?, ?, ?)", key, stock, reserved);
    }
}
