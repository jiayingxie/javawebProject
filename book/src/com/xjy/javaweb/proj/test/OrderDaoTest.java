package com.xjy.javaweb.proj.test;

import com.xjy.javaweb.proj.dao.OrderDao;
import com.xjy.javaweb.proj.dao.impl.OrderDaoImpl;
import com.xjy.javaweb.proj.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
    }

    @Test
    public void queryOrders() {
        for (Order ele : orderDao.queryOrders()) {
            System.out.println(ele);
        }
    }

    @Test
    public void queryPageOrdersCount() {
        System.out.println(orderDao.queryPageOrdersCount());
    }

    @Test
    public void queryPageOrders() {
        for (Order ele : orderDao.queryPageOrders(0,4)) {
            System.out.println(ele);
        }
    }

    @Test
    public void queryOrderByUserId() {
        for (Order ele : orderDao.queryOrderByUserId(1)) {
            System.out.println(ele);
        }
    }

    @Test
    public void queryOrderByUserIdPage() {
        for (Order ele : orderDao.queryPageOrderByUserId(1,0,4)) {
            System.out.println(ele);
        }
    }

    @Test
    public void queryPageOrderCountByUserId() {
        System.out.println(orderDao.queryPageOrderCountByUserId(1));
    }

    @Test
    public void changeOrderStatus() {
        System.out.println(orderDao.changeOrderStatus(1, "1234567891"));
    }
}