package com.sangmo.service;

import com.sangmo.dao.ContentDao;
import com.sangmo.dao.OrderDao;
import com.sangmo.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private ContentDao contentDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao){
        this.orderDao = orderDao;
    }

    @Autowired
    public void setContentDao(ContentDao contentDao){
        this.contentDao = contentDao;
    }


    public List<Order> getOrderByContentId(int id) {
//        return orderDao.getOrderByContentId(id);
        return null;
    }

    public List<Order> getAllOrdersForUser(String userName) {
        List<Order> orders = orderDao.getAllOrderForUser(userName);
        return orders;
    }

    public Order getOrderForUserById(int id, String userName) {
        return orderDao.getOrderForUser(userName,id);
    }

    public void makeNewOrder(Order order) {
        orderDao.insertNewOrder(order);
    }

//    public void buyContent(int contentId,int num,String userName) {
//        Order order = new Order();
//        Content content = contentDao.getContentById(contentId);
//        order.setContentId(contentId);
//        order.setNum(num);
//        order.setUserName(userName);
//        order.setPrice(content.getPrice());
//        order.setTitle(content.getTitle());
//        order.setPicture(content.getPicture());
//        order.setDate(DateUtil.date2String(new Date()));
//        this.makeNewOrder(order);
//    }

//    public void updateOrder(Order order) {
//        orderDao.updateOrder(order);
//    }
}
