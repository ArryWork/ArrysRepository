package com.bs.park.service;

import com.bs.park.pojo.BookInfo;
import com.bs.park.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by  2016-12-23.
 */
@Service
public interface UserService {
    public List<User> qryUser()throws Exception;

    public int EditUser(User User) throws Exception;

    public User login(User user) throws  Exception;
    public BookInfo parkStart(String userid,String parkid,double scale) throws Exception;

    public BookInfo showParkingMsg(@Param("userid") String userid);

    public BookInfo endPark(String bookInfoId);

    boolean deductCharg(String userid, double payamt);

    BookInfo getBookInfo(String bookInfoId);

    String getUserStatus(String fid);

    List<BookInfo> getAllBookInfoById(String userid,int month, int year);

    BookInfo bookPark(String userid, String parkinglotid, Date parktime);

    BookInfo getBookingInfo(String userid);

    boolean cancleBook(String bookInfoId);

    void recharge(String userid, BigDecimal famt);

    User getUserById(String userid);

    List<BookInfo> getUnpaidBoofInfo(String userid);

    int rePay(String userid, String bookinfoid);

    int setUserPassword(String userId, String password);
}
