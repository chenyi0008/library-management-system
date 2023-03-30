package com.library.service;

import com.library.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    /**
     * 判断账号和密码是否正确
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);

    /**
     * 注册账号
     * @param username
     * @param password
     * @return
     */
    public int registor(String username,String password);

    /**
     * 判断账号是否存在
     * @param username
     * @return
     */
    public User judge(String username);




}
