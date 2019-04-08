package org.sang.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器
 * 统一编码格式
 * @author 零
 *
 */
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
