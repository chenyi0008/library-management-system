package com.library.service.impl;

import com.library.dao.UserDao;
import com.library.domain.Book;
import com.library.domain.PageBean;
import com.library.domain.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao UserDao;

    public User login(String username, String password) {
        return UserDao.login(username,password);
    }

    public int registor(String username, String password) {
        return UserDao.register(username,password);
    }

    public User judge(String username) {
        return UserDao.judge(username);
    }




}
