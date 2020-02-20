package com.es.core.model.order;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcOrderDao implements OrderDao {
    private final static String SELECT_ORDER_QUERY = "select orders.id AS orderId, subtotal, deliveryPrice," +
            "totalPrice, firstName, lastName, deliveryAddress, contactPhoneNo," +
            "orderItems.id AS orderItemId, quantity," +
            "phones.id AS phoneId, brand, model, price, displaySizeInches, weightGr, lengthMm," +
            "widthMm, heightMm, announced, deviceType, os, displayResolution, " +
            "pixelDensity, displayTechnology, backCameraMegapixels, " +
            "frontCameraMegapixels, ramGb, internalStorageGb, batteryCapacityMah, " +
            "talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, " +
            "description, colors.id AS colorId, colors.code AS colorCode from orders " +
            "left join orderItems on orders.id = orderItems.orderId " +
            "left join phones on phones.id = orderItems.phoneId " +
            "left join phone2color on phones.id = phone2color.phoneId " +
            "left join colors on colors.id = phone2color.colorId " +
            "where orders.id = ?";
    private final static String INSERT_ORDER_QUERY = "insert into orders (subtotal, deliveryPrice, totalPrice, firstName, lastName, deliveryAddress, contactPhoneNo) values (:subtotal, :deliveryPrice, :totalPrice, :firstName, :lastName, :deliveryAddress, :contactPhoneNo)";
    private final static String INSERT_ORDER_ITEMS_QUERY = "insert into orderItems (orderId, phoneId, quantity) values (:orderId, :phoneId, :quantity)";
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Order> getById(Long id) {
        ArrayList<Order> orders = (ArrayList<Order>) jdbcTemplate.query(SELECT_ORDER_QUERY, new OrderListResultSetExtractor(), id);
        Optional<Order> optionalOrder = Optional.ofNullable(orders.get(0));
        if(optionalOrder.isPresent()){
            return optionalOrder;
        }
        return Optional.empty();
    }

    @Override
    public Long save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = getParameters(order);
        namedParameterJdbcTemplate.update(INSERT_ORDER_QUERY, namedParameters, keyHolder);
        if(!order.getOrderItems().isEmpty()){
            insertOrderItems(order, (Long) keyHolder.getKey());
        }
        return (Long) keyHolder.getKey();
    }

    private SqlParameterSource getParameters(Order order){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("subtotal", order.getSubtotal());
        namedParameters.addValue("deliveryPrice", order.getDeliveryPrice());
        namedParameters.addValue("totalPrice", order.getTotalPrice());
        namedParameters.addValue("firstName", order.getFirstName());
        namedParameters.addValue("lastName", order.getLastName());
        namedParameters.addValue("deliveryAddress", order.getDeliveryAddress());
        namedParameters.addValue("contactPhoneNo", order.getContactPhoneNo());
        return namedParameters;
    }

    private void insertOrderItems(Order order, Long id){
        for(OrderItem orderItem : order.getOrderItems()){
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource namedParameters = getOrderItemsParameters(orderItem, id);
            namedParameterJdbcTemplate.update(INSERT_ORDER_ITEMS_QUERY, namedParameters, keyHolder);
        }
    }

    private SqlParameterSource getOrderItemsParameters(OrderItem orderItem, Long id){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("orderId", id);
        namedParameters.addValue("phoneId", orderItem.getPhone().getId());
        namedParameters.addValue("quantity", orderItem.getQuantity());
        return namedParameters;
    }
}
