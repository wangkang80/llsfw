/**
 * UUID.java
 * Created at 2014-03-18
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

/**
 * <p>
 * ClassName: UUID
 * </p>
 * <p>
 * Description: uuid相关操作
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月18日
 * </p>
 */
public class UUID {
    /**
     * <p>
     * Description: 返回uuid
     * </p>
     * 
     * @param separated 是否有分隔符 true:有(默认),false:无
     * @return uuid(小写)
     */
    public static String getUUID(boolean separated) {
        String id = java.util.UUID.randomUUID().toString();
        if (!separated) {
            id = id.replaceAll("-", "");
        }
        return id;
    }

    public static void main(String[] arge) {
        System.out.println(UUID.getUUID(false));
    }
}
