package org.sang.servlet;

import org.sang.bean.Course;
import org.sang.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 取消课程
 * @author 零
 *
 */
public class CancelCourseServlet extends HttpServlet {
    private CourseService meetingService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid = req.getParameter("mid");
        Course meeting = meetingService.getMeetingDetailsByMeetingId(Integer.parseInt(mid));
        req.setAttribute("m", meeting);
        req.getRequestDispatcher("/cancelcourse.jsp").forward(req, resp);
    }
}
