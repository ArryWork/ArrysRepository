package com.bs.park.controller;

import com.bs.park.pojo.Area;
import com.bs.park.pojo.Parkinglot;
import com.bs.park.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 林强 on 2017-02-22.
 */
@Controller
@RequestMapping("/pub")
public class PubController {
    @Autowired
    private PubService pubService;

    /**
     * 经纬度获取区域
     * @param longtitude
     * @param latitude
     * @return
     */
    @RequestMapping("getArea.do")
    @ResponseBody
    public Area getArea(String longtitude, String latitude){
        Area area = null;
        area = pubService.getArea(longtitude,latitude);
        return area;
    }

    /**
     * 通过区域id获取区域内所有的停车场
     * @param areaid
     * @return
     */
    @RequestMapping("getParkList.do")
    @ResponseBody
    public List<Parkinglot> getParkList(String areaid){
        List<Parkinglot> parkinglotList = pubService.getParkList(areaid);
        return parkinglotList;
    }

    @RequestMapping("getAreaListByCanton.do")
    @ResponseBody
    public List<Area> getAreaListByCanton(String cantoncode){
        List<Area> areaList = pubService.getAreaListByCanton(cantoncode);
        return areaList;
    }
}
