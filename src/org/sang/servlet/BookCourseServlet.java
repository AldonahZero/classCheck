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
 * 
 * @author 零
 *
 */
public class BookCourseServlet extends HttpServlet {
    private ClassRoomService meetingRoomService = new ClassRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ClassRoom> list = meetingRoomService.getAllMeetingRoom();
        req.setAttribute("mrs", list);
        req.getRequestDispatcher("/bookcourse.jsp").forward(req, resp);
    }
}
