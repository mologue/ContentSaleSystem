package com.sangmo.service;

import com.sangmo.domain.Order;
import com.sangmo.domain.User;

import java.util.List;

public interface OrderService {
    List<Order> getOrderByContentId(int id);
    List<Order> getAllOrdersForUser(String userName);
    Order getOrderForUserById(int id,String userName);
    void makeNewOrder(Order order);
//    void buyContent(int contentId,int num,String userName);
//    void updateOrder(Order order);
}
