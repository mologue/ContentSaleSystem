package com.sangmo.service;

import com.sangmo.dao.ContentDao;
import com.sangmo.dao.OrderDao;
import com.sangmo.domain.Order;
import com.sangmo.domain.ShoppingCarItem;
import com.sangmo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

//@Service
//public class ShoppingCarServiceImpl implements ShoppingCarService {
//    private ShoppingCarItemDao shoppingCarItemDao;
//    private ContentDao contentDao;
//    private OrderDao orderDao;
//
//    @Autowired
//    public void setShoppingCarItemDao(ShoppingCarItemDao shoppingCarItemDao){
//        this.shoppingCarItemDao = shoppingCarItemDao;
//    }
//    @Autowired
//    public void setContentDao(ContentDao contentDao){
//        this.contentDao = contentDao;
//    }
//    @Autowired
//    public void setOrderDao(OrderDao orderDao){
//        this.orderDao = orderDao;
//    }
//
//
//    public List<ShoppingCarItem> listAllItems(String userName) {
//        List<ShoppingCarItem> itemList = shoppingCarItemDao.listAllForUser(userName);
//        return itemList;
//    }
//
//    public void add(ShoppingCarItem shoppingCarItem) {
//        shoppingCarItemDao.addShoppingItem(shoppingCarItem);
//    }
//
//    @Transactional
//    public void buyItem(List<ShoppingCarItem> itemList,String userName) {
//        if(itemList==null || itemList.size()==0){
//            return;
//        }
//        for(ShoppingCarItem shoppingCarItem:itemList){
//            Order order = new Order();
//            order.setContentId(shoppingCarItem.getContentId());
//            order.setDate(DateUtil.date2String(new Date()));
//            order.setPrice(shoppingCarItem.getPrice());
//            order.setUserName(userName);
//            order.setAmount(shoppingCarItem.getAmount());
//            orderDao.insertNewOrder(order);
//            shoppingCarItemDao.deleteShoppingItem(shoppingCarItem);
//        }
//    }
//}
