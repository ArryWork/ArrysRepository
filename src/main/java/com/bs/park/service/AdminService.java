package com.bs.park.service;

import com.bs.park.pojo.BookInfo;
import com.bs.park.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 林强 on 2017-03-20.
 */
public interface AdminService {

    public Map freezeUser(String userList);

    List<User> qryUser();

    Map resumeUser(String userid);

    List<BookInfo> getCurrentParkMsg(String areaid,String parkid,String status);

    int checkStatus(String bookInfoId, String status);


    List<BookInfo> getOrder(String areaid,String userAccount, String parkinglotid, String bookInfoId);
}

