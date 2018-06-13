package com.sangmo.dao;

import com.sangmo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select count(*) from user where userName=#{userName} and password=#{password}")
    int getUserNum(@Param("userName") String userName,@Param("password") String password);

    @Select("select userType from user where userName=#{userName}")
    int getUserTypeByName(String userName);

    @Select("select * from user where userName=#{userName}")
    @Results({
//            @Result(id = true,property = "userId",column = "userId"),
            @Result( id=true, property = "userName",column = "userName"),
            @Result(property = "userType",column = "userType")
    })
    User getUserByName(String userName);
}
