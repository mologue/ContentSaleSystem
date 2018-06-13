package com.sangmo.service;

import com.sangmo.domain.User;

public interface UserService {
    boolean userExists(String userName,String password);
    int getUserType(String userName);
    User getUserByName(String userName);
}
