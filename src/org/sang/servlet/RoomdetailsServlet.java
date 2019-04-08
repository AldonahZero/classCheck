package org.sang.servlet;

import org.sang.bean.ClassRoom;
import org.sang.service.ClassRoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查看会议室
 * @author 零
 *
 */
public class RoomdetailsServlet extends HttpServlet {
    private ClassRoomService meetingRoomService = new ClassRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomid = req.getParameter("roomid");
        ClassRoom meetingRoom = meetingRoomService.getMeetingRoomById(Integer.parseInt(roomid));
        req.setAttribute("mr", meetingRoom);
        req.getRequestDispatcher("/roomdetails.jsp").forward(req, resp);
    }
}
