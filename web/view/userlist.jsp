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
    <title>用户列表</title>
</head>
<style>
    .headImg {
        border-radius: 50%;
        height: 60px;
        width: 60px;
    }

    td {
        font-family: "华文行楷";
        font-size: 35px;
        width: 200px;
        text-align: center;
    }

    .textTd {
        border: aqua 1px solid;
    }

    tr {

    }

    .mhead {
        width: 100%;
        text-align: center;
        font-family: "华文行楷";
        font-size: 35px;
    }

    .add {
        width: 100%;
    }

    .add table {
        width: 60%;
        transform: translateX(20%);
    }

    body {
        background-image: linear-gradient(90deg, rgba(245, 238, 238, 0.5), rgba(255, 246, 143, 0.5),
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

    .mfoot {
        position: fixed;
        transform: translateY(300px);
        top: 45%;
        width: 100%;
        display: flex;
        text-align: center;
    }

    .mfoot a {

        width: 20%;
        transform: translateX(100%);
        font-family: "华文行楷";
        font-size: 35px;
    }
</style>
<body>
<div class="mhead">用户</div>
<br>
<div class="add">
    <table>

        <tbody>
        <c:forEach var="user" items="${requestScope.userList}">
            <tr>
                <td>
                    <img class="headImg" src="${user.userPicUrl}">
                </td>
                <td class="textTd" id="userName">${user.userName}</td>
                <td class="textTd" id="userLiske">${user.userAge}</td>
                <td class="textTd"><a href="delUser?userId=${user.userID}"><input type="button" value="删除"></a></td>
                <td class="textTd"><a href="modPer?userId=${user.userID}"><input type="button" value="修改"></a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2">
                <a href="/web_war_exploded/userList?pageIndex=${(pageIndex-1)<1?1:(pageIndex-1)}">上一页</a>
            </td>
            <td>
                第${pageIndex}页
            </td>
            <td colspan="2">
                <a href="/web_war_exploded/userList?pageIndex=${(pageIndex+1)>pageCount?pageCount:(pageIndex+1)}">下一页</a>
            </td>
        </tr>
        <tr>
            <td colspan="5"></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="mfoot">
    <a href="view/add_user.jsp"><input type="button" value="添加用户"></a><br>
    <a href="/web_war_exploded/modPer"><input type="button" value="修改信息"></a><br>
</div>
</body>
</html>
