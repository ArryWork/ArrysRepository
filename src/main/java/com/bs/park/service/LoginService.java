package com.bs.park.service;

import com.bs.park.pojo.Admin;
import com.bs.park.pojo.User;

/**
 * Created by ��ǿ on 2016-12-21.
 */
public interface LoginService {
    public Admin adminLogin(Admin admin)throws Exception;

    public User  userLogin(User user) throws Exception;
}
