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
 * 要参加的会议
 * @author 零
 *
 */
public class MyCourseServlet extends HttpServlet {
    private CourseService meetingService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int loginEmpId = ((User) req.getSession().getAttribute("loginUser")).getEmployeeid();
        List<Course> list = meetingService.getMyMeeting(loginEmpId);
        req.setAttribute("mrs", list);
        req.getRequestDispatcher("/mycourses.jsp").forward(req, resp);
    }
}
