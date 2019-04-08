package org.sang.servlet;

import org.sang.bean.ClassRoom;
import org.sang.service.ClassRoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sang on 2017/8/18.
 */
public class GetAllClassroomServlet extends HttpServlet {
    private ClassRoomService meetingRoomService = new ClassRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	String page = req.getParameter("page");
        String count = req.getParameter("count");
        if (page == null || "".equals(page)) {
            page = "1";
        }
        if (count == null || "".equals(count)) {
            count = "10";
        }
    	List<ClassRoom> allMeetingRoom = meetingRoomService.getClassRooms(Integer.parseInt(page), Integer.parseInt(count));
    	int totalCount = meetingRoomService.getAllMeetingRoom().size();
        int totalPage = totalCount / Integer.parseInt(count) + 1;
        req.setAttribute("list", allMeetingRoom);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("page", page);
        req.setAttribute("count", count);
        req.getRequestDispatcher("/classrooms.jsp").forward(req, resp);
    }
}
