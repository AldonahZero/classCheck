package org.sang.servlet;

import org.sang.bean.Course;
import org.sang.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 搜索会议室
 * @author 零
 *
 */
public class SearchCourseServlet extends HttpServlet {
    private CourseService meetingService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String meetingname = req.getParameter("meetingname");
        String roomname = req.getParameter("roomname");
        String reservername = req.getParameter("reservername");
        String reservefromdate = req.getParameter("reservefromdate");
        String reservetodate = req.getParameter("reservetodate");
        String meetingfromdate = req.getParameter("meetingfromdate");
        String meetingtodate = req.getParameter("meetingtodate");
        String page = req.getParameter("page");
        String count = req.getParameter("count");
        if (page == null || "".equals(page)) {
            page = "1";
        }
        if (count == null || "".equals(count)) {
            count = "10";
        }
        List<Course> list = meetingService.searchMeeting(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate, Integer.parseInt(page), Integer.parseInt(count));
        int totalCount = meetingService.getCount(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate);
        int totalPage = totalCount / Integer.parseInt(count) + 1;
        req.setAttribute("list", list);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("page", page);
        req.setAttribute("meetingname", meetingname);
        req.setAttribute("roomname", roomname);
        req.setAttribute("reservername", reservername);
        req.setAttribute("reservefromdate", reservefromdate);
        req.setAttribute("reservetodate", reservetodate);
        req.setAttribute("meetingfromdate", meetingfromdate);
        req.setAttribute("meetingtodate", meetingtodate);
        req.setAttribute("count", count);
        req.getRequestDispatcher("/searchcourses.jsp").forward(req, resp);
    }
}
