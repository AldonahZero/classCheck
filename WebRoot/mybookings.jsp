<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>农大课检系统</title>
    <link rel="stylesheet" href="styles/common.css"/>
    <style type="text/css">

    </style>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="page-body">
    <jsp:include page="leftMenu.jsp"/>
    <div class="page-content">
        <div class="content-nav">
            个人中心 > 我的预定
        </div>
        <table class="listtable">
            <caption>我预定的课程：</caption>
            <tr class="listheader">
                <th>课程名称</th>
                <th>教室名称</th>
                <th>课程开始时间</th>
                <th>课程结束时间</th>
                <th>课程预定时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${mrs}" var="mr">
                <tr>
                    <td>${mr.meetingname}</td>
                    <td>${mr.roomname}</td>
                    <td><fmt:formatDate value="${mr.starttime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                    <td><fmt:formatDate value="${mr.endtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                    <td><fmt:formatDate value="${mr.reservationtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                    <td>
                        <a class="clickbutton" href="/classCheck/meetingdetail?mid=${mr.meetingid}&type=cancel">查看/撤销</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="http://www.yiciyuan.top">我们</a>
    <img src="images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>
