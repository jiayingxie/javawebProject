package com.xjy.javaweb.proj.test;

import com.xjy.javaweb.proj.dao.OrderItemDao;
import com.xjy.javaweb.proj.dao.impl.OrderItemDaoImpl;
import com.xjy.javaweb.proj.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100), new BigDecimal(100),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript 从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty 入门", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
    }

    @Test
    public void queryOrderItemByOrderId() {
        List<OrderItem> orderItemList = orderItemDao.queryOrderItemsByOrderId("1234567891");
        for (OrderItem ele : orderItemList) {
            System.out.println(ele);
        }
    }

    @Test
    public void queryOrderItemsByOrderIdPage() {
        for (OrderItem ele : orderItemDao.queryOrderItemsByOrderIdPage("1234567891", 0,2)) {
            System.out.println(ele);
        }
    }

    @Test
    public void queryOrderItemsByOrderIdPageCount() {
        System.out.println(orderItemDao.queryOrderItemsByOrderIdPageCount("1234567891"));
    }
}