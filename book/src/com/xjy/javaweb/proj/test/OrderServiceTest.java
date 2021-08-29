package com.xjy.javaweb.proj.test;

import com.xjy.javaweb.proj.pojo.*;
import com.xjy.javaweb.proj.service.OrderService;
import com.xjy.javaweb.proj.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );
    }

    @Test
    public void showAllOrders() {
        for (Order ele : orderService.showAllOrders()) {
            System.out.println(ele);
        }
    }

    @Test
    public void showAllOrdersPage() {
        System.out.println(orderService.showAllOrdersPage(0,4));
    }

    @Test
    public void showMyOrders() {
        for (Order ele : orderService.showMyOrders(1)) {
            System.out.println(ele);
        }
    }

    @Test
    public void showMyOrdersPage() {
        System.out.println(orderService.showMyOrdersPage(1, 0, 4));
    }

    @Test
    public void showOrderDetails() {
        for (OrderItem ele : orderService.showOrderDetails("1234567891")) {
            System.out.println(ele);
        }
    }

    @Test
    public void deliverOrder() {
        System.out.println(orderService.deliverOrder("1234567891"));
    }
}