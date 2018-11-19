package cn.itcast.util;

import cn.itcast.pojo.ElectricityFees;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class WebUtils {
    public static String generateID() {
        return UUID.randomUUID().toString();
    }

    public static String md5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(message.getBytes());

            BASE64Encoder encoder = new BASE64Encoder();
            return  encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    //暂时没用到
    public static Object checkNull(Object obj) {
        Class<? extends Object> clazz = obj.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 可访问私有变量
            field.setAccessible(true);
            // 获取属性类型
            String type = field.getGenericType().toString();
            // 如果type是类类型，则前面包含"class "，后面跟类名
            //if ("class java.lang.String".equals(type)) {
                // 将属性的首字母大写
                String methodName = field.getName().replaceFirst(field.getName().substring(0, 1),
                        field.getName().substring(0, 1).toUpperCase());
                System.out.println(methodName);
                try {
                    Method methodGet = clazz.getMethod("get" + methodName);
                    // 调用getter方法获取属性值
                    String str = (String) methodGet.invoke(obj);
                    if (StringUtils.isBlank(str)) {
                        // Method methodSet = clazz.getMethod("set" +
                        // methodName, new Class[] { String.class });
                        // methodSet.invoke(o, new Object[] { "" });
                        System.out.println(field.getType()); // class java.lang.String
                        // 如果为null的String类型的属性则重新复制为空字符串
                        field.set(obj, field.getType().getConstructor(field.getType()).newInstance(""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            //}
        }
        return obj;
    }

    public static String word2num(String word){
        if(word==null || "".equals(word))
            return "0";//0代表为空 出错
        else if("十二月".equals(word))
            return "12";
        else if("十一月".equals(word))
            return "11";
        else if("十月".equals(word))
            return "10";
        else if("九月".equals(word))
            return "9";
        else if("八月".equals(word))
            return "8";
        else if("七月".equals(word))
            return "7";
        else if("六月".equals(word))
            return "6";
        else if("五月".equals(word))
            return "5";
        else if("四月".equals(word))
            return "4";
        else if("三月".equals(word))
            return "3";
        else if("二月".equals(word))
            return "2";
        else if("一月".equals(word))
            return "1";
        else
            return "13";//13代表不是任何一月
    }
}
