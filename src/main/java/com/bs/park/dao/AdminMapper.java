package com.bs.park.dao;

import com.bs.park.pojo.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(String fid);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);


    Admin queryAdmin(Admin admin);
}