package com.ouyang.config.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 版权所有 © 2017
 * @date 2017年10月16日 下午7:45:42
 * @Description TODO
 * 处理全站编码过滤器
 */
public class EncodingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // 转化ServletRequest为HttpRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        filterChain.doFilter(new MyRequest(request), response);
    }

    /**
     * 包装设计模式对request进行处理
     */
    // request存在一个默认包装类（简化包装模式）
	static class MyRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;

        public MyRequest(HttpServletRequest request) {
            // 用super传入一个被增强对象
            super(request);
            this.request = request;
        }

        // 覆盖实现方法
        @Override
        public String getParameter(String name) {
            String value = this.request.getParameter(name);
            if (!request.getMethod().equalsIgnoreCase("get")) {
                return value;
            }
            if (value == null) {
                return null;
            }
            try {
                return new String(value.getBytes(StandardCharsets.UTF_8), request.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
