package cdu.pjm.controller;

import cdu.pjm.model.User;
import cdu.pjm.service.UserService;
import cdu.pjm.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "ModUserServlet",urlPatterns = "/modUser")
public class ModUserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String userId=request.getParameter("userId");
        User lastUser=userService.queryUser(Long.parseLong(userId));
        User user =this.getUser(request,Long.parseLong(userId),lastUser);
        if (user==null){
            request.getSession().setAttribute("updateStatus",0);
            response.sendRedirect("/web_war_exploded/modUser?userId="+userId);
            return;
        }
        int tag = userService.updateUser(user);
        HttpSession session = request.getSession();
        if (tag>0){
            System.out.println("成功");
            session.setAttribute("updateStatus","true");
            response.sendRedirect(request.getContextPath()+"/userList");
        } else{
            session.setAttribute("updateStatus","false");
            response.sendRedirect(request.getContextPath()+"/modUser?userId="+userId);
        }
    }
    static User getUser(HttpServletRequest request,Long userId,User luser){
        String userName=request.getParameter("txtName");
        String userPwd=request.getParameter("txtPW");
        String userGender=luser.getUserGender();
        String userAge= request.getParameter("selH");
        Vector<String> userLike=new Vector<>();
        for(String str:request.getParameterValues("txtLOVE")){
            userLike.add(str);
        }
        String userPicUrl= luser.getUserPicUrl();
        User user = new User(userId, userName, userPwd, userGender, userAge, userLike.toString(),userPicUrl);
        return user;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
