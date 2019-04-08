<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>农大课检系统</title>
    <link rel="stylesheet" href="styles/common.css"/>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="page-body">
    <jsp:include page="leftMenu.jsp"/>
    <div class="page-content">
        <div class="content-nav">
            个人中心 > <a href="notifications">最新通知</a>
            <div style="display: inline;text-align: right">您是第${vc}位访客!</div>
        </div>
        <table class="listtable">
            <caption>
                我的本周课程:
            </caption>
            <tr class="listheader">
                <th style="width:300px">课程名称</th>
                <th>教室</th>
                <th>起始时间</th>
                <th>结束时间</th>
                <th style="width:100px">操作</th>
            </tr>
            <c:forEach items="${mt7}" var="mt">
                <tr>
                    <td>${mt.meetingname}</td>
                    <td>${mt.roomname}</td>
                    <td><fmt:formatDate value="${mt.starttime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td><fmt:formatDate value="${mt.endtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td>
                        <a class="clickbutton" href="/classCheck/meetingdetail?mid=${mt.meetingid}">查看详情</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <table class="listtable">
            <caption>
                已取消的课程:
            </caption>
            <tr class="listheader">
                <th style="width:300px">课程名称</th>
                <th>教室</th>
                <th>起始时间</th>
                <th>结束时间</th>
                <th>取消原因</th>
                <th style="width:100px">操作</th>
            </tr>
            <c:forEach items="${cm}" var="m">
                <tr>
                    <td>${m.meetingname}</td>
                    <td>${m.roomname}</td>
                    <td><fmt:formatDate value="${m.starttime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td><fmt:formatDate value="${m.endtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td>${m.canceledreason}</td>
                    <td>
                        <a class="clickbutton" href="/classCheck/meetingdetail?mid=${m.meetingid}">查看详情</a>
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
