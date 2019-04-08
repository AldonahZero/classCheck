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
import org.sang.bean.Status;
import org.sang.bean.User;
import org.sang.service.CourseService;
/**
 * 参加课程
 * @author 零
 *
 */
public class AttendCourseServlet extends HttpServlet {

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
        int i=meetingService.attendMeeting(Integer.parseInt(mid),Integer.parseInt(userid));
        req.setAttribute("i", i);
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
