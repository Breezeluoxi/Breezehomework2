package cdu.pjm.controller;

import cdu.pjm.service.UserService;
import cdu.pjm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelUserServlet",urlPatterns = "/delUser")
public class DelUserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        Long userId=Long.parseLong(userIdStr);
        if (userId<0)return;
        int isOK = userService.deleteUser(userId);
        if (isOK>0){
            request.getSession().setAttribute("deleteStatus","true");
        }else{
            request.getSession().setAttribute("deleteStatus","false");
        }
        response.sendRedirect("/web_war_exploded/userList");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
