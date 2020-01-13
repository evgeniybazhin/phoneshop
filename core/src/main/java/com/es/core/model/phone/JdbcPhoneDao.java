package com.es.core.model.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class JdbcPhoneDao implements PhoneDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ColorDao colorDao;

    private static final String SQL_SAVE_PHONE = "insert into phones (brand, model, price, displaySizeInches, weightGr, lengthMm, widthMm, heightMm," +
            "announced, deviceType, os, displayResolution, pixelDensity, displayTechnology, backCameraMegapixels, frontCameraMegapixels," +
            "ramGb, internalStorageGb, batteryCapacityMah, talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, description) " +
            "values (:brand, :model, :price, :displaySizeInches, :weightGr, :lengthMm, :widthMm, :heightMm," +
            ":announced, :deviceType, :os, :displayResolution, :pixelDensity, :displayTechnology, :backCameraMegapixels, :frontCameraMegapixels," +
            ":ramGb, :internalStorageGb, :batteryCapacityMah, :talkTimeHours, :standByTimeHours, :bluetooth, :positioning, :imageUrl, :description)";
    private static final String SQL_GET_PHONE = "select * from phones where id = ";
    private static final String SQL_GET_COUNT = "select count(*) from phones";

    public Phone getById(final Long id) {
        Phone phone;
        try {
            phone = (Phone) jdbcTemplate.queryForObject(SQL_GET_PHONE + id, new BeanPropertyRowMapper(Phone.class));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
        Optional<Phone> optionalPhone = Optional.ofNullable(phone);
        optionalPhone.ifPresent(phone1 -> phone.setColors(colorDao.get(id)));
        return optionalPhone.get();
    }

    public void save(final Phone phone) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(phone);
        namedParameterJdbcTemplate.update(SQL_SAVE_PHONE, namedParameters, keyHolder);
        colorDao.save(phone.getColors(), keyHolder.getKey().longValue());
    }

    public List<Phone> findAll(int offset, int limit, String search, String sortBy) {
        List<Phone> phones = (List<Phone>) jdbcTemplate.query("select * from phones" + searchQuery(search) + sortQuery(sortBy) + " offset " + offset +" limit " + limit, new BeanPropertyRowMapper(Phone.class));
        if(phones != null){
            phones.forEach(phone -> phone.setColors(colorDao.get(phone.getId())));
        }
        return phones;
    }

    private String searchQuery(String search){
        return search == null || search.equals("")  ? "" : " where (UPPER(model) LIKE '%" + search.toUpperCase() + "%' OR UPPER(brand) LIKE '%" + search.toUpperCase() + "%')";
    }

    private String sortQuery(String sortBy){
        String[] array = new String[0];
        if(sortBy != null && !sortBy.equals("")){
            array = sortBy.split("_");
        }
        return sortBy == null || sortBy.equals("") ? "" : " ORDER BY " + array[0].toUpperCase() + " " + array[1].toUpperCase();
    }

    @Override
    public Integer getCount() {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT, Integer.class);
    }
}
