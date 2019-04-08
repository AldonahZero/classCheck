<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>农大课检系统</title>
    <link rel="stylesheet" href="styles/common.css"/>
    <style type="text/css">
        #divfrom {
            float: left;
            width: 200px;
        }

        #divto {
            float: left;
            width: 200px;
        }

        #divoperator {
            float: left;
            width: 50px;
            padding: 60px 5px;
        }

        #divoperator input[type="button"] {
            margin: 10px 0;
        }

        #selDepartments {
            display: block;
            width: 100%;
        }

        #selEmployees {
            display: block;
            width: 100%;
            height: 200px;
        }

        #selSelectedEmployees {
            display: block;
            width: 100%;
            height: 225px;
        }
    </style>
   
</head>
<body onload="body_load()">
<jsp:include page="top.jsp"/>
<div class="page-body">
    <jsp:include page="leftMenu.jsp"/>
    <div class="page-content">
        <div class="content-nav">
            课程预定 > 修改课程预定
        </div>
        <form>
            <fieldset>
                <legend>课程信息</legend>
                <table class="formtable">
                    <tr>
                        <td>课程名称：</td>
                        <td>${mt.meetingname}</td>
                    </tr>
                    <tr>
                        <td>参加人数：</td>
                        <td>${mt.numberofparticipants}</td>
                    </tr>
                    <tr>
                        <td>开始时间：</td>
                        <td>${mt.starttime}</td>
                    </tr>
                    <tr>
                        <td>结束时间：</td>
                        <td>${mt.endtime}</td>
                    </tr>
                    <tr>
                        <td>课程说明：</td>
                        <td>
                            <textarea id="description" rows="5" readonly>${mt.description}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>选修人员：</td>
                        <td>
                            <table class="listtable">
                                <tr class="listheader">
                                    <th>姓名</th>
                                    <th>联系电话</th>
                                    <td>电子邮件</td>
                                    <td>状态</td>
                                    <c:if test="${loginUser.role==3}">
                                    <td>教师权限更改个人考勤</td>
                                    </c:if>
                                </tr>
                                <c:forEach items="${empss}" var="emp">
                                    <tr>
                                        <td>${emp.key.employeename}</td>
                                        <td>${emp.key.phone}</td>
                                        <td>${emp.key.email}</td>
                                        <td>
                                        <c:if test="${emp.value.status==0}">未签到</c:if>
                                        <c:if test="${emp.value.status==1}">已签到</c:if>
                                        <c:if test="${emp.value.status==2}">已请假</c:if>
                                        </td>
                                        <c:if test="${loginUser.role==3}">
                                        <td>
                        					<a class="clickbutton" href="/classCheck/DoCourseDetailsServlet?mid=${mt.meetingid}&type=cancel&class=attend&personid=${emp.key.employeeid}">已到</a>
                        					<a class="clickbutton" href="/classCheck/DoCourseDetailsServlet?mid=${mt.meetingid}&type=cancel&class=cancel&personid=${emp.key.employeeid}">未到</a>
                        					<a class="clickbutton" href="/classCheck/DoCourseDetailsServlet?mid=${mt.meetingid}&type=cancel&class=leave&personid=${emp.key.employeeid}">请假</a>
                    					</td>
                    					</c:if>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <tr>
                    <tr>
                        <td class="command" colspan="2">
                            <c:if test="${type=='cancel'}">
                                <input type="button" class="clickbutton" value="撤销课程"
                                       onclick="window.location.href='/classCheck/cancelmeeting?mid=${mt.meetingid}';"/>
                            </c:if>
                            <c:if test="${loginUser.role>3}">
                            <input type="button" class="clickbutton" value="签到"
                                       onclick="window.location.href='/classCheck/LocationServlet?mid=${mt.meetingid}&userid=${loginUser.employeeid}';"/>
                            <input type="button" class="clickbutton" value="请假"
                                       onclick="window.location.href='/classCheck/leave?mid=${mt.meetingid}&userid=${loginUser.employeeid}';"/>
                            </c:if>
                            <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
                        </td>
                    </tr>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="http://www.yiciyuan.top">我们</a>
    <img src="images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>
