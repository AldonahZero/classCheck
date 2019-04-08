<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-header">
    <div class="header-banner">
        <img src="images/header.png" alt="CoolMeeting"/>
    </div>
    <div class="header-title">
        欢迎访问——农大课检系统
    </div>
    <div class="header-quicklink">
        欢迎您<strong><c:if test="${loginUser!=null}">,${loginUser.employeename}</c:if></strong>
        <a href="#">
        <c:if test="${loginUser.role==4}">学生,</c:if>
        <c:if test="${loginUser.role==3}">教师,</c:if>
        <c:if test="${loginUser.role==2}">管理员,</c:if>
        <c:if test="${loginUser.role==1}">超级管理员,</c:if>
        </a>
        
			<c:if test="${loginUser!=null}">
        <a id="ab" href="/classCheck/ExitServlet"> [退出登录]</a>
        </c:if>
    </div>
</div>

