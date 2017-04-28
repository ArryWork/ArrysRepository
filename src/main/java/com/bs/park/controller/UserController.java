package com.bs.park.controller;

import com.bs.park.pojo.BookInfo;
import com.bs.park.pojo.User;
import com.bs.park.service.LoginService;
import com.bs.park.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 林强 on 2017-02-25.
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;
    @RequestMapping("/login.do")
    @ResponseBody
    public Map userLogin(HttpSession session,User user){
        Map result  = new HashMap();
        try {
            User u = loginService.userLogin(user);
            if(u!=null) {
                session.setAttribute("user", u);
                result.put("url","user/goHome.do");
            }else {
                result.put("error","账号不存在或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 回主页
     * @param session
     * @return
     */
    @RequestMapping("goHome")
    public  ModelAndView goHome(HttpSession session){
        ModelAndView mv = new ModelAndView();
        User u = (User) session.getAttribute("user");
        String status = userService.getUserStatus((u.getFid()));
        u.setStatus(status);
        session.setAttribute("user",u);
        if("1".equals(status)){
            mv.setViewName("userMain");
        }
        if ("0".equals(status )) {
            mv=this.showParkingMsg(u.getFid());
        }
        if("3".equals(status)){
            System.out.print("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrror");
        }
        return mv;
    }

    /**
     * 开始停车
     * @param userid
     * @param parkid
     * @return
     */
    @RequestMapping("/parkStart.do")
    @ResponseBody
    public Map<String,Object> parkStart(String userid, String parkid,Double scale){
        BookInfo bookInfo = null;
        Map<String,Object> result = new HashMap();
        try {
            bookInfo = userService.parkStart(userid,parkid,scale);
        } catch (Exception e) {
            result.put("flag","0");
            result.put("msg","系统错误\n错误信息："+e.getMessage());
            e.printStackTrace();
            return result;
        }
        result.put("flag","1");
        result.put("bookInfo",bookInfo);
        return result;
    }

    /**
     * 展示正在停车中的信息
     * @param userid
     * @return
     */
    @RequestMapping("showParkingMsg")
    public ModelAndView showParkingMsg(String userid){
        ModelAndView mv = new ModelAndView();
        BookInfo bookInfo = userService.showParkingMsg(userid);
        if("2".equals(bookInfo.getStatus())) {
            mv.setViewName("showBookMsgPage");
        }
        else {
            mv.setViewName("showParkingMsgPage");
        }
        mv.addObject("bookInfo",bookInfo);
        return mv;
    }

    /**
     * 停车结束
     * @param bookInfoId
     * @return
     */
    @RequestMapping("endPark")
    public ModelAndView endPark(String bookInfoId){
        ModelAndView mv = new ModelAndView();
        BookInfo bookInfo = userService.endPark(bookInfoId);
        if("0".equals(bookInfo.getIspayed())){
            mv.setViewName("redirect:showBookInfo.do?bookInfoId="+bookInfoId);
        }else if("1".equals(bookInfo.getIspayed())){
            mv.setViewName("redirect:showBookInfo.do?bookInfoId="+bookInfoId);
        }
        return mv;
    }

    /**
     * 停车结束后展示所有停车信息
     * @param bookInfoId
     * @return
     */
    @RequestMapping("showBookInfo")
    public ModelAndView showBookInfo(String bookInfoId){
        ModelAndView mv = new ModelAndView();
        BookInfo bookInfo = userService.getBookInfo(bookInfoId);
        mv.setViewName("parkInfo");
        mv.addObject(bookInfo);
        return mv;
    }

    /**
     * 预定车位
     * @param userid
     * @param parkinglotid
     * @param parktime
     * @return
     */
    @RequestMapping("bookPark.do")
    public ModelAndView bookPark(String userid,String parkinglotid,long parktime){
        ModelAndView mv = new ModelAndView();
        Date parkDate = new Date(parktime);
        try {
            BookInfo bookInfo = userService.bookPark(userid, parkinglotid, parkDate);
            mv.setViewName("redirect:showBookMsg.do?bookInfoId=" + bookInfo.getFid());
        }catch (Exception e){

        }
        return mv;
    }

    /**
     * 取消预约
     * @param bookInfoId
     * @return
     */
    @RequestMapping("cancleBook")
    @ResponseBody
    public boolean cancleBook(String bookInfoId){
        return userService.cancleBook(bookInfoId);
    }
    /**
     * 获取所有订单信息list
     * @param userid
     * @param month
     * @param year
     * @return
     */
    @RequestMapping("showAllOrder")
    @ResponseBody
    public List<BookInfo> showAllOrder(String userid,int month,int year){
        ModelAndView mv = new ModelAndView();
        List<BookInfo> bookInfoList = userService.getAllBookInfoById(userid,month,year);
        return bookInfoList;
    }

    @RequestMapping("goToShowPage")
    public String goToShowPage(){
        return "showAllParkOrder";
    }

    @RequestMapping("showBookMsg")
    public ModelAndView showBookMsg(String bookInfoId){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showBookMsgPage");
        BookInfo bookInfo = userService.getBookInfo(bookInfoId);
        mv.addObject("bookInfo",bookInfo);
        return mv;
    }

    @RequestMapping("rechargePage")
    public String rechargePage(){
        return "rechargePage";
    }

    @RequestMapping("validatePassword")
    @ResponseBody
    public Map validatePassword(HttpSession session, String userid, String password, BigDecimal famt){
        Map result = new HashMap();
        User user= (User) session.getAttribute("user");
        if(user.getPassword().equals(password)){
            userService.recharge(userid,famt);
            user = userService.getUserById(userid);
            session.setAttribute("user",user);
        }else {
            result.put("msg","密码错误，请重新输入");
        }
        return  result;
    }

    @RequestMapping("unpaidPage")
    public String goToUnpaidPage(){
        return "unpaidPage";
    }
    @RequestMapping("rePayOrderPage")
    public String goTorePayOrderPage(){return "rePayOrder";}

    @RequestMapping("showUnpaidOrder")
    @ResponseBody
    public List<BookInfo> showUnpaidOrder(String userid){
        return userService.getUnpaidBoofInfo(userid);
    }

    @RequestMapping("rePay")
    @ResponseBody
    public Map rePay(String userid,String bookInfoId) {
        Map result = new HashMap();
        int i = 0;
        try {
            i = userService.rePay(userid, bookInfoId);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return result;
        }
        if (i == 0) {
            result.put("msg", "当前余额不足，请充值后再重新支付");
            result.put("flag", "0");
        } else {
            result.put("msg", "支付成功");
            result.put("flag", "1");
        }
        return result;
    }
}
