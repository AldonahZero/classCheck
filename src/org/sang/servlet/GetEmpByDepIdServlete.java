package org.sang.servlet;

import com.google.gson.Gson;
import org.sang.bean.User;
import org.sang.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sang on 2017/8/21.
 */
public class GetEmpByDepIdServlete extends HttpServlet {
    private UserService employeeService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String depid = req.getParameter("depid");
        List<User> list = employeeService.getEmpByDepId(Integer.parseInt(depid));
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(new Gson().toJson(list));
    }
}
