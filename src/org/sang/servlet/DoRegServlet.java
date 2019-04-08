package org.sang.servlet;

import org.apache.commons.mail.HtmlEmail;
import org.sang.bean.User;
import org.sang.service.UserService;
import org.sang.util.Md5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
/**
 * 
 * @author 零
 *
 */
public class DoRegServlet extends HttpServlet {
    private UserService employeeService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String uservfcode=(String)req.getSession().getAttribute("vfcode");//图片文本
    	String captcha_code=req.getParameter("check");
        if(!captcha_code.equalsIgnoreCase(uservfcode))
		{
			req.setAttribute("error", "验证码错误");
			req.getRequestDispatcher("/reg").forward(req, resp);
			return;
		}
        String useremailcode=(String)req.getSession().getAttribute("useremailcode");
        String email_code=req.getParameter("emailcode");
        if(!email_code.equalsIgnoreCase(useremailcode))
		{
			req.setAttribute("error", "动态验证码错误");
			req.getRequestDispatcher("/reg").forward(req, resp);
			return;
		}
    	String employeename = req.getParameter("employeename");
        String accountname = req.getParameter("accountname");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String deptid = req.getParameter("deptid");
        User employee = new User(employeename, accountname, phone, email, Integer.parseInt(deptid), Md5.GetMD5Code(password));
        int reg = employeeService.reg(employee);
        if (reg == 1) {
            //注册成功，跳转到登录页面
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else if (reg == -1) {
            //用户名重复，注册失败
            req.setAttribute("error", "用户名重复，注册失败");
            req.getRequestDispatcher("/reg").forward(req, resp);
        } else {
            req.setAttribute("error", "不明原因，注册失败");
            req.getRequestDispatcher("/reg").forward(req, resp);
        }
    }
   

}
