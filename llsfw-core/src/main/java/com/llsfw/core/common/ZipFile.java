/**
 * ZipFile.java
 * Created at 2014-01-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: ZipFile
 * </p>
 * <p>
 * Description: 压缩&解压文件
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月4日
 * </p>
 */
public class ZipFile {
    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field byteSize: 字节数组长度
     * </p>
     */
    private String byteSize = "1024";

    /**
     * <p>
     * Field encoding: 编码格式
     * </p>
     */
    private String encoding = "UTF-8";

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public ZipFile() {
    }

    /**
     * <p>
     * Description: 构造方法
     * </p>
     * 
     * @param encoding 编码
     */
    public ZipFile(String encoding) {
        this.encoding = encoding;
    }

    /**
     * <p>
     * Description: 把zip文件解压到指定的文件夹
     * </p>
     * 
     * @param zipFilePath zip文件路径
     * @param saveFileDir 解压后的文件存放路径
     * @throws Exception 异常
     */
    public void decompressZip(String zipFilePath, String saveFileDir) throws Exception {
        File file = null;
        file = new File(zipFilePath);
        if (file.exists()) {
            InputStream is = null;
            ZipArchiveInputStream zais = null;
            try {
                is = new FileInputStream(file);
                zais = new ZipArchiveInputStream(is, this.encoding);
                ArchiveEntry archiveEntry = null;

                //把zip包中的每个文件读取出来
                //然后把文件写到指定的文件夹
                while ((archiveEntry = zais.getNextEntry()) != null) {
                    //获取文件名
                    String entryFileName = null;
                    entryFileName = archiveEntry.getName();
                    //构造解压出来的文件存放路径
                    String entryFilePath = null;
                    entryFilePath = saveFileDir + entryFileName;
                    byte[] content = null;
                    content = new byte[new Integer(this.byteSize)];
                    OutputStream os = null;
                    try {
                        //把解压出来的文件写到指定路径
                        File entryFile = null;
                        entryFile = new File(entryFilePath);
                        entryFile.mkdirs();
                        os = new FileOutputStream(entryFile);
                        int r;
                        while ((r = zais.read(content)) > 0) {
                            os.write(content, 0, r);
                        }
                    } catch (Exception throwable) {
                        this.log.error("解压缩错误:", throwable);
                        throw throwable;
                    } finally {
                        if (os != null) {
                            os.flush();
                            os.close();
                        }
                    }
                }
            } catch (Exception throwable) {
                this.log.error("解压缩错误:", throwable);
                throw throwable;
            } finally {
                try {
                    if (zais != null) {
                        zais.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (Exception throwable) {
                    this.log.error("解压缩错误:", throwable);
                    throw throwable;
                }
            }
        }
    }

    /**
     * <p>
     * Description: 压缩文件
     * </p>
     * 
     * @param files 需要压缩的文件
     * @param zipFilePath zip文件
     * @throws Exception 异常
     */
    public void compressFiles2Zip(File[] files, String zipFilePath) throws Exception {
        if (files != null && files.length > 0) {
            ZipArchiveOutputStream zaos = null;
            try {
                File zipFile = null;
                zipFile = new File(zipFilePath);
                zaos = new ZipArchiveOutputStream(zipFile);

                //将每个文件用ZipArchiveEntry封装
                //再用ZipArchiveOutputStream写到压缩文件中
                for (File file : files) {
                    if (file != null) {
                        ZipArchiveEntry zipArchiveEntry = null;
                        zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                        zaos.putArchiveEntry(zipArchiveEntry);
                        InputStream is = null;
                        try {
                            is = new BufferedInputStream(new FileInputStream(file), Constants.IO_BUFFERED);
                            byte[] buffer = null;
                            buffer = new byte[new Integer(this.byteSize)];
                            int len = -1;
                            while ((len = is.read(buffer)) != -1) {
                                zaos.write(buffer, 0, len);
                            }
                            zaos.closeArchiveEntry();
                        } catch (Exception throwable) {
                            this.log.error("压缩错误:", throwable);
                            throw throwable;
                        } finally {
                            if (is != null) {
                                is.close();
                            }
                        }
                    }
                }
                zaos.finish();
            } catch (Exception throwable) {
                this.log.error("压缩错误:", throwable);
                throw throwable;
            } finally {
                try {
                    if (zaos != null) {
                        zaos.close();
                    }
                } catch (Exception throwable) {
                    this.log.error("关闭失败:", throwable);
                    throw throwable;
                }
            }

        }
    }
}
