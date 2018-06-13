package com.sangmo.dao;

import com.sangmo.domain.Content;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ContentDao {

    @Select("select * from content where price=#{arg0} and title=#{arg1} and picture=#{arg2} and summary=#{arg3} and detail=#{arg4}")
    @Results({
            @Result(id = true,property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "detail",column = "detail"),
            @Result(property = "price",column = "price"),
            @Result(property = "sellerName",column = "sellerName"),
    })
    Content getContent(Double price,String title,String picture,String summary,String detail);

    @Select("select * from content where contentId=#{contentId}")
//    @ResultMap("com.sangmo.dao.ContentMapper.content")
    @Results({
            @Result(id = true,property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "detail",column = "detail"),
            @Result(property = "price",column = "price"),
            @Result(property = "sellerName",column = "sellerName"),
    })
    Content getContentById(int contentId);


    @Select("select * from content")
//    @ResultMap("com.sangmo.dao.ContentMapper.content")
    @Results({
            @Result(id = true,property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "detail",column = "detail"),
            @Result(property = "price",column = "price"),
            @Result(property = "sellerName",column = "sellerName"),
    })
    List<Content> getAllContents();


    @Insert("insert into content (price,title,picture,summary,detail,sellerName) VALUES(#{price},#{title},#{picture},#{summary},#{detail},#{sellerName});")
    @Options(useGeneratedKeys = true, keyProperty = "contentId")
    void addContent(Content content);

    @Update("update content set price=#{price},title=#{title},picture=#{picture},summary=#{summary},detail=#{detail} where contentId=#{contentId}")
    void updateContent(Content content);

    @Delete("delete from content where contentId=#{contentId}")
    void deleteContentById(int id);

}
