package com.sangmo.service;

import com.sangmo.dao.ContentDao;
import com.sangmo.dao.OrderDao;
import com.sangmo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    private ContentDao contentDao;
    private OrderDao orderDao;

    @Autowired
    public void setContentDao(ContentDao contentDao){
        this.contentDao = contentDao;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao){
        this.orderDao =orderDao;
    }


    public Content getContent(String title, String picture, String summary, String detail, Double price) {
        return contentDao.getContent(price,title,picture,summary,detail);
    }

    public Content getContentById(int id) {
        return contentDao.getContentById(id);
    }

    public void inserContent(Content content) {
        contentDao.addContent(content);
    }

    public void updateContent(Content content) {
        contentDao.updateContent(content);
    }

    public void deleteContentById(int id) {
        contentDao.deleteContentById(id);
    }

    public List<Content> getAllContents() {
        return contentDao.getAllContents();
    }

    public List<Content> getAllItemsForBuyer(User user) {
        List<Content> buyContents = new ArrayList<Content>();
        List<Content> contents = contentDao.getAllContents();
        if(contents==null || contents.size()<1){
            return buyContents;
        }
        List<Order> orders = orderDao.getAllOrderForUser(user.getUserName());
        HashMap<Integer,Integer> contentIdOrderIdMap = new HashMap<Integer, Integer>();
//        HashSet<Integer> orderIdSet = new HashSet<Integer>();
        if(orders!=null && orders.size()>0){
            for(Order order:orders){
                contentIdOrderIdMap.put(order.getContentId(),order.getOrderId());
            }
        }
        for(Content content:contents){
            Content buyContent = new Content();
            buyContent.setContentId(content.getContentId());
            buyContent.setPrice(content.getPrice());
            buyContent.setDetail(content.getDetail());
            buyContent.setPicture(content.getPicture());
            buyContent.setTitle(content.getTitle());
            buyContent.setSummary(content.getSummary());
            if(contentIdOrderIdMap.containsKey(content.getContentId())){
                buyContent.setHasBought(true);
                Order order = orderDao.getOrderByOrderId(contentIdOrderIdMap.get(content.getContentId()));
                buyContent.setBuyNum(order.getAmount());
                buyContent.setBuyPrice(order.getPrice());
            }else{
                buyContent.setHasBought(false);
            }
            buyContents.add(buyContent);
        }
        return buyContents;
    }

    public List<Content> getAllUnboughtItemsForBuyer(User user) {
        List<Content> unBoughtContents = new ArrayList<Content>();
        List<Content> contents = contentDao.getAllContents();
        if(contents==null || contents.size()<1){
            return unBoughtContents;
        }
        List<Order> orders = orderDao.getAllOrderForUser(user.getUserName());
        HashSet<Integer> orderIdSet = new HashSet<Integer>();
        if(orders!=null && orders.size()>0){
            for(Order order:orders){
                orderIdSet.add(order.getContentId());
            }
        }
        for(Content content:contents){
            if(orderIdSet.contains(content.getContentId())){
                continue;
            }
            Content buyContent = new Content();
            buyContent.setContentId(content.getContentId());
            buyContent.setPrice(content.getPrice());
            buyContent.setDetail(content.getDetail());
            buyContent.setPicture(content.getPicture());
            buyContent.setTitle(content.getTitle());
            buyContent.setSummary(content.getSummary());
            buyContent.setHasBought(false);
            unBoughtContents.add(buyContent);
        }
        return unBoughtContents;
    }

    public List<Content> getAllItemsForSeller(User user) {
//        List<Content> soldItems = new ArrayList<Content>();
        List<Content> contents = contentDao.getAllContents();
        if(contents==null || contents.size()<1){
            return new ArrayList<Content>();
        }
//        List<Order> orders = orderDao.getAllOrderForUser(user.getUserName());
//        HashSet<Integer> orderIdSet = new HashSet<Integer>();
//        if(orders!=null && orders.size()>0){
//            for(Order order:orders){
//                orderIdSet.add(order.getContentId());
//            }
//        }
        for(Content content: contents){
            if(contentHasSold(content)){
                content.setHasSold(true);
            }else{
                content.setHasSold(false);
            }
        }
        return contents;
    }

    private boolean contentHasSold(Content content){
        if(orderDao.countOrderByContentId(content.getContentId())>0){
            return true;
        }return false;
    }
}
