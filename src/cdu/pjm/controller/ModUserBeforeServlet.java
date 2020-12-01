package cdu.pjm.controller;

import cdu.pjm.model.User;
import cdu.pjm.service.UserService;
import cdu.pjm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

@WebServlet(name = "ModUserBeforeServlet",urlPatterns = "/modPer")
public class ModUserBeforeServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        if (userId < 0) return;
        HttpSession session = request.getSession();
        if (session.getAttribute("updateStatus") != null) {
            session.removeAttribute("updateStatus");
            request.setAttribute("updateStatus", 1);
        }
        User user = userService.queryUser(userId);
        request.setAttribute("user", user);
        Vector<String> userLikes=new Vector<>();
        String strs[]=user.getUserLike().substring(1,user.getUserLike().length()-1).split(", ");
        for(String st:strs){
            userLikes.add(st);
        }
        request.setAttribute("userLikes", userLikes);
        request.getRequestDispatcher("view/mod_user.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
