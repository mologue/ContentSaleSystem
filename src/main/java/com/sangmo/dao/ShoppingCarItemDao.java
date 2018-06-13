package com.sangmo.dao;

import com.sangmo.domain.ShoppingCarItem;
import com.sangmo.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//public interface ShoppingCarItemDao {
//
//    @Select("select * from shoppingCar where uerName = #{userName}")
//    @ResultMap("com.sangmo.dao.ShoppingCarMapper.shoppingCarItem")
//    List<ShoppingCarItem> listAllForUser(String userName);
//
//    @Insert("insert into shoppingCar(userName,contentId,amount) values(#{userName}, #{contentId},#{amount}")
//    void addShoppingItem(ShoppingCarItem shoppingCarItem);
//
//    @Delete("delete from shoppingCar where itemId = #{itemId}")
//    void deleteShoppingItem(ShoppingCarItem shoppingCarItem);
//}
