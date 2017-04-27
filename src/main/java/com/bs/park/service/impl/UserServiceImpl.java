package com.bs.park.service.impl;

import com.bs.park.dao.BookInfoMapper;
import com.bs.park.dao.ParkingSpaceMapper;
import com.bs.park.dao.ParkinglotMapper;
import com.bs.park.dao.UserMapper;
import com.bs.park.pojo.BookInfo;
import com.bs.park.pojo.ParkingSpace;
import com.bs.park.pojo.User;
import com.bs.park.service.UserService;
import com.bs.park.utils.UUIDTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 林强 on 2016-12-23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private ParkinglotMapper parkinglotMapper;
    @Override
    public List<User> qryUser() throws Exception {
        List<User> carUsers = userMapper.queryUser();
        return carUsers;
    }

    @Override
    public int EditUser(User carUser) throws Exception {
        userMapper.update(carUser);
        return 0;
    }

    @Override
    public User login(User user) throws Exception {
        User u = userMapper.getUser(user);
        if(u.getAccount()!=null&&u.getAccount()!="")
            return user;
        else
            return null;
    }

    @Override
    public BookInfo parkStart(String userid,String parkid,double scale) throws Exception {
        BookInfo bookInfo = new BookInfo();
        ParkingSpace parkingSpace=null;
        User user = userMapper.getUserById(userid);
        if("1".equals(user.getStatus())){
            try {
                parkingSpace= getAvaliableSpace(parkid,"2");
            }catch (Exception e){
                throw new RuntimeException("所选停车场已经无车位可用，请刷新后重试");
            }
            if(parkingSpace == null)
                throw new RuntimeException("所选停车场已经无车位可用，请刷新后重试");
            bookInfo.setUserid(userid);
            bookInfo.setParkingid(parkid);
            bookInfo.setSpaceord(parkingSpace.getSpaceorder());
            bookInfo.setFid(UUIDTool.getUUID());
            bookInfo.setStatus("1");
            Date date = new Date();
            bookInfo.setBooktime(date);
            bookInfo.setParktime(date);
            bookInfo.setScale(scale);
        }
        bookInfoMapper.insertSelective(bookInfo);
        userMapper.setStatus(userid,"0");
        return bookInfo;
    }

    /**
     * 预约车位
     * @param userid
     * @param parkinglotid
     * @param parktime
     */
    @Override
    public BookInfo bookPark(String userid, String parkinglotid, Date parktime) {
        BookInfo bookInfo = new BookInfo();
        ParkingSpace parkingSpace = null;
        try {
            parkingSpace = getAvaliableSpace(parkinglotid,"2");
        } catch (Exception e) {
            throw new RuntimeException("所选停车场已经无车位可用，请刷新后重试");
        }
        bookInfo.setFid(UUIDTool.getUUID());
        bookInfo.setUserid(userid);
        bookInfo.setStatus("2");
        bookInfo.setParkingid(parkinglotid);
        bookInfo.setSpaceord(parkingSpace.getSpaceorder());
        bookInfo.setBooktime(new Date());
        bookInfo.setScale(parkingSpace.getChargeScale());
        bookInfo.setParktime(parktime);
        bookInfoMapper.insertSelective(bookInfo);
        userMapper.setStatus(userid,"0");
        return  bookInfo;
    }


    /**
     * 获取用户已预约车位的信息
     * @param userid
     * @return
     */
    @Override
    public BookInfo getBookingInfo(String userid) {
        return bookInfoMapper.getBookInfoByUserId(userid);
    }

    /**
     * 取消预约的车位
     * @param bookInfoId
     */
    @Override
    public boolean cancleBook(String bookInfoId) {
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookInfoId);
        Date currentTime = new Date();
        if(currentTime.after(bookInfo.getParktime())){
            bookInfo.setStatus("1");
            bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
            return false;
        }else {
            returnParkSpace(bookInfo.getParkingid(),bookInfo.getSpaceord());
            userMapper.setStatus(bookInfo.getUserid(),"1");
            bookInfo.setIspayed("1");
            bookInfo.setStatus("3");
            bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
            return true;
        }
    }

    @Override
    public void recharge(String userid, BigDecimal famt) {
        userMapper.recharge(userid,famt);
    }

    @Override
    public User getUserById(String userid) {
        return userMapper.getUserById(userid);
    }

    @Override
    public List<BookInfo> getUnpaidBoofInfo(String userid) {
        return bookInfoMapper.getUnpaidBookInfo(userid);
    }

    @Override
    public int rePay(String userid, String bookinfoid) {
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookinfoid);
        if(deductCharg(userid,bookInfo.getPayamt())){
            bookInfo.setIspayed("1");
            return bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
        }else
            return 0;
    }


    /**
     * 正在停车中的停车信息
     * @param userid
     * @return
     */
    @Override
    public BookInfo showParkingMsg(String userid) {
        BookInfo bookInfo = bookInfoMapper.getBookInfoByUserId(userid);
        Date currentTime = new Date();
        if(currentTime.after(bookInfo.getParktime())){
            bookInfo.setStatus("1");
            bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
        }
        if("1".equals(bookInfo.getStatus())) {
            Date parktime = bookInfo.getParktime();
            String hasPakedTime = timeSpan(parktime, currentTime);
            bookInfo.setHasParkedTime(hasPakedTime);
        }
        return bookInfo;
    }

    /**
     * 停车结束
     * @param bookInfoId
     * @return
     */
    @Override
    public BookInfo endPark(String bookInfoId) {
        DecimalFormat df = new DecimalFormat("#.00");
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookInfoId);
        Date startTime = bookInfo.getParktime();
        Date endTime = new Date();
        double parktime = (endTime.getTime()-startTime.getTime())/1000.0/60.0/60.0;
        parktime =  (double)Math.round(parktime*100)/100;
        double chargAmt = bookInfo.getScale()*parktime;
        bookInfo.setEndtime(endTime);
        bookInfo.setPayamt(chargAmt);
        bookInfo.setStatus("0");
        //扣款
        boolean flag = deductCharg(bookInfo.getUserid(),bookInfo.getPayamt());
        //车位归还
        returnParkSpace(bookInfo.getParkingid(),bookInfo.getSpaceord());
        if(flag){
            bookInfo.setIspayed("1");
        }else {
            bookInfo.setIspayed("0");
        }
        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
        userMapper.setStatus(bookInfo.getUserid(),"1");
        String parkedTime = timeSpan(bookInfo.getParktime(),bookInfo.getEndtime());
        bookInfo.setHasParkedTime(parkedTime);
        return bookInfo;
    }


    @Override
    public boolean deductCharg(String userid, double payamt) {
        User user = userMapper.getUserById(userid);
        if(user.getBalance()<payamt)
            return false;
        else {
            userMapper.deductCharg(userid,user.getBalance()-payamt);
            return true;
        }
    }

    /**
     * 获取订单信息
     * @param bookInfoId
     * @return
     */
    @Override
    public BookInfo getBookInfo(String bookInfoId) {
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookInfoId);
        Date currentTime = new Date();
        if(currentTime.after(bookInfo.getParktime())&&!"0".equals(bookInfo.getStatus())){
            bookInfo.setStatus("1");
            bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
        }
        if("1".equals(bookInfo.getStatus())) {
            String timespan = timeSpan(bookInfo.getParktime(),bookInfo.getEndtime());
            bookInfo.setHasParkedTime(timespan);
        }
        return bookInfo;
    }

    @Override
    public String getUserStatus(String fid) {
        return userMapper.getUserById(fid).getStatus();
    }

    /**
     * 用户获取所有订单信息
     * @param userid
     * @return
     */
    @Override
    public List<BookInfo> getAllBookInfoById(String userid,int month,int year) {
        List<BookInfo> bookInfos = bookInfoMapper.getAllBookInfoByUserId(userid,month,year);
        return bookInfos;
    }
    /**
     * 判断用户是否可用
     * @param userid
     * @return
     */
    private boolean userAvaliable(String userid){
        User user = userMapper.getUserById(userid);
        boolean flag = false;
        if(user!=null) {
            String status = user.getStatus();
            if("1".equals(status))
                flag=true;
        }
        return flag;
    }

    /**
     * 停车结束后归还车位
     * @param parkingid
     * @param spaceord
     */
    private void returnParkSpace(String parkingid, String spaceord) {
        parkinglotMapper.plusOne(parkingid);
        parkingSpaceMapper.setSpaceStatusByOrd(parkingid,spaceord);
    }

    private String getCurrentTime(){
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private  String timeSpan(Date start,Date end){
        long l = end.getTime() - start.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        StringBuffer hasPakedTime = new StringBuffer();
        if(day!=0){
            hasPakedTime.append( day);
            hasPakedTime.append("天");
        }
        hasPakedTime.append(hour+"小时"+min+"分钟");
        return hasPakedTime.toString();
    }

    /**
     * 获取一个可以停车的位置，并设置车位状态
     * @param parkinglotid
     * @param status 车位状态 0、空余 1、预约 2、已停
     * @return
     */
    @Transactional
    private ParkingSpace getAvaliableSpace(String parkinglotid,String status)throws Exception{
       ParkingSpace parkingSpace = parkingSpaceMapper.getAvaliableSpace(parkinglotid);
       int spaceId = 0;
       if(parkingSpace==null) {
           ParkingSpace nps = new ParkingSpace();
           nps.setParkingid(parkinglotid);
           parkingSpaceMapper.addParkingSpace(nps);
           parkingSpace = parkingSpaceMapper.getSpaceById(nps.getFid());
           if("0".equals(parkingSpace.getSpaceorder())){
               parkingSpaceMapper.deleteSpaceById(nps.getFid());
               throw new RuntimeException("无车位");
           }
       }else {
           spaceId = parkingSpace.getFid();
       }
       parkingSpaceMapper.setSpaceStatus(spaceId,status);
       parkinglotMapper.minusOne(parkinglotid);
       return parkingSpace;
    }
}
