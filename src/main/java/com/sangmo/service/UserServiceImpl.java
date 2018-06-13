package com.sangmo.service;

import com.sangmo.dao.UserDao;
import com.sangmo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }


    public boolean userExists(String userName, String password) {
        int userNum = userDao.getUserNum(userName,password);
        return userNum==0?false:true;
    }

    public int getUserType(String userName) {
        return userDao.getUserTypeByName(userName);
    }

    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }
}
