
package com.bs.park.controller;

import com.bs.park.pojo.Admin;
import com.bs.park.pojo.BookInfo;
import com.bs.park.pojo.PageModel;
import com.bs.park.pojo.User;
import com.bs.park.service.AdminService;
import com.bs.park.service.LoginService;
import com.bs.park.service.UserService;
import com.bs.park.utils.SpringMailSender;
import com.bs.park.utils.UUIDTool;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * 用户控制层
 *
 * @author 林强
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private LoginService loginService;
    private static Logger log = Logger.getLogger("AdminController.class");
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @Autowired
    private SpringMailSender mailSender;
    @Autowired
    SpringMailSender springMailSender;

    /**
     * 用户登录
     * @param session
     * @param admin
     * @return
     * @throws IOException
     */
    @RequestMapping("login.do")
    public ModelAndView login(HttpSession session, Admin admin) throws IOException {
        ModelAndView mv = new ModelAndView();
        try {
            Admin isChecked = loginService.adminLogin(admin);
            if (isChecked != null) {
                mv.setViewName("index");
                session.setAttribute("admin", isChecked);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        }
        return mv;
    }

    @RequestMapping("goHome.do")
    public String goHome(){
        return "index";
    }
    /**
     * 返回主页
     * @return
     */
    @RequestMapping("mainpage")
    public ModelAndView mainpage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mainpage");
        return mv;
    }

    /**
     * 用户管理
     * @return
     */
    @RequestMapping("usermanage")
    public ModelAndView usermanage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usermanage");
        return mv;
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("queryUser")
    @ResponseBody
    public List<User> queryUser() {
        List<User> carUsers = null;
        PageModel page = new PageModel();
        try {
            carUsers = adminService.qryUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carUsers;
    }

    /**
     * 冻结用户
     * @param userid
     * @return
     */
    @RequestMapping("freezeUser")
    @ResponseBody
    public Map freezeUser(String userid) {
        Map result = new HashMap();
        result = adminService.freezeUser(userid);
        return result;
    }

    /**
     * 恢复用户
     * @param userid
     * @return
     */
    @RequestMapping("resumeUser")
    @ResponseBody
    public Map resumeUser(String userid) {
        Map result = new HashMap();
        result = adminService.resumeUser(userid);

        return result;
    }

    /**
     * 展示当前停车场的停车信息
     * @param areaid
     * @param parkid
     * @param status
     * @return
     */
    @RequestMapping("showCurrentParkMsg.do")
    @ResponseBody
    public List<BookInfo> showCurrentParkMsg(@Param("areaid") String areaid, String parkid, String status) {
        List<BookInfo> currentParkMsg = adminService.getCurrentParkMsg(areaid, parkid, status);
        return currentParkMsg;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 去展示界面
     * @return
     */
    @RequestMapping("showCurrentParkMsgPage")
    public String showCurrentParkMsg() {
        return "currentParkMsg";
    }

    /**
     * 修正记录
     * @param bookInfoId
     * @param status 修正为该状态
     * @return
     */
    @RequestMapping("checkStatus")
    @ResponseBody
    public Map checkStatus(String bookInfoId, String status) {
        Map result = new HashMap();
        int i = adminService.checkStatus(bookInfoId, status);
        if (i == 1) {
            result.put("msg", "成功修正为" + ("1".equals(status) ? "在停" : "预定"));
        } else
            result.put("msg", "修改记录失败");
        return result;
    }

    /** 强制用户结束停车
     * @param bookInfoId
     * @param type       停车类型 1、停车结束，但是状态没更新导致用户无法重新停车 2、用户无法主动停车，管理员手动停车
     * @return
     */
    @RequestMapping("endParkByAdmin")
    @ResponseBody
    public Map endParkByAdmin(String bookInfoId, String type) {
        Map result = new HashMap();
        if ("1".equals(type)) {
            adminService.checkStatus(bookInfoId,"0");
            result.put("msg","停车信息更新成功");
        }else if("2".equals(type)){
            userService.endPark(bookInfoId);
            result.put("msg","结束该用户停车成功");
        }
        return result;
    }

    /**
     * 发送欠款通知邮件
     * @param userEmail
     * @param userName
     * @param dept
     * @param balance
     */
    @RequestMapping("sendEmail")
    public void sendEmail(String userEmail,String userName, String dept, BigDecimal balance){
        springMailSender.simpleSend(userEmail,userName,dept,balance);
    }

    /**
     * 跳转去展示订单信息的界面
     * @return
     */
    @RequestMapping("showOrderPage")
    public String  showBookInfo(){
        return "showOrder";
    }

    @RequestMapping("showOrder")
    @ResponseBody
    public List<BookInfo> showOrder(String areaid,String userAccount, String parkinglotid, String bookInfoId){
        return adminService.getOrder(areaid,userAccount,parkinglotid,bookInfoId);
    }

    @RequestMapping("resetUserPassword")
    public Map<String,Object> resetUserPassword(String userId,String mail){
        Map<String ,Object> result = new HashMap<>();
        String password = UUIDTool.generatePassword();
        userService.setUserPassword(userId,password);
        mailSender.sendResetMail(mail,password);
        return result;
    }

}

