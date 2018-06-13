package com.sangmo.service;

import com.sangmo.domain.ShoppingCarItem;
import com.sangmo.domain.User;

import java.util.List;

public interface ShoppingCarService {
    List<ShoppingCarItem> listAllItems(String userName);
    void add(ShoppingCarItem shoppingCarItem);
    void buyItem(List<ShoppingCarItem> itemList,String userName);
}
