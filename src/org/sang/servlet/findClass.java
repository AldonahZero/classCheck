package org.sang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sang.bean.User;
import org.sang.service.CourseService;
import org.sang.service.UserService;

public class findClass extends HttpServlet {

	private UserService employeeService = new UserService();
	private CourseService meetingService = new CourseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 拿班级id去查此班级在考勤表的情况 
         */
    	String depid = req.getParameter("depid");
    	String depname = req.getParameter("depname");
        Map map=meetingService.getClassMeetingcondition(Integer.parseInt(depid));
        String status = req.getParameter("status");
        String page = req.getParameter("page");
        String count = req.getParameter("count");
        if (status == null || "".equals(status)) {
            status = "1";
        }
        if (page == null || "".equals(page)) {
            page = "1";
        }
        if (count == null || "".equals(count)) {
            count = "10";
        }
//        List<User> list = employeeService.searchEmp(employeename, username, Integer.parseInt(status), Integer.parseInt(page), Integer.parseInt(count));
        int totalCount = map.size();
        int totalPage = totalCount / Integer.parseInt(count) + 1;
//        req.setAttribute("list", list);
        req.setAttribute("map", map);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("page", page);
        req.setAttribute("depid", depid);
        req.setAttribute("depname", depname);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/ClassAttendanceCondition.jsp").forward(req, resp);
    }
}
