package org.sang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sang.bean.ClassRoom;
import org.sang.bean.Date;
import org.sang.bean.Department;
import org.sang.bean.Status;
import org.sang.bean.StringBean;
import org.sang.bean.User;
import org.sang.service.ClassRoomService;
import org.sang.service.DepartmentService;
import org.sang.service.UserService;

public class AllClassConditionServlet extends HttpServlet {

	private UserService employeeService = new UserService();
	private DepartmentService departmentService=new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//拿到map<department map<date,number>>的数据
    	String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");
        Date date=new Date(Timestamp.valueOf(starttime), Timestamp.valueOf(endtime));
        System.out.println(date);
 
        Map<Department, Map<StringBean, Status>> datebase=departmentService.getTimestampattationnumber(date);
        
        req.setAttribute("datebase", datebase);
        req.getRequestDispatcher("/AllClassCondition.jsp").forward(req, resp);
    }
}
