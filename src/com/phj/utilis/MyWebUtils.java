package com.phj.utilis;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName MyWebUtils 自己写的工具类，一般不会调用，利用反射
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/11
 * @Version V1.0
 **/
public class MyWebUtils {


    /**
     * 将页面传来数据封装成bean
     * @param request 页面请求，用来获取页面传来的数据
     * @param t 要封装成的对象
     * @param <T> 泛型，不确定的参数类型
     * @return 封装好的bean
     */
    public static <T> T paramsToBean(HttpServletRequest request, T t) {
        try {
            //获取所有需要封装的属性
            Field[] declaredFields = t.getClass().getDeclaredFields();
            //利用反射循环赋值
            for (Field field : declaredFields) {
                //获取参数类型
                Class<?> type = field.getType();
                //获取参数名
                String name = field.getName();
                //通过参数名获取对应的前端变量
                String value = request.getParameter(name);
                //通过响应的参数名写出setter方法名
                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                //获取setter方法
                Method method = t.getClass().getDeclaredMethod(methodName, type);
                //调用setter方法
                method.invoke(t, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
