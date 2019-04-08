package org.sang.servlet;

import org.sang.bean.User;
import org.sang.service.UserService;

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
public class ApproveaccountServlet extends HttpServlet {
    private UserService employeeService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = employeeService.getUnApproveaccount();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/approveaccount.jsp").forward(req, resp);
    }
}
