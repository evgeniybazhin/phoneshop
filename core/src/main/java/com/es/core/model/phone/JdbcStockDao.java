package com.es.core.model.phone;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JdbcStockDao implements StockDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Stock getCountInStock(Long key) {
        return (Stock) jdbcTemplate.queryForObject("select * from stocks inner join phones on stocks.phoneId = phones.id where phones.Id = " + key, new BeanPropertyRowMapper(Stock.class));
    }
}
