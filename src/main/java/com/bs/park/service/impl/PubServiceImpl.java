package com.bs.park.service.impl;

import com.bs.park.dao.PubMapper;
import com.bs.park.pojo.Area;
import com.bs.park.pojo.Parkinglot;
import com.bs.park.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 林强 on 2017-02-22.
 */
@Service
public class PubServiceImpl implements PubService {
    @Autowired
    private PubMapper pubMapper;

    @Override
    public List<Area> queryArea() throws Exception {
        return null;
    }

    @Override
    public Area getArea(String longtitude, String latitude) {
        Area area = pubMapper.getArea(longtitude, latitude);
        return area;
    }

    @Override
    public List<Parkinglot> getParkList(String areaid) {
        List<Parkinglot> parkinglotList = pubMapper.getParkList(areaid);
        return parkinglotList;
    }

    @Override
    public List<Area> getAreaListByCanton(String cantoncode) {
        List<Area> areaList = pubMapper.getAreaListByCanton(cantoncode);
        return areaList;

    }
}
