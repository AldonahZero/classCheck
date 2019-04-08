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

public class FindPersonServlet extends HttpServlet {
	private UserService employeeService = new UserService();
	private CourseService meetingService = new CourseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 拿id去查此人在考勤表的情况 
         */
    	String empid = req.getParameter("empid");
    	Map map=null;
    	if(empid!=null)
    		map=meetingService.getMyMeetingcondition(Integer.parseInt(empid));
        String employeename = req.getParameter("employeename");
        String username = req.getParameter("username");
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
        List<User> list = employeeService.searchEmp(employeename, username, Integer.parseInt(status), Integer.parseInt(page), Integer.parseInt(count));
        int totalCount = map.size();
        int totalPage = totalCount / Integer.parseInt(count) + 1;
        req.setAttribute("empid", empid);
        req.setAttribute("list", list);
        req.setAttribute("map", map);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("page", page);
        req.setAttribute("employeename", employeename);
        req.setAttribute("username", username);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/AttendanceCondition.jsp").forward(req, resp);
    }
}
