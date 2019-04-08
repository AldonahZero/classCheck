package org.sang.servlet;


import org.sang.bean.Status;
import org.sang.bean.User;
import org.sang.bean.Course;
import org.sang.dao.UserDao;
import org.sang.service.CourseService;
import org.sang.service.UserService;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class ExitServlet extends HttpServlet {
    private CourseService meetingService = new CourseService();
    private UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("loginUser");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }


}
