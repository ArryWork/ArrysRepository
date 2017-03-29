package com.bs.park.service.impl;

import com.bs.park.dao.BookInfoMapper;
import com.bs.park.dao.UserMapper;
import com.bs.park.pojo.BookInfo;
import com.bs.park.pojo.User;
import com.bs.park.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 林强 on 2017-03-20.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    BookInfoMapper bookInfoMapper;
    @Override
    public Map freezeUser(String userid) {
        HashMap result = new HashMap();
        try {
            userMapper.massSetStatus(userid,"3");
        }catch (Exception e){
            e.printStackTrace();
            result.put("error","1");
            result.put("mag",e.getMessage());
        }
        result.put("msg","冻结成功");
        return  result;

    }

    @Override
    public List<User> qryUser() {
       return userMapper.queryUser();
    }

    @Override
    public Map resumeUser(String userid) {
        HashMap result = new HashMap();
        try {
            userMapper.massSetStatus(userid,"1");
        }catch (Exception e){
            e.printStackTrace();
            result.put("error","1");
            result.put("mag",e.getMessage());
            return  result;
        }
        result.put("msg","恢复成功");
        return  result;
    }

    @Override
    public List<BookInfo> getCurrentParkMsg(String areaid,String parkid ,String status) {
        List<BookInfo> parkMsg = bookInfoMapper.getCurrentParkMsg(areaid,parkid,status);
        return parkMsg;
    }

    @Override
    public int checkStatus(String bookInfoId, String status) {
        return bookInfoMapper.updateBookInfoStatus(bookInfoId,status);
    }

    @Override
    public List<BookInfo> getOrder(String areaid ,String userAccount, String parkinglotid, String bookInfoId) {
        return bookInfoMapper.getEndedOrder(areaid,userAccount,parkinglotid,bookInfoId);
    }
}
