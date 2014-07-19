/**
 * XmlUtils.java
 * Created at 2014年7月19日
 * Created by wangkang
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.ContentListFacade;
import org.dom4j.tree.DefaultAttribute;

/**
 * <p>
 * ClassName: XmlUtils
 * </p>
 * <p>
 * Description: xml工具类
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年7月19日
 * </p>
 */
public class XmlUtils {

    @SuppressWarnings("rawtypes")
    public static Map<String, Object> dom2Map(Document doc) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断需要解析的文档是否为空 
        if (doc == null) {
            return map;
        }
        // 获取根节点 
        Element root = doc.getRootElement();

        // 获取根节点下的子节点迭代器 
        Iterator iterator = root.elementIterator();

        // 循环子节点，开始向map中存值 
        while (iterator.hasNext()) {
            Element e = (Element) iterator.next();
            List list = e.elements();

            // 判断当前节点是否有子节点 
            // 如果存在子节点调用element2Map(Element e)方法，不存在子节点直接存进map中 
            if (list.size() > 0) {
                map.put(e.getName(), element2Map(e));
            } else {
                saveAttribute2Map(map, e);
                map.put(e.getName(), e.getText());
            }
        }
        return map;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Map<String, Object> element2Map(Element e) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> list = e.elements();
        saveAttribute2Map(map, e);
        if (list.size() > 0) {
            for (int i = 0, j = list.size(); i < j; i++) {
                Element iter = (Element) list.get(i);
                List<Object> mapList = new ArrayList<Object>();
                // 存在子节点 
                if (iter.elements().size() > 0) {
                    Map<?, ?> m = element2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList<Object>();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), iter.getText());
                }
            }
        } else {
            saveAttribute2Map(map, e);
            map.put(e.getName(), e.getText());
        }

        return map;
    }

    private static void saveAttribute2Map(Map<String, Object> map, Element e) {
        ContentListFacade attributes = (ContentListFacade) e.attributes();
        if (attributes.size() > 0) {
            HashMap<String, String> attrMap = new HashMap<String, String>();
            map.put("attribute", attrMap);
            DefaultAttribute attrTmp = null;
            for (int i = 0, j = attributes.size(); i < j; i++) {
                attrTmp = (DefaultAttribute) attributes.get(i);
                attrMap.put(attrTmp.getName(), attrTmp.getValue());
            }
        }
    }

    @SuppressWarnings("resource")
    public static String getContent(String filePath, String fileName) {
        File file = new File(filePath, fileName);
        InputStream strm = null;
        StringBuffer content = new StringBuffer();
        try {
            strm = new FileInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(strm, "GBK"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return content.toString();
    }

    public static void main(String[] args) throws DocumentException, UnsupportedEncodingException {
        Document doc = DocumentHelper
                .parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?><ccsc><result>SUCESS</result><detail>abcdef</detail></ccsc>");
        Map<String, Object> map = XmlUtils.dom2Map(doc);
        String result = map.get("ccsc").toString();
        System.out.println(result);
    }

}
