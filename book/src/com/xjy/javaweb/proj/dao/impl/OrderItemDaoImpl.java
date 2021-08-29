package com.xjy.javaweb.proj.dao.impl;

import com.xjy.javaweb.proj.dao.OrderItemDao;
import com.xjy.javaweb.proj.pojo.Order;
import com.xjy.javaweb.proj.pojo.OrderItem;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,
                orderItem.getName(),
                orderItem.getCount(),
                orderItem.getPrice(),
                orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "SELECT `id`, `name`, `count`, `price`, `total_price` totalPrice, `order_id` orderId FROM `t_order_item` WHERE `order_id` = " + orderId;
        return queryForList(OrderItem.class, sql);
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderIdPage(String orderId, int begin, int pageSize) {
        String sql = "SELECT `id`, `name`, `count`, `price`, `total_price` totalPrice, `order_id` orderId FROM `t_order_item` WHERE `order_id` = ? limit ?,?";
        return queryForList(OrderItem.class, sql, orderId, begin, pageSize);
    }

    @Override
    public Integer queryOrderItemsByOrderIdPageCount(String orderId) {
        String sql = "SELECT COUNT(*) FROM `t_order_item` WHERE `order_id` = " + orderId;
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }
}
