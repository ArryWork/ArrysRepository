package com.bs.park.service;

import com.bs.park.pojo.Area;
import com.bs.park.pojo.Parkinglot;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 林强 on 2017-02-22.
 * 公共类，用于获取公共资源
 */


public interface PubService {
    public List<Area> queryArea() throws Exception;

    /**
     * 通过经纬度获取当前所处的区域
     * @param longtitude
     * @param latitude
     * @return
     */
    Area getArea(String longtitude, String latitude);

    /**
     * 通过区域id获取区域内的停车场所
     * @param areaid
     * @return
     */
    List<Parkinglot> getParkList(String areaid);

    List<Area> getAreaListByCanton(String cantonid);

}
