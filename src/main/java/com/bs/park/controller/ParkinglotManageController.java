package com.bs.park.controller;

import com.bs.park.pojo.Parkinglot;
import com.bs.park.service.ParkinglotService;
import org.apache.http.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 林强 on 2016-12-26.
 */
@Controller
@RequestMapping("/park")
public class ParkinglotManageController {
    @Resource
    private ParkinglotService parkMsgService;

    @RequestMapping("parkManagePage")
    public String parkManagePage(){
        return "parkManage";
    }

    /**
     * 通过区域ID 获取停车场列表
     * @param areaid
     * @return
     */
    @RequestMapping("showParkinglot")
    @ResponseBody
    public List<Parkinglot> showParkinglot(@Param("areaid") String areaid){
    List<Parkinglot> parkinglotList = parkMsgService.getParkinglots(areaid);
    return parkinglotList;
    }

    @RequestMapping("updateParkNum")
    @ResponseBody
    public Map updateParkNum(@Param("spaceNumber") String spaceNumber, @Param("parkId") String parkId){
        Map result = new HashMap();
        int i = 0;

        try {
            i  = parkMsgService.updateParkNum(spaceNumber,parkId);
        }catch (Exception e){
            result.put("error","系统错误，请联系管理员"+e.getMessage());
            return result;
        }
        if(i==0) {
            result.put("msg", "更新成功");
        }else {
            result.put("msg", "更新失败");
        }
        return result;
    }

    @RequestMapping("addParkinglot")
    @ResponseBody
    public Map addParkinglot(Parkinglot parkinglot){
        Map result = new HashMap();
        try {
            parkMsgService.addParkinglot(parkinglot);
        }catch (Exception e ){
            result.put("error","系统错误，请联系管理员"+e.getMessage());
        }
        result.put("msg","新增成功");
        return  result;
    }
}
