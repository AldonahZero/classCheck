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
 * Created by sang on 2017/8/23.
 */
public class NotificationsServlet extends HttpServlet {
    private CourseService meetingService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * 保存员工id，七天会议，取消的会议
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int loginEmpId = ((User) req.getSession().getAttribute("loginUser")).getEmployeeid();
        List<Course> mt7 = meetingService.getMeeting7Days(loginEmpId);
        List<Course> cm = meetingService.getCanceledMeeting(loginEmpId);
        req.setAttribute("mt7", mt7);
        req.setAttribute("cm", cm);
        req.getRequestDispatcher("/notifications.jsp").forward(req, resp);
    }
}
