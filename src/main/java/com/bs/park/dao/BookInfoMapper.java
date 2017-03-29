package com.bs.park.dao;

import com.bs.park.pojo.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoMapper {
    int deleteByPrimaryKey(String fid);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);

    BookInfo getBookInfoByUserId(@Param("userid") String userid);

    int updateBookInfo(@Param("bookInfoId") String bookInfoId);

    List<BookInfo> getAllBookInfoByUserId(@Param("userid") String userid,@Param("month") int month,@Param("year") int year);

    List<BookInfo> getCurrentParkMsg(@Param("areaid") String areaid,@Param("parkid")String parkid, @Param("status") String status);

    int updateBookInfoStatus(@Param("bookInfoId") String bookInfoId,@Param("status")String status);

    List<BookInfo> getEndedOrder(@Param("areaid")String areaid,@Param("userAccount") String userAccount, @Param("parkid") String parkid, @Param("bookInfoId") String bookInfoId);

    List<BookInfo> getUnpaidBookInfo(@Param("userid") String userid);
}