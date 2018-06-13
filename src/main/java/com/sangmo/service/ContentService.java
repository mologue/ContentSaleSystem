package com.sangmo.service;

import com.sangmo.domain.Content;
import com.sangmo.domain.User;

import java.util.List;

public interface ContentService {
    Content getContent(String title,String picture,String summary,String detail,Double price);

    Content getContentById(int id);

    void inserContent(Content content);

    void updateContent(Content content);

    void deleteContentById(int contentId);

    List<Content> getAllContents();

    List<Content> getAllItemsForBuyer(User user);

    List<Content> getAllUnboughtItemsForBuyer(User user);

    List<Content> getAllItemsForSeller(User user);

}
