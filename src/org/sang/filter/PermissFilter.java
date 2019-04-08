package org.sang.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 过滤器
 * 防止未登录去请求其他页面
 * @author 零
 *
 */
public class PermissFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        if ("/login.jsp".equals(servletPath) || "/reg".equals(servletPath) || "/doreg".equals(servletPath) || "/login".equals(servletPath) || servletPath.contains("/images/") || servletPath.contains("/js/") || servletPath.contains("/My97DatePicker/") || servletPath.contains("/styles/")||servletPath.contains("EmailServlet") ) {
            chain.doFilter(req, response);
        } else {
            Object loginUser = req.getSession().getAttribute("loginUser");
            if (loginUser == null) {
                ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
            } else {
                chain.doFilter(req, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
