<%--
  Created by IntelliJ IDEA.
  User: jiam
  Date: 2020/11/30
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    h1 {
        font-family: "华文彩云";
        color: #0AAD8A;
    }
    .cover {
        width: 100%;
        height: 100%;
        text-align: center;
        color: white;
    }
    .cover:before {
        content: "";
        position: fixed;
        width: 100%;
        height: 100%;
        left: 0;
        background: no-repeat;
        background-position: center;
        background-size: cover;
        z-index: -1;
    }
    #myta {
        color: #000000;
    }
    body {
        background-image: linear-gradient(90deg, rgba(245, 238, 238, 0.5),
        rgba(255, 246, 143, 0.5),
        rgba(255, 106, 106, 0.5), rgba(216, 191, 216, 0.5),
        rgba(255, 45, 136, 0.5), rgba(216, 178, 206, 0.5),
        rgba(25, 132, 109, 0.5), rgba(244, 16, 212, 0.5),
        rgba(135, 206, 255, 0.5));
        background-size: 400%;
        animation: bgMoz 15s infinite;
    }

    @keyframes bgMoz {
        0% {
            background-position: 0% 50%;
        }
        50% {
            background-position: 100% 50%;
        }
        100% {
            background-position: 0% 50%;
        }
    }
</style>
<body>
<h1 align="center">你好吖！${requestScope.user.userName}</h1>
<table id="my" width="700" border="1" cellpadding="8" cellspacing="0" style="border-collapse: collapse" align="center">
    <tbody align="left" id="myta">
    <form action="/web_war_exploded/modUser?userId=${requestScope.user.userID}" method="post" >
        <tr>
            <td>
                请输入你的用户名：
                <input type="text" name="txtName" maxlength="10" value="${requestScope.user.userName}">
                (10个字符以内)
            </td>
        </tr>
        <tr>
            <td>
                请输入你的密码：
                <input type="password" name="txtPW" maxlength="15" value="${requestScope.user.userPwd}">
                (15个字符以内)
            </td>
        </tr>
        <tr>
            <td>
                年龄：
                <select name="selH">
                    <option value="00后" <c:if test="${requestScope.user.userAge=='00后'}">selected</c:if>>00后</option>
                    <option value="90后" <c:if test="${requestScope.user.userAge=='90后'}">selected</c:if>>90后</option>
                    <option value="70后" <c:if test="${requestScope.user.userAge=='70后'}">selected</c:if>>70后</option>
                    <option value="60后" <c:if test="${requestScope.user.userAge=='60后'}">selected</c:if>>60后</option>
                    <option value="其他" <c:if test="${requestScope.user.userAge=='其他'}">selected</c:if>>其他</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                请输入你的爱好：
                <input type="checkbox" name="txtLOVE" value="阅读" <c:if test="${requestScope.userLikes.contains('阅读')}">checked</c:if>>
                阅读&nbsp;
                <input type="checkbox" name="txtLOVE" value="音乐" <c:if test="${requestScope.userLikes.contains('音乐')}">checked</c:if> >
                音乐&nbsp;
                <input type="checkbox" name="txtLOVE" value="运动" <c:if test="${requestScope.userLikes.contains('运动')}">checked</c:if>>
                运动&nbsp;
                <input type="checkbox" name="txtLOVE" value="其他" <c:if test="${requestScope.userLikes.contains('其他')}">checked</c:if> >
                其他&nbsp;
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交"></a>
                <input type="reset" value="重置" ></a>
            </td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
