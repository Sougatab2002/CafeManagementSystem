package com.cts.Service;

import java.util.List;

import com.cts.POJO.Order;

public interface OrderService {

    List<Order> getAllOrders();

    Order addOrder(Integer productId) ;

    void deleteOrder(Integer orderId);
}

