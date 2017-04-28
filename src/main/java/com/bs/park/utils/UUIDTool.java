package com.bs.park.utils;

import java.util.UUID;

public class UUIDTool {

    public UUIDTool() {
    }

    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     *
     * @return
     */
    public static String getUUID() {  
        /*UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // 去掉"-"符号  
        String temp = str.substring(0, 8) + str.substring(9, 13)  
                + str.substring(14, 18) + str.substring(19, 23)  
                + str.substring(24);  
        return temp;*/

        return UUID.randomUUID().toString().replace("-", "");
    }
    public static String generatePassword(){
        return UUID.randomUUID().toString().replace("-","").substring(27,32);
    }
}