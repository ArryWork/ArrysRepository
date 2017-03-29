package com.bs.park.dao;

import com.bs.park.pojo.Area;
import com.bs.park.pojo.Parkinglot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 林强 on 2017-02-22.
 */
public interface PubMapper {
    public List<Area> queryArea();

    Area getArea(@Param("longtitude") String longtitude, @Param("latitude") String latitude);

    List<Parkinglot> getParkList(@Param("areaid") String areaid);

    List<Area> getAreaListByCanton(@Param("cantoncode") String cantoncode);
}
