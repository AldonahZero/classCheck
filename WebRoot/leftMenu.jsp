<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-sidebar">
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">个人中心</div>
        <ul class="sidebar-menu">
            <li class="sidebar-menuitem"><a href="/classCheck/notifications">最新通知</a></li>
            <c:if test="${loginUser.role==3}">
            <li class="sidebar-menuitem active"><a href="/classCheck/mybooking">我的预定</a></li>
            </c:if>
            <c:if test="${loginUser.role>=3}">
            <li class="sidebar-menuitem"><a href="/classCheck/mymeeting">我的课程</a></li>
        	</c:if>
        </ul>
    </div>
    <c:if test="${loginUser.role<=2}">
        <div class="sidebar-menugroup">
            <div class="sidebar-grouptitle">人员管理</div>
            <ul class="sidebar-menu">
                <li class="sidebar-menuitem"><a href="departments">班级管理</a></li>
             <c:if test="${loginUser.role==1}">
               	 <li class="sidebar-menuitem"><a href="reg">账号注册</a></li>
              </c:if>
                <li class="sidebar-menuitem"><a href="approveaccount">注册审批</a></li>
                <li class="sidebar-menuitem"><a href="finduser">搜索用户</a></li>
                <c:if test="${loginUser.role<=1}">
                <li class="sidebar-menuitem"><a href="serachemp">管理用户</a></li>
                 </c:if>
            </ul>
        </div>
    </c:if>
     <c:if test="${loginUser.role<=3}">
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">教室管理</div>
        <ul class="sidebar-menu">
            <c:if test="${loginUser.role<=2}">
                <li class="sidebar-menuitem"><a href="addclassroom.jsp">添加教室</a></li>
            </c:if>
            
            <c:if test="${loginUser.role<=3}">
            <li class="sidebar-menuitem"><a href="getallmr">查看教室</a></li>
            	<c:if test="${loginUser.role==3}">
            	<li class="sidebar-menuitem"><a href="/classCheck/bookmeeting">预定课程</a></li>
            	</c:if>
            </c:if>
            
            <c:if test="${loginUser.role<=2}">
                <li class="sidebar-menuitem"><a href="/classCheck/searchmeeting">搜索课程</a></li>
            </c:if>
        </ul>
    </div>
     </c:if>
</div>
