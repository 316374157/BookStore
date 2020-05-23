package com.phj.utilis;

import com.phj.bean.Cart;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName Web 工具类
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/11
 * @Version V1.0
 **/
public class WebUtils {
    /**
     * 将页面传来数据封装成bean
     * @param request 页面请求，用来获取页面传来的数据
     * @param t 要封装成的对象
     * @param <T> 泛型，不确定的参数类型
     * @return 封装好的bean
     */
    public static <T> T paramsToBean(HttpServletRequest request,T t){
        try {
            //BeanUtils工具类，用来封装对象，会自动转型
            //第二个是一个数据的map集合，存放了数据名和值,但是前端传进来的数据在bean中不存在，使用这个方法会报错
            Map<String, String[]> parameterMap = request.getParameterMap();
            BeanUtils.populate(t, parameterMap);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
