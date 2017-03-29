package com.bs.park.dao;

import com.bs.park.pojo.Parkinglot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkinglotMapper {
    int deleteByPrimaryKey(String fid);

    int insert(Parkinglot record);

    int insertSelective(Parkinglot record);

    Parkinglot selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(Parkinglot record);

    int updateByPrimaryKey(Parkinglot record);

    void minusOne(@Param("parkid")String parkid);

    void plusOne(@Param("parkid") String parkingid);

    List<Parkinglot> getParkinglotsByAreaId(@Param("areaid") String areaid);

    int updateSpaceNumber(@Param("spaceNumber") String spaceNumber, @Param("parkId") String parkId);

    int addParkinglot(Parkinglot parkinglot);

}