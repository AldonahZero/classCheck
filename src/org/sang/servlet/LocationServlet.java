package org.sang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sang.bean.Course;
import org.sang.bean.Position;
import org.sang.bean.Status;
import org.sang.bean.User;
import org.sang.service.CourseService;

public class LocationServlet extends HttpServlet {

	private CourseService meetingService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String type = req.getParameter("type");
    	String mid = req.getParameter("mid");
        String userid = req.getParameter("userid");
        req.setAttribute("mid", mid);
        req.setAttribute("userid", userid);
        req.setAttribute("type", type); 
        //拿到对应mid的roomid再取出经纬度
        Position position=meetingService.getclassroompostionBymid(Integer.parseInt(mid));
        req.setAttribute("position", position);
        req.getRequestDispatcher("/location.jsp").forward(req, resp);
    }
}
