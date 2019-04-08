package org.sang.servlet;

import org.sang.bean.User;
import org.sang.bean.Course;
import org.sang.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 将预定会议存到域
 * @author 零
 *
 */
public class MyBookingServlet extends HttpServlet {
    private CourseService meetingService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int loginEmpId = ((User) req.getSession().getAttribute("loginUser")).getEmployeeid();
        List<Course> mrs = meetingService.getMyBookingMeeting(loginEmpId);
        req.setAttribute("mrs", mrs);
        req.getRequestDispatcher("/mybookings.jsp").forward(req, resp);
    }
}
