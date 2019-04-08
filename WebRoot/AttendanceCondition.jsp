<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
             课程预定 > 用户考勤情况
        </div>
        <form action="/classCheck/findemployee" method="post">
            <fieldset>
                <legend>个人资料</legend>
                <table class="formtable">
                    <tr>
                        <td>姓名：</td>
                        <td>     
                            <span class="info-number">${employeename}</span>
                        </td>
                        <td>账号名：</td>
                        <td>
                         <span class="info-number">${username}</span>
                            
                        </td>
                        <td>ID：</td>
                        <td>
                         <span class="info-number">${empid}</span>
                            
                        </td>
                    </tr>
                    <tr>
                        
                    </tr>
                </table>
            </fieldset>
        </form>
        <div>
            <h3 style="text-align:center;color:black">考勤情况</h3>
            <div class="pager-header">
                <div class="header-info">
                    共<span class="info-number">${totalCount}</span>条结果，
                    分成<span class="info-number">${totalPage}</span>页显示，
                    当前第<span class="info-number">${page}</span>页
                </div>
                <div class="header-nav">
                    <a type="button" class="clickbutton"
                       href="/classCheck/findemployee?page=1&status=${status}&employeename=${employeename}&username=${username}&empid=${empid}">首页</a>
                    <c:choose>
                        <c:when test="${page>1}">
                            <a type="button" class="clickbutton"
                               href="/classCheck/findemployee?page=${page-1}&status=${status}&employeename=${employeename}&username=${username}&empid=${empid}">上页</a>
                        </c:when>
                        <c:otherwise>
                            上页
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${page<totalPage}">
                            <a type="button" class="clickbutton"
                               href="/classCheck/findemployee?page=${page+1}&status=${status}&employeename=${employeename}&username=${username}&empid=${empid}">下页</a>
                        </c:when>
                        <c:otherwise>
                            下页
                        </c:otherwise>
                    </c:choose>
                    <a type="button" class="clickbutton"
                       href="/classCheck/findemployee?page=${totalPage}&status=${status}&employeename=${employeename}&username=${username}&empid=${empid}">末页</a>
                    <form action="/classCheck/findemployee" method="post" style="display: inline">
                        <input type="hidden" name="employeename" value="${employeename}">
                        <input type="hidden" name="username" value="${username}">
                        <input type="hidden" name="status" value="${status}">
                        <input type="hidden" name="empid" value="${empid}">
                        跳到第<input type="text" id="pagenum" name="page" class="nav-number"/>页
                        <input type="submit" class="clickbutton" value="跳转"/>
                    </form>
                </div>
            </div>
        </div>
        <table class="listtable">
            <tr class="listheader">
                <th>课程ID</th>
                <th>课程名</th>
                <th>状态</th>
            </tr>
            <c:forEach items="${map}" var="emp">
                <tr>
                    <td>${emp.key.meetingid}</td>
                    <td>${emp.key.meetingname}</td>
                    <td> 
                    	<c:if test="${emp.value.status==0}">未签到</c:if>
                    	<c:if test="${emp.value.status==1}">已签到</c:if>
                    	<c:if test="${emp.value.status==2}">已请假</c:if>
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
