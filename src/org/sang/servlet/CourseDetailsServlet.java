package org.sang.servlet;

import org.sang.bean.Status;
import org.sang.bean.User;
import org.sang.bean.Course;
import org.sang.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CourseDetailsServlet extends HttpServlet {
    private CourseService meetingService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid = req.getParameter("mid");
        String type = req.getParameter("type");
        Course meeting = meetingService.getMeetingDetailsByMeetingId(Integer.parseInt(mid));
        List<User> emps = meetingService.getEmps();
        Map<User, Status> empss=meetingService.getEmpssHashMap();
        req.setAttribute("mt", meeting);
        req.setAttribute("emps", emps);
        req.setAttribute("empss", empss);
        req.setAttribute("type", type);
        req.getRequestDispatcher("/coursedetails.jsp").forward(req, resp);
    }
}
