/**
 * WgetThreadUtil.java
 * Created at 2015年3月23日
 * Created by wangkang
 * Copyright (C) 2015 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: WgetThreadUtil
 * </p>
 * <p>
 * Description: wget线程
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2015年3月23日
 * </p>
 */
class WgetThreadUtil implements Runnable {

    private static Logger log = LoggerFactory.getLogger(WgetThreadUtil.class);

    // 设置读取的字符编码
    private String character = "UTF-8";
    private InputStream inputStream;

    public WgetThreadUtil(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.setDaemon(true);//将其设置为守护线程
        thread.start();
    }

    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, character));
            String line = null;
            while ((line = br.readLine()) != null) {
                log.info(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //释放资源
                inputStream.close();
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
