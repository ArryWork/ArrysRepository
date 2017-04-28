package com.bs.park.dao;

import com.bs.park.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface UserMapper {
    int insert(User record);

    List<User> queryUser();

    int insertSelective(User record);

    int update(User carUser);

    User getUser(User user);

    User getUserById(@Param("userid") String userid);

    void setStatus(@Param("userid") String userid, @Param("status") String status);

    void deductCharg(@Param("userid") String userid, @Param("balance") double payamt);

    void recharge(@Param("userid") String userid, @Param("famt") BigDecimal famt);

    void massSetStatus(@Param("userid") String userid, @Param("status") String status);

    int setPassword(@Param("userId") String userId, @Param("password") String password);
}