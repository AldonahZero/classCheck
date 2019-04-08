package org.sang.servlet;

import org.sang.bean.ClassRoom;
import org.sang.service.ClassRoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加教室
 * @author 零
 *
 */
public class AddClassroomServlet extends HttpServlet {
    private ClassRoomService meetingRoomService = new ClassRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomid = req.getParameter("roomid");
        String roomnum = req.getParameter("roomnum");
        String roomname = req.getParameter("roomname");
        String capacity = req.getParameter("capacity");
        String status = req.getParameter("status");
        String description = req.getParameter("description");
        ClassRoom meetingRoom = new ClassRoom(Integer.parseInt(roomnum), roomname, Integer.parseInt(capacity), Integer.parseInt(status), description);
        if (roomid == null || "".equals(roomid)) {
            //添加教室
            int result = meetingRoomService.insert(meetingRoom);
            if (result == 1) {
                //去查看教室页面
                resp.sendRedirect(req.getContextPath() + "/getallmr");
            } else {
                req.setAttribute("error", "添加失败");
                req.getRequestDispatcher("/addclassroom.jsp").forward(req, resp);
            }
        } else {
            //修改教室
            meetingRoom.setRoomid(Integer.parseInt(roomid));
            int update = meetingRoomService.update(meetingRoom);
            if (update == 1) {
                resp.sendRedirect(req.getContextPath() + "/getallmr");
            }else{
                //更新失败
            }
        }
    }
}
