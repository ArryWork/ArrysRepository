package com.bs.park.service.impl;

import com.bs.park.dao.AdminMapper;
import com.bs.park.dao.UserMapper;
import com.bs.park.pojo.Admin;
import com.bs.park.pojo.User;
import com.bs.park.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ��ǿ on 2016-12-21.
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    public AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Admin adminLogin(Admin admin) throws Exception {
        Admin admin1 = adminMapper.queryAdmin(admin);
        return admin1;
    }

    @Override
    public User userLogin(User user) throws Exception {
        return userMapper.getUser(user);
    }
}
