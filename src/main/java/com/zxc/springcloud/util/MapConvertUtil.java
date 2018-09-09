package com.zxc.springcloud.util;

import com.zxc.springcloud.pojo.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapConvertUtil {

    public static Object convert(Map map, Class T) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] fields = T.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            Object o = T.newInstance();
            for (Field field : fields) {
                String fieldName = field.getName();
                String setFieldName = getSetMethod(fieldName);
                Method setFieldMethod = o.getClass().getMethod(setFieldName, field.getType());
                setFieldMethod.invoke(o, map.get(fieldName));
            }
            return o;
        }
        return null;
    }

    public static Map convert(Object entity) throws InvocationTargetException, IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            Map map = new HashMap();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getMethodName = getGetMethod(fieldName);
                Method getMethod;
                try {
                    getMethod = entity.getClass().getMethod(getMethodName);
                } catch (NoSuchMethodException e) {
                    continue;
                }
                Class Clazz = field.getType();
                map.put(fieldName, Clazz.cast(getMethod.invoke(entity, null)));
            }
            return map;
        }
        return null;
    }

    public static String getSetMethod(String field) {
        char[] chars = field.toCharArray();
        chars[0] -= 32;
        return "set" + new String(chars);
    }

    public static String getGetMethod(String field) {
        char[] chars = field.toCharArray();
        chars[0] -= 32;
        return "get" + new String(chars);
    }

    public static void main(String[] args) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map map = new HashMap();
        map.put("name", "zxc");
        map.put("age", 3);
        Object user = convert(map, User.class);
        System.out.println(user.toString());
        Map map1 = convert(user);
        System.out.println(map1);
    }

}