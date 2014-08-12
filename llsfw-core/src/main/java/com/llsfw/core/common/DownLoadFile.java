/**
 * DownLoadFile.java
 * Created at 2014-08-12
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
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

        //0：从头开始的全文下载；1：从某字节开始的下载（bytes=27000-）；2：从某字节开始到某字节结束的下载（bytes=27000-39000） 
        int rangeSwitch = 0;

        //记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000） 
        long toLength = 0;

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

        //判断是否包含断点请求
        if (request.getHeader("Range") != null) { // 客户端请求的下载的文件块的开始字节 
            log.info("request.getHeader(\"Range\")=" + request.getHeader("Range"));

            //设置响应类型
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

            //获取range
            rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");

            //判断range类型
            if (rangeBytes.indexOf('-') == rangeBytes.length() - 1) { //如:bytes=969998336- 
                rangeSwitch = 1;
                rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
                pastLength = Long.parseLong(rangeBytes.trim());
                contentLength = fileLength - pastLength + 1;//客户端请求的是 969998336 之后的字节 
            } else { //如:bytes=1275856879-1275877358 
                rangeSwitch = 2;
                String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
                String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
                pastLength = Long.parseLong(temp0.trim()); //bytes=1275856879-1275877358，从第 1275856879 个字节开始下载 
                toLength = Long.parseLong(temp2); //bytes=1275856879-1275877358，到第 1275877358 个字节结束 
                contentLength = toLength - pastLength + 1;//客户端请求的是 1275856879-1275877358 之间的字节 
            }
        } else { //从开始进行下载 
            contentLength = fileLength;//客户端要求全文下载 
        }

        /**
         * 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。 响应的格式是:
         * Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
         * ServletActionContext.getResponse().setHeader("Content-Length", new
         * Long(file.length() - p).toString());
         */
        response.reset(); //告诉客户端允许断点续传多线程连接下载,响应的格式是:Accept-Ranges: bytes 
        response.setHeader("Accept-Ranges", "bytes"); //如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置;响应的格式是:HTTP/1.1 200 OK 
        if (pastLength != 0) {
            //不是从最开始下载, 
            //响应的格式是: 
            //Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小] 
            log.info("----------------------------不是从开始进行下载！服务器即将开始断点续传...");
            switch (rangeSwitch) {
            case 1: {//针对 bytes=27000- 的请求 
                String contentRange = new StringBuffer("bytes ").append(new Long(pastLength).toString()).append("-")
                        .append(new Long(fileLength - 1).toString()).append("/")
                        .append(new Long(fileLength).toString()).toString();
                response.setHeader("Content-Range", contentRange);
                break;
            }
            case 2: {//针对 bytes=27000-39000 的请求 
                String contentRange = rangeBytes + "/" + new Long(fileLength).toString();
                response.setHeader("Content-Range", contentRange);
                break;
            }
            default: {
                break;
            }
            }
        } else {
            //是从开始下载 
            log.info("----------------------------是从开始进行下载！");
        }

        try {

            // 设置相应参数
            response.setContentType(Constants.setContentType(downloadFile.getName()));
            response.setHeader("Accept-Ranges", "bytes");
            //response.setHeader("Content-Length", String.valueOf(downloadFile.length()));
            response.setHeader(
                    "Content-disposition",
                    "attachment; filename="
                            + new String(fileName.getBytes(com.llsfw.core.common.Constants.DEF_CHARACTER_SET_ENCODING),
                                    "ISO8859-1"));

            os = response.getOutputStream();
            out = new BufferedOutputStream(os);
            raf = new RandomAccessFile(downloadFile, "r");
            try {
                switch (rangeSwitch) {
                case 0: {//普通下载，或者从头开始的下载 
                    //同1 
                }
                case 1: {//针对 bytes=27000- 的请求 
                    raf.seek(pastLength);//形如 bytes=969998336- 的客户端请求，跳过 969998336 个字节 
                    int n = 0;
                    while ((n = raf.read(b, 0, Constants.IO_BUFFERED)) != -1) {
                        out.write(b, 0, n);
                    }
                    break;
                }
                case 2: {//针对 bytes=27000-39000 的请求 
                    raf.seek(pastLength - 1);//形如 bytes=1275856879-1275877358 的客户端请求，找到第 1275856879 个字节 
                    int n = 0;
                    long readLength = 0;//记录已读字节数 
                    while (readLength <= contentLength - Constants.IO_BUFFERED) {//大部分字节在这里读取 
                        n = raf.read(b, 0, Constants.IO_BUFFERED);
                        readLength += Constants.IO_BUFFERED;
                        out.write(b, 0, n);
                    }
                    if (readLength <= contentLength) {//余下的不足 Constants.IO_BUFFERED个字节在这里读取 
                        n = raf.read(b, 0, (int) (contentLength - readLength));
                        out.write(b, 0, n);
                    }
                    break;
                }
                default: {
                    break;
                }
                }
                out.flush();
            } catch (IOException ie) {
                /**
                 * 在写数据的时候， 对于 ClientAbortException 之类的异常，
                 * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时， 抛出这个异常，这个是正常的。
                 * 尤其是对于迅雷这种吸血的客户端软件， 明明已经有一个线程在读取 bytes=1275856879-1275877358，
                 * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段， 直到有一个线程读取完毕，迅雷会 KILL
                 * 掉其他正在下载同一字节段的线程， 强行中止字节读出，造成服务器抛 ClientAbortException。
                 * 所以，我们忽略这种异常
                 */
                log.info("下载出错:", ie);
                throw new Exception(ie);
            }
        } catch (Throwable e) {
            log.info("下载出错:", e);
            throw new Exception(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (raf != null) {
                raf.close();
            }
        }
    }
}