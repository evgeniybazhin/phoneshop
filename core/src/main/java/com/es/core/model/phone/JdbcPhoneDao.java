package com.es.core.model.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcPhoneDao implements PhoneDao{
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ColorDao colorDao;

    private String SQL_SAVE_PHONE = "insert into phones (brand, model, price, displaySizeInches, weightGr, lengthMm, widthMm, heightMm," +
            "announced, deviceType, os, displayResolution, pixelDensity, displayTechnology, backCameraMegapixels, frontCameraMegapixels," +
            "ramGb, internalStorageGb, batteryCapacityMah, talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, description) " +
            "values (:brand, :model, :price, :displaySizeInches, :weightGr, :lengthMm, :widthMm, :heightMm," +
            ":announced, :deviceType, :os, :displayResolution, :pixelDensity, :displayTechnology, :backCameraMegapixels, :frontCameraMegapixels," +
            ":ramGb, :internalStorageGb, :batteryCapacityMah, :talkTimeHours, :standByTimeHours, :bluetooth, :positioning, :imageUrl, :description)";

    public Optional<Phone> get(final Long key) {
        Phone phone = (Phone) jdbcTemplate.queryForObject("select * from phones where id = " + key, new BeanPropertyRowMapper(Phone.class));
        Optional<Phone> optionalPhone = Optional.of(phone);
        if(optionalPhone.isPresent()){
            return Optional.empty();
        }
        phone.setColors(colorDao.get(key));
        return Optional.of(phone);
    }

    public void save(final Phone phone) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(phone);
        namedParameterJdbcTemplate.update(SQL_SAVE_PHONE, namedParameters, keyHolder);
        colorDao.save(phone.getColors(), keyHolder.getKey().longValue());
    }

    public List<Phone> findAll(int offset, int limit) {
        List<Phone> phones = (List<Phone>) jdbcTemplate.query("select * from phones offset " + offset + " limit " + limit, new BeanPropertyRowMapper(Phone.class));
        for(Phone phone : phones){
            phone.setColors(colorDao.get(phone.getId()));
        }
        return phones;
    }
}
