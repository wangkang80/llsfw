/**
 * DesCodec.java
 * Created at 2014-01-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: DesCodec
 * </p>
 * <p>
 * Description: DES加密解密
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月4日
 * </p>
 */
public class DesCodec {
    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field keyAlgorithm: 算法名称
     * </p>
     */
    private String keyAlgorithm = "DES";

    /**
     * <p>
     * Field cipherAlgorithm: 算法名称/加密模式/填充方式 <br />
     * DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
     * </p>
     */
    private String cipherAlgorithm = "DES/ECB/PKCS5Padding";

    /**
     * <p>
     * Field keySize: 密码长度
     * </p>
     */
    private String keySize = "56";

    /**
     * <p>
     * Field byteSize: 字节数组长度
     * </p>
     */
    private String byteSize = "1024";

    /**
     * <p>
     * Field charSet: 字符集编码
     * </p>
     */
    private String charSet = "UTF-8";

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param charSet 字符集编码
     */
    public DesCodec(String charSet) {
        this.charSet = charSet;
    }

    /**
     * <p>
     * Description: 生成密钥
     * </p>
     * 
     * @return 密钥
     * @throws NoSuchAlgorithmException 异常
     */
    public String initkey() throws NoSuchAlgorithmException {
        //实例化密钥生成器
        KeyGenerator kg = null;
        kg = KeyGenerator.getInstance(this.keyAlgorithm);

        //初始化密钥生成器 
        kg.init(new Integer(this.keySize));

        //生成密钥  
        SecretKey secretKey = null;
        secretKey = kg.generateKey();

        //获取二进制密钥编码形式
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    /**
     * <p>
     * Description: 加密数据
     * </p>
     * 
     * @param data 待加密数据
     * @param key 密钥
     * @return 加密后的数据
     * @throws Exception 异常
     */
    public String encrypt(String data, String key) throws Exception {
        //还原密钥  
        Key k = null;
        k = this.toKey(Base64.decodeBase64(key));

        //实例化Cipher对象，它用于完成实际的加密操作  
        Cipher cipher = null;
        cipher = Cipher.getInstance(this.cipherAlgorithm);

        //初始化Cipher对象，设置为加密模式  
        cipher.init(Cipher.ENCRYPT_MODE, k);

        //执行加密操作。加密后的结果通常都会用Base64编码进行传输  
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes(this.charSet)));
    }

    /**
     * <p>
     * Description: 解密数据
     * </p>
     * 
     * @param data 待解密数据
     * @param key 密钥
     * @return 解密后的数据
     * @throws Exception 异常
     */
    public String decrypt(String data, String key) throws Exception {
        //还原密钥  
        Key k = null;
        k = this.toKey(Base64.decodeBase64(key));

        //实例化Cipher对象，它用于完成实际的加密操作  
        Cipher cipher = null;
        cipher = Cipher.getInstance(this.cipherAlgorithm);

        //初始化Cipher对象，设置为解密模式  
        cipher.init(Cipher.DECRYPT_MODE, k);

        //执行解密操作  
        return new String(cipher.doFinal(Base64.decodeBase64(data)), this.charSet);
    }

    /**
     * <p>
     * Description: 文件加密
     * </p>
     * 
     * @param file 需加密的文件
     * @param destFile 加密后存放的文件
     * @param key 密码
     * @throws Exception 异常
     */
    public void encryptFile(String file, String destFile, String key) throws Exception {
        InputStream is = null;
        OutputStream out = null;
        CipherInputStream cis = null;
        try {
            //还原密钥  
            Key k = null;
            k = this.toKey(Base64.decodeBase64(key));

            //实例化Cipher对象，它用于完成实际的加密操作  
            Cipher cipher = null;
            cipher = Cipher.getInstance(this.cipherAlgorithm);

            //初始化Cipher对象，设置为加密模式  
            cipher.init(Cipher.ENCRYPT_MODE, k);

            //创建输入输出流
            is = new FileInputStream(file);
            out = new FileOutputStream(destFile);
            cis = new CipherInputStream(is, cipher);

            //开始写文件
            byte[] buffer = null;
            buffer = new byte[new Integer(this.byteSize)];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (Exception s) {
            this.log.error("文件加密失败:", s);
            throw s;
        } finally {
            if (cis != null) {
                cis.close();
            }
            if (is != null) {
                is.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * <p>
     * Description: 解密文件
     * </p>
     * 
     * @param file 待解密的文件
     * @param destFile 解密后存放的文件
     * @param key 密码
     * @throws Exception 异常
     */
    public void decryptFile(String file, String destFile, String key) throws Exception {
        InputStream is = null;
        CipherInputStream cis = null;
        OutputStream out = null;
        try {
            //还原密钥  
            Key k = null;
            k = this.toKey(Base64.decodeBase64(key));

            //实例化Cipher对象，它用于完成实际的加密操作  
            Cipher cipher = null;
            cipher = Cipher.getInstance(this.cipherAlgorithm);

            //初始化Cipher对象，设置为解密模式  
            cipher.init(Cipher.DECRYPT_MODE, k);

            //创建输入输出流
            is = new FileInputStream(file);
            out = new FileOutputStream(destFile);
            cis = new CipherInputStream(is, cipher);

            //开始写文件
            byte[] buffer = null;
            buffer = new byte[new Integer(this.byteSize)];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (Exception e) {
            this.log.error("文件解密失败:", e);
            throw e;
        } finally {
            if (cis != null) {
                cis.close();
            }
            if (is != null) {
                is.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 
     */
    /**
     * <p>
     * Description: 转换密钥
     * </p>
     * 
     * @param key 密码
     * @return 秘钥
     * @throws Exception 异常
     */
    private Key toKey(byte[] key) throws Exception {
        //实例化Des密钥  
        DESKeySpec dks = null;
        dks = new DESKeySpec(key);

        //实例化密钥工厂 
        SecretKeyFactory keyFactory = null;
        keyFactory = SecretKeyFactory.getInstance(this.keyAlgorithm);

        //生成密钥 
        SecretKey secretKey = null;
        secretKey = keyFactory.generateSecret(dks);

        return secretKey;
    }

    //    public static void main(String[] arge) throws Exception {
    //        DesCodec d = new DesCodec("UTF-8");
    //
    //        d.log.info("字符串加密&解密");
    //
    //        String k = d.initkey();
    //        d.log.info("key:" + k);
    //
    //        String source = "王康wangkang";
    //        d.log.info("加密前:" + source);
    //
    //        String ens = d.encrypt(source, k);
    //        d.log.info("加密后:" + ens);
    //
    //        String des = d.decrypt(ens, k);
    //        d.log.info("解密后:" + des);
    //
    //        d.log.info("====================");
    //
    //        d.log.info("文件加密&解密");
    //
    //        k = d.initkey();
    //        d.log.info("key:" + k);
    //
    //        //记录MD5
    //        String md51 = DigestUtils.md5Hex(new FileInputStream("F:/jdk-7u45-windows-x64.exe"));
    //        d.log.info("MD51:" + md51);
    //
    //        //加密
    //        d.encryptFile("F:/jdk-7u45-windows-x64.exe", "F:/aaa.exe", k);
    //
    //        //压缩
    //        ZipFile zf = new ZipFile();
    //        zf.compressFiles2Zip(new File[] { new File("F:/aaa.exe") }, "F:/1.zip");
    //
    //        //连接FTP
    //        Ftp f = new Ftp("UTF-8");
    //        f.connect("127.0.0.1", 21, "wangkang", "qqqqqq");
    //
    //        //删除ftp上的文件
    //        f.removeFile("/1.zip");
    //
    //        //上传ftp
    //        f.upload("F:/1.zip", "/1.zip");
    //
    //        //从ftp上下载
    //        new File("F:/d/1.zip").delete();
    //        f.download("/1.zip", "F:/d/1.zip");
    //
    //        //解压
    //        zf.decompressZip("F:/d/1.zip", "F:/d/");
    //
    //        //解密
    //        d.decryptFile("F:/d/aaa.exe", "F:/d/bbb.exe", k);
    //
    //        //记录MD5
    //        String md52 = DigestUtils.md5Hex(new FileInputStream("F:/d/bbb.exe"));
    //        d.log.info("MD52:" + md52);
    //
    //        //MD5比对
    //        d.log.info("MD5验证:" + md51.equals(md52));
    //        d.log.info("文件加密&解密结束");
    //    }
}
