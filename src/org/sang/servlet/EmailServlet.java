package org.sang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.sang.bean.User;

public class EmailServlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String useremailcode=getcode();
    	req.getSession().setAttribute("useremailcode", useremailcode);
    	String email = req.getParameter("email");
    	sendEmail(email, useremailcode);
    }

	 public String getcode()
	    {
	    	Random random = new Random();
	    	StringBuffer code = new StringBuffer();
			for (int i = 0; i < 6; i++) {
				code.append(random.nextInt(10));
			}
			return code.toString();
	    }
	    
	    public boolean sendEmail(String emailaddress, String code) {

			try {
				HtmlEmail email = new HtmlEmail();
				email.setHostName("smtp.163.com");
				email.setCharset("UTF-8");
				email.addTo(emailaddress);// 收件地址
				email.setSSL(true);
				email.setSslSmtpPort("465");
				email.setFrom("aldnoahzeronull@163.com", "农大课检系统");

				email.setAuthentication("aldnoahzeronull@163.com", "mhg199837");

				email.setSubject("农大课检系统");
				email.setMsg("农大课检系统！\n今天你签到了吗？\n你的动态验证码是:" + code+" !\n青年强则国家强，青年兴则国家兴！");

				email.send();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	    }

}
