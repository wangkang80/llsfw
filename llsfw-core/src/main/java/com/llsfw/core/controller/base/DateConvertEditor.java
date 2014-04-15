/**
 * DateConvertEditor.java
 * Created at 2013-11-29
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.controller.base;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.util.StringUtils;

/**
 * <p>
 * ClassName: DateConvertEditor
 * </p>
 * <p>
 * Description: 转换Date类型的请求数据
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年11月29日
 * </p>
 */
public class DateConvertEditor extends PropertyEditorSupport {

    /**
     * <p>
     * Field DATA_FORMAT_10: 10位长度的日期
     * </p>
     */
    private static final int DATA_FORMAT_10 = 10;

    /**
     * <p>
     * Field DATA_FORMAT_19: 19位长度的日期
     * </p>
     */
    private static final int DATA_FORMAT_19 = 19;

    /**
     * <p>
     * Field DATA_FORMAT_21: 21位长度的日期
     * </p>
     */
    private static final int DATA_FORMAT_21 = 21;

    /**
     * <p>
     * Field datetimeFormat: yyyy-MM-dd HH:mm:ss 格式化日期
     * </p>
     */
    private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * <p>
     * Field dateFormat: yyyy-MM-dd 格式化日期
     * </p>
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * <p>
     * Description: 日期格式转换
     * </p>
     * 
     * @param text 日期型数据
     * @throws IllegalArgumentException 格式化异常
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            try {
                formatDate(text);
            } catch (ParseException ex) {
                final IllegalArgumentException IAE = new IllegalArgumentException("Could not parse date: "
                        + ex.getMessage());
                IAE.initCause(ex);
                throw IAE;
            }
        } else {
            setValue(null);
        }
    }

    /**
     * <p>
     * Description: 格式化日期
     * </p>
     * 
     * @param text 需要格式化的日期数据
     * @throws ParseException 转换异常
     */
    private void formatDate(String text) throws ParseException {
        if (text.indexOf(":") == -1 && text.length() == DATA_FORMAT_10) {
            setValue(this.dateFormat.parse(text));
        } else if (text.indexOf(":") > 0 && text.length() == DATA_FORMAT_19) {
            setValue(this.datetimeFormat.parse(text));
        } else if (text.indexOf(":") > 0 && text.length() == DATA_FORMAT_21) {
            text = text.replace(".0", "");
            setValue(this.datetimeFormat.parse(text));
        } else {
            throw new IllegalArgumentException("Could not parse date, date format is error ");
        }
    }
}
