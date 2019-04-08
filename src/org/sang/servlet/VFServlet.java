package org.sang.servlet;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class VFServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 生成图片 文本保存到session域中
		 * 图片发给客户端
		 */
		VF vf=new VF();
		BufferedImage bufferedImage=vf.getImage();
		request.getSession().setAttribute("vfcode", vf.getText());
		vf.output(bufferedImage, response.getOutputStream());
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

}
