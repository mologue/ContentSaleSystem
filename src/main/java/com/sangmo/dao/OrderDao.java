package com.sangmo.dao;

import com.sangmo.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {
    @Select("select * from `order` where userName=#{userName}")
//    @ResultMap("com.sangmo.dao.OrderMapper.order")
    @Results({
            @Result(id = true,property = "orderId",column = "orderId"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "date",column = "date"),
            @Result(property = "price",column = "price"),
            @Result(property = "amount",column = "amount")
    })
    List<Order> getAllOrderForUser(@Param("userName") String userName);

    @Select("select * from `order` where orderId=#{orderId}")
//    @ResultMap("com.sangmo.dao.OrderMapper.order")
    @Results({
            @Result(id = true,property = "orderId",column = "orderId"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "date",column = "date"),
            @Result(property = "price",column = "price"),
            @Result(property = "amount",column = "amount")
    })
    Order getOrderByOrderId(int orderId);

    @Select("select * from `order` where userName=#{userName} and orderId=#{orderId}")
//    @ResultMap("com.sangmo.dao.OrderMapper.order")
    @Results({
            @Result(id = true,property = "orderId",column = "orderId"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "date",column = "date"),
            @Result(property = "price",column = "price"),
            @Result(property = "amount",column = "amount")
    })
    Order getOrderForUser(@Param("userName") String userName,@Param("orderId") int orderId);

    @Select("select count(*) from `order` where contentId=#{contentId}")
    int countOrderByContentId(int contentId);

    @Insert("insert into `order` (userName,contentId,title,picture,date,price,amount) VALUES (#{userName},#{contentId},#{title},#{picture},#{date},#{price},#{amount});")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void insertNewOrder(Order order);

//    @Update("update order set amount=#{amount},time=#{time} where orderId=#{orderId}")
//    void updateOrder(Order order);
}
