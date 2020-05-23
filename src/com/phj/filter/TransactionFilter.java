package com.phj.filter;

import com.phj.utilis.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 31637 给所有的service都加上事务特性
 */
@WebFilter(filterName = "TransactionFilter", urlPatterns = "/*")
public class TransactionFilter implements Filter {

    @Override
    public void destroy() {
    }

    /**
     * 请求发出之后先拦截请求，然后响应过来之后拦截响应，参考地铁站的买票上车机制
     * @param req 请求
     * @param resp 响应
     * @param chain filter
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            //放行
            chain.doFilter(req, resp);
            //无异常，提交并关闭
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            //有异常，回滚并关闭
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            //抛出异常让Tomcat服务器处理
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
