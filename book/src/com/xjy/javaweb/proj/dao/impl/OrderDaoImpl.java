package com.xjy.javaweb.proj.dao.impl;

import com.xjy.javaweb.proj.dao.OrderDao;
import com.xjy.javaweb.proj.pojo.Book;
import com.xjy.javaweb.proj.pojo.Order;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
//        这里是不是要限制一下，userId必须是数据库中存在的？？？好像不需要，因为我
//        是通过OrderServlet调用OrderService然后调用现在这个方法的，在OrderServlet
//        那，会确保有userId的存在
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,
                order.getOrderId(),
                order.getCreateTime(),
                order.getPrice(),
                order.getStatus(),
                order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        // 注意，这样拿到的时间数据，最后面有个".0"
        String sql = "SELECT `order_id` orderId, `create_time` createTime, `price`, `status`, `user_id` userId FROM `t_order`";
        return queryForList(Order.class, sql);
    }

    @Override
    public List<Order> queryPageOrders(int begin, int pageSize) {
        String sql = "SELECT `order_id` orderId, `create_time` createTime, `price`, `status`, `user_id` userId FROM `t_order` limit ?,?";
        return queryForList(Order.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryPageOrdersCount() {
        String sql = "SELECT COUNT(*) FROM `t_order`";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        // 注意，这样拿到的时间数据，最后面有个".0"
        String sql = "SELECT `order_id` orderId, `create_time` createTime, `price`, `status`, `user_id` userId FROM `t_order` WHERE `user_id` = " + userId;
        return queryForList(Order.class, sql);
    }

    @Override
    public List<Order> queryPageOrderByUserId(Integer userId, int begin, int pageSize) {
        String sql = "SELECT `order_id` orderId, `create_time` createTime, `price`, `status`, `user_id` userId FROM `t_order` WHERE `user_id` = ? limit ?,?";
        return queryForList(Order.class, sql, userId, begin, pageSize);
    }

    @Override
    public Integer queryPageOrderCountByUserId(Integer userId) {
        String sql = "SELECT COUNT(*) FROM `t_order` WHERE `user_id` = " + userId;
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public int changeOrderStatus(Integer status, String orderId) {
        String sql = "UPDATE `t_order` SET `status` = " + status + " WHERE `order_id` = " + orderId;
        return update(sql);
    }
}
