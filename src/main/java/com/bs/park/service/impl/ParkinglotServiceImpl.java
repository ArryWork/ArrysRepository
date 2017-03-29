package com.bs.park.service.impl;

import com.bs.park.dao.ParkinglotMapper;
import com.bs.park.pojo.Parkinglot;
import com.bs.park.service.ParkinglotService;
import com.bs.park.utils.UUIDTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by 林强 on 2016-12-26.
 */
@Service
public class ParkinglotServiceImpl implements ParkinglotService {

    @Resource
    ParkinglotMapper parkinglotMapper;

    @Override
    public List<Parkinglot> getParkinglots(String areaid) {
       return parkinglotMapper.getParkinglotsByAreaId(areaid);
    }

    @Override
    public int updateParkNum(String spaceNumber, String parkId) {
        return parkinglotMapper.updateSpaceNumber(spaceNumber,parkId);
    }

    @Override
    public int addParkinglot(Parkinglot parkinglot) {
        parkinglot.setFid(UUIDTool.getUUID());
        int t = parkinglotMapper.addParkinglot(parkinglot);
        return t;
    }
}
