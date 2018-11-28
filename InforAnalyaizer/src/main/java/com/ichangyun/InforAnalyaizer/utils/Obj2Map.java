package com.ichangyun.InforAnalyaizer.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将对象转为map的工具类
 * @author Administrator
 *
 */

public class Obj2Map {
	public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        List<Field> fs = new ArrayList<>();
        Class clazz = obj.getClass();
        while (null!=clazz) {
        	Field[] fields = clazz.getDeclaredFields();
        	fs.addAll(Arrays.asList(fields));
        	clazz=clazz.getSuperclass();
        }
        try {
            for (Field field : fs) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
