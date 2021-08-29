package com.xjy.javaweb.proj.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class Cart {
    /**
     * key是商品编号，
     * value，是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer,CartItem>();

    /*
     * @param cartItem
     * @return
     * @Description: add a new cart item to the cart
     **/
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            items.put(cartItem.getId(), cartItem);
        } else {
            int curCount = item.getCount() + 1;
            item.setCount(curCount);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(curCount)));
        }
    }

    /*
     * @param id
     * @return
     * @Description: delete a cart item from the cart
     **/
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /*
     * @param
     * @return
     * @Description: clear the cart
     **/
    public void clear() {
        items.clear();
    }

    /*
     * @param id
     * @param count
     * @return
     * @Description: change the number of cart item
     **/
    public void updateCount(Integer id, Integer count) {
        if (count < 0) return;
        CartItem item = items.get(id);
        if (item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));
        }
    }

    /*
     * @param
     * @return
     * @Description: Count the number of items in the cart (if an item is added twice,
     *                  then count twice)
     **/
    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }

    /*
     * @param
     * @return
     * @Description: calculate the total price
     **/
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
