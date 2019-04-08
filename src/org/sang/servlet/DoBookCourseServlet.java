package org.sang.servlet;

import org.sang.bean.User;
import org.sang.bean.Course;
import org.sang.service.CourseService;
import org.sang.service.DepartmentService;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * 预定会议室
 * @author 零
 *
 */
public class DoBookCourseServlet extends HttpServlet {
    private CourseService meetingService = new CourseService();
    private DepartmentService departmentService=new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String meetingname = req.getParameter("meetingname");
        String numberofparticipants = req.getParameter("numberofparticipants");
        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");
        String roomid = req.getParameter("roomid");
        String description = req.getParameter("description");
        String[] selSelectedEmployees = req.getParameterValues("selSelectedEmployees");
        //获取当前登录的用户对象
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        Course meeting = new Course(meetingname, Integer.parseInt(roomid), loginUser.getEmployeeid(), Integer.parseInt(numberofparticipants), Timestamp.valueOf(starttime), Timestamp.valueOf(endtime), new Timestamp(System.currentTimeMillis()), description);
        meetingService.insert(meeting, selSelectedEmployees);
        resp.sendRedirect(req.getContextPath() + "/searchcourses.jsp");
    }
}
