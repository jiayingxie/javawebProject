package com.xjy.javaweb.proj.dao;

import com.xjy.javaweb.proj.pojo.OrderItem;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public interface OrderItemDao {
    /*
     * @param orderItem
     * @return the influenced rows
     * @Description: save the orderItem to database
     **/
    public int saveOrderItem(OrderItem orderItem);

    /*
     * @param orderId
     * @return a List of the given orderId's all orderItems
     * @Description: to query one orderId's all orderItems
     **/
    List<OrderItem> queryOrderItemsByOrderId(String orderId);

    /*
     * @param orderId
     * @param begin, the first OrderItem Obejct on the page
     * @param pageSize, how many OrderItem Obejct should be display in one page
     * @return
     * @Description: same as queryOrderItemsByOrderId with paging
     **/
    List<OrderItem> queryOrderItemsByOrderIdPage(String orderId, int begin, int pageSize);

    /*
     * @param orderId
     * @return
     * @Description: count how many OrderItems the given orderId has
     **/
    public Integer queryOrderItemsByOrderIdPageCount(String orderId);
}
