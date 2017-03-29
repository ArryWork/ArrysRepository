package com.bs.park.service;

import com.bs.park.pojo.Parkinglot;

import java.util.List;

/**
 * Created by 林强 on 2016-12-26.
 */
public interface ParkinglotService {
    List<Parkinglot> getParkinglots(String areaid);

    int updateParkNum(String spaceNumber, String parkId);

    int addParkinglot(Parkinglot parkinglot);
}
