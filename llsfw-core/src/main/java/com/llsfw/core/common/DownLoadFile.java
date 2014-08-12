/**
 * DownLoadFile.java
 * Created at 2014-08-12
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: DownLoadFile
 * </p>
 * <p>
 * Description: 文件下载
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年8月12日
 * </p>
 */
public class DownLoadFile {

    private static Logger log = LoggerFactory.getLogger(DownLoadFile.class);

    /**
     * <p>
     * Description: 下载文件
     * </p>
     * 
     * @param request 请求参数
     * @param response 响应参数
     * @param downloadFile 需下载的文件
     * @param fileName 文件名称
     * @throws Exception 异常
     */
    public void download(HttpServletRequest request, HttpServletResponse response, File downloadFile, String fileName)
            throws Exception {

        //记录文件大小 
        long fileLength = downloadFile.length();

        //记录已下载文件大小 
        long pastLength = 0;

        //客户端请求的字节总量 
        long contentLength = 0;

        //记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容 
        String rangeBytes = "";

        //负责读取数据 
        RandomAccessFile raf = null;

        //写出数据 
        OutputStream os = null;

        //缓冲 
        OutputStream out = null;

        //暂存容器
        byte b[] = new byte[Constants.IO_BUFFERED];

        try {

            //判断是否包含断点请求
            if (request.getHeader("Range") != null) {
                log.info("request.getHeader(\"Range\")=" + request.getHeader("Range"));

                ////获取range,客户端请求的是 969998336 之后的字节 ,设置响应类型
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
                rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
                pastLength = Long.parseLong(rangeBytes.trim());
                contentLength = fileLength - pastLength + 1;
            } else { //从开始进行下载 
                contentLength = fileLength;//客户端要求全文下载 
            }

            if (pastLength != 0) {
                //不是从最开始下载, 
                //响应的格式是: Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小] 
                log.info("----------------------------不是从开始进行下载！服务器即将开始断点续传...");
                String contentRange = new StringBuffer("bytes ").append(new Long(pastLength).toString()).append("-")
                        .append(new Long(fileLength - 1).toString()).append("/")
                        .append(new Long(fileLength).toString()).toString();
                response.setHeader("Content-Range", contentRange);
            } else {
                //是从开始下载 
                log.info("----------------------------是从开始进行下载！");
            }

            // 设置相应参数
            response.setHeader("Accept-Ranges", "bytes"); //如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置;响应的格式是:HTTP/1.1 200 OK 
            response.setContentType(Constants.setContentType(downloadFile.getName()));
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", String.valueOf(contentLength));
            response.setHeader(
                    "Content-disposition",
                    "attachment; filename="
                            + new String(fileName.getBytes(com.llsfw.core.common.Constants.DEF_CHARACTER_SET_ENCODING),
                                    "ISO8859-1"));

            os = response.getOutputStream();
            out = new BufferedOutputStream(os);
            raf = new RandomAccessFile(downloadFile, "r");

            raf.seek(pastLength); //形如 bytes=969998336- 的客户端请求，跳过 969998336 个字节 
            int n = 0;
            while ((n = raf.read(b, 0, Constants.IO_BUFFERED)) != -1) {
                out.write(b, 0, n);
            }

        } catch (Throwable e) {
            log.info("下载出错:", e);
            throw new Exception(e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (raf != null) {
                raf.close();
            }
        }
    }
}
