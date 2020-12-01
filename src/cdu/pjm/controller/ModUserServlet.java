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
        String userId=request.getParameter("userId");
        User user =getUser(request,Long.parseLong(userId));
        if (user==null){
            request.getSession().setAttribute("updateStatus",0);
            response.sendRedirect("/web_war_exploded/modUser?userId="+userId);
            return;
        }
//        user.setUserID(Long.parseLong(userId));
        int tag = userService.updateUser(user);
        HttpSession session = request.getSession();
        if (tag>0){
            session.setAttribute("updateStatus","true");
            response.sendRedirect(request.getContextPath()+"/web_war_exploded/userList");
        } else{
            session.setAttribute("updateStatus","false");
            response.sendRedirect(request.getContextPath()+"/web_war_exploded/modUser?userId="+userId);
        }
        System.out.println("更改成功");
    }
    static User getUser(HttpServletRequest request,Long userId){
        boolean isMult = ServletFileUpload.isMultipartContent(request);
        String userName="";
        String userPwd="";
        String userGender="";
        String userAge="";
        Vector<String> userLike=new Vector<>();
        String userPicUrl= "";
        if(isMult){//是否为文件
            FileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
//            items接受所有
            try {
                List<FileItem> items = upload.parseRequest(request);
                //迭代器遍历item
                Iterator<FileItem> iter=items.iterator();
                while(iter.hasNext()){
                    FileItem item=iter.next();
                    String itemName=item.getFieldName();
                    if(item.isFormField()){//not picture
                        switch(itemName){
                            case "txtName":
                                userName=item.getString("UTF-8");
                                break;
                            case "txtPW":
                                userPwd=item.getString("UTF-8");
                                break;
                            case "gender":
                                userGender=item.getString("UTF-8");
                                break;
                            case "selH":
                                userAge=item.getString("UTF-8");
                                break;
                            case "txtLOVE":
                                userLike.add(item.getString("UTF-8"));
                                break;
                            default://??
                                break;
                        }
                    }else{//img pictuer ->file
                        //storge path
                        String path=request.getSession().getServletContext().getRealPath("");
                        //gain fileName
                        String fileName=item.getName();
                        //gain fileContent&upload
                        File file = new File(path+"/photo",userId+fileName.substring(fileName.lastIndexOf(".") ));
                        /*===========================================*/

                        /*===========================================*/
                        //write imgFile
                        item.write(file);
                        userPicUrl+="photo/"+userId+fileName.substring(fileName.lastIndexOf("."));
                    }
                }
            } catch (FileUploadException e) {
                //file upload error
                e.printStackTrace();
                System.out.println("file upload error");
            } catch (Exception e) {
                //file write Error
                e.printStackTrace();
                System.out.println("file write Error");
            }
        }
        User user = new User(userId, userName, userPwd, userGender, userAge, userLike.toString(),userPicUrl);
        return user;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
