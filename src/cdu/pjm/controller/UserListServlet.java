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
import java.util.List;

@WebServlet(name = "UserListServlet",urlPatterns = "/userList")
public class UserListServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    private static final int count = 4;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        checkSessionStatus(request,"deleteStatus");
//        checkSessionStatus(request,"updateStatus");
        String pageIndexStr = request.getParameter("pageIndex");
        if (pageIndexStr==null||pageIndexStr=="")
            pageIndexStr="1";
        int pageIndex = Integer.parseInt(pageIndexStr);
        pageIndex=pageIndex<=0?1:pageIndex;
        int userCount = userService.getUserCount();
        int pageNum=userCount/count+(userCount%count==0?0:1);
        request.setAttribute("pageCount",pageNum);
        if(pageIndex>pageNum){
            pageIndex=pageNum;
        }else if(pageIndex<=0){
            pageIndex=1;
        }
        List<User> userList = userService.getUserList((pageIndex - 1) * count, count);
        request.setAttribute("pageIndex",pageIndex);
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("view/userlist.jsp").forward(request,response);
    }
    private void checkSessionStatus(HttpServletRequest request,String statusName) {
        HttpSession session = request.getSession();
        String status = (String)session.getAttribute(statusName);
        if (status!=null){
            session.removeAttribute(statusName);
            if ("true".equals(status)){
                request.setAttribute(statusName.substring(0,statusName.lastIndexOf("Status"))+"Success",1);
            }else{
                request.setAttribute(statusName.substring(0,statusName.lastIndexOf("Status"))+"Failed",1);
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
