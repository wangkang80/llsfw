/**
 * WgetUtil.java
 * Created at 2015年3月23日
 * Created by wangkang
 * Copyright (C) 2015 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: WgetUtil
 * </p>
 * <p>
 * Description: wget调用工具
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2015年3月23日
 * </p>
 */
public class WgetUtil {

    private static Logger log = LoggerFactory.getLogger(WgetUtil.class);

    /**
     * 使用wget下载文件
     * 
     * @param displayName appName
     * @param category 分类
     * @param download_url 下载地址
     * @return 成功返回文件路径，失败返回null
     */
    public static void downloadFileByWget(String cmd) {
        int retry = 2;
        int res = -1;
        int time = 1;
        while (retry-- > 0) {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            log.info("wget shell: {}", pb.command());
            Process ps = null;
            try {
                ps = pb.start();
            } catch (IOException e) {
                log.error("IOException", e);
            }
            res = doWaitFor(ps, 120 * time++);
            if (res != 0) {
                log.warn("Wget download failed...");
            } else {
                break;
            }
        }
    }

    /**
     * @param ps sub process
     * @param timeout 超时时间，SECONDS
     * @return 正常结束返回0
     */
    private static int doWaitFor(Process ps, int timeout) {
        int res = -1;
        if (ps == null) {
            return res;
        }
        boolean finished = false;
        int time = 0;
        WgetThreadUtil stdoutUtil = new WgetThreadUtil(ps.getInputStream());
        WgetThreadUtil erroroutUtil = new WgetThreadUtil(ps.getErrorStream());
        //启动线程读取缓冲区数据
        stdoutUtil.start();
        erroroutUtil.start();
        while (!finished) {
            time++;
            if (time >= timeout) {
                log.info("Process wget timeout 30s, destroyed!");
                ps.destroy();
                break;
            }
            try {
                res = ps.exitValue();
                finished = true;
            } catch (IllegalThreadStateException e) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {

                }
            }
        }
        return res;
    }
}
