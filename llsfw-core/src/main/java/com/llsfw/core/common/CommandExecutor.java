/**
 * CommandExecutor.java
 * Created at 2015年3月13日
 * Created by wangkang
 * Copyright (C) 2015 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: CommandExecutor
 * </p>
 * <p>
 * Description: 执行操作系统命令
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2015年3月13日
 * </p>
 */
public class CommandExecutor {

    private Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

    /**
     * <p>
     * Description: 执行操作系统命令
     * </p>
     * 
     * @param command 命令
     */
    public void runCommand(String command) {
        logger.info(command);
        Process child = null;
        BufferedReader reader = null;
        try {
            Runtime rt = Runtime.getRuntime();
            child = rt.exec(command);

            // 以下代码为控制台输出相关的批出理 
            String line = null;
            reader = new BufferedReader(new InputStreamReader(child.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }

            // 等待刚刚执行的命令的结束
            while (true) {
                if (child.waitFor() == 0) {
                    break;
                }
            }
        } catch (Exception e) {
            child.destroy();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
