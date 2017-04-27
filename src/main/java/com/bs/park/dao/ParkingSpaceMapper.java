package com.bs.park.dao;

import com.bs.park.pojo.ParkingSpace;
import org.apache.ibatis.annotations.Param;

public interface ParkingSpaceMapper {
    int insert(ParkingSpace record);

    int insertSelective(ParkingSpace record);

    ParkingSpace getAvaliableSpace(@Param("parkid") String parkid);
    void setSpaceStatus(@Param("spaceid") int spaceid,@Param("status") String status);

    void setSpaceStatusByOrd(@Param("parkingid") String parkingid,@Param("spaceord")String spaceord);

    int addParkingSpace(ParkingSpace parkingSpace);

    ParkingSpace getSpaceById(@Param("spaceid") int spaceId);

    void deleteSpaceById(@Param("spaceId") int fid);
}