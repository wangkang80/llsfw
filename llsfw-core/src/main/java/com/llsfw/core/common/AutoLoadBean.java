/**
 * DesCodec.java
 * Created at 2014-01-04
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.core.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class AutoLoadBean {
    private static Logger logger = LoggerFactory.getLogger(AutoLoadBean.class);

    private static String DATE_FORMAT_1 = "yyyy-MM-dd";

    private static String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";

    private static String DATE_MATCHER_1 = "^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}$";

    private static String DATE_MATCHER_2 = "^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}(\\s([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d)$";

    /**
     * 自动将requestMap的值装入bean中<br />
     * 如果requestMap里的值为空,则对应bean的属性也将被设置为空
     * 
     * @param bean :buan
     * @param requestMap :map
     * @return 更新好的bean
     * @throws NoSuchMethodException
     * @throws SecurityException
     */

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T load(T bean, Map<String, String[]> requestMap) throws NoSuchMethodException, SecurityException {
        // 获得类所有属性
        Field[] fields = bean.getClass().getDeclaredFields();

        // 开始循环属性
        for (Field field : fields) {

            // 获得请求参数名字集合
            Set<String> names = requestMap.keySet();

            // 开始循环请求参数
            for (String name : names) {
                String value = "";

                // 判断请求值属性名是否和需要绑定类的属性名一直
                if (!name.equals(field.getName())) {
                    continue;
                }

                // 防止file类型值提交
                try {
                    value = StringUtils.hasText(requestMap.get(name)[0]) ? requestMap.get(name)[0].trim() : "";
                } catch (Exception e) {
                    logger.debug("自动装入参数AutoLoadBean.load:根据" + name + "获得值失败");
                    continue;
                }

                // 取得对象属性名
                String fieldName = field.getName();

                // 获得参数类型
                Class cl = field.getType();

                // 拼装set方法名
                String setName = getSetMethodName(fieldName);

                // 获取相应的方法(符合 set 后 大写或者小写 的方法名)
                Method setMethod = null;
                try {
                    setMethod = bean.getClass().getMethod(setName, new Class[] { field.getType() });
                } catch (Exception e) {
                    setMethod = bean.getClass().getMethod("set" + fieldName, new Class[] { field.getType() });
                }

                try {

                    // 根据不同的系统对象转换
                    if (!StringUtils.hasText(value)) {
                        // 为空,则设置为空

                        setMethod.invoke(bean, new Object[] { null });

                    } else if (cl == String.class) {

                        // 为String
                        setMethod.invoke(bean, new Object[] { value });

                    } else if (cl == Byte.class || cl == Short.class || cl == Integer.class || cl == Long.class
                            || cl == Float.class || cl == Double.class || cl == Boolean.class || cl == Character.class) {

                        // 为 bytp,short,int,long,float,double,boolean,char
                        Method valueOf = cl.getMethod("valueOf", new Class[] { String.class });
                        Object valueObj = valueOf.invoke(cl, new Object[] { value });
                        setMethod.invoke(bean, new Object[] { valueObj });

                    } else if (cl == Date.class || cl == java.sql.Date.class) {
                        // 为date
                        String formatChar = null;

                        if (Pattern.compile(DATE_MATCHER_1).matcher(value).find()) {
                            formatChar = DATE_FORMAT_1;
                            // 分别2个默认的格式化规制
                        } else if (Pattern.compile(DATE_MATCHER_2).matcher(value).find()) {
                            formatChar = DATE_FORMAT_2;
                        }

                        if (formatChar != null) {
                            Date date = new SimpleDateFormat(formatChar).parse(value);
                            Object dateObj = cl.newInstance();
                            Method setTime = cl.getMethod("setTime", new Class[] { long.class });
                            setTime.invoke(dateObj, new Object[] { date.getTime() });
                            setMethod.invoke(bean, new Object[] { dateObj });
                        } else {
                            logger.warn("自动装入参数AutoLoadBean.load:自动转换日期格式失败,参数未绑定");
                            continue;
                        }

                    } else {
                        logger.warn("自动装入参数AutoLoadBean.load:未知数据类型,无法调用Set方法");
                        continue;
                    }

                    logger.debug("自动装入参数AutoLoadBean.load:属性" + fieldName + "绑定成功,值为:" + value);
                    break;
                } catch (Exception e) {
                    logger.warn("自动装入参数AutoLoadBean.load:设置值失败,属性类型为:" + field.getType().getSimpleName() + ",值为"
                            + value);
                }
            }
        }
        return bean;
    }

    /**
     * 返回属性的set方法
     * 
     * @param fieldName
     * @return
     */
    private static String getSetMethodName(String fieldName) {
        // 截取对象属性名的第一位,并且转换为大写
        String stringLetter = fieldName.substring(0, 1).toUpperCase();

        // 拼装set方法名
        String setName = "set" + stringLetter + fieldName.substring(1);

        logger.debug("自动装入参数AutoLoadBean.load:调用" + setName + "方法");

        return setName;
    }
}
