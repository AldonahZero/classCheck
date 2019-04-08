<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            课程预定 > 查看教室
        </div>
        <div>
            <h3 style="text-align:center;color:black">查询结果</h3>
            <div class="pager-header">
                <div class="header-info">
                    共<span class="info-number">${totalCount}</span>条结果，
                    分成<span class="info-number">${totalPage}</span>页显示，
                    当前第<span class="info-number">${page}</span>页
                </div>
                <div class="header-nav">
                    <a type="button" class="clickbutton"
                       href="getallmr?page=1">首页</a>
                    <c:choose>
                        <c:when test="${page>1}">
                            <a type="button" class="clickbutton"
                               href="getallmr?page=${page-1}">上页</a>
                        </c:when>
                        <c:otherwise>上页</c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${page<totalPage}">
                            <a type="button" class="clickbutton"
                               href="getallmr?page=${page+1}">下页</a>
                        </c:when>
                        <c:otherwise>
                            下页
                        </c:otherwise>
                    </c:choose>
                    <a type="button" class="clickbutton"
                       href="getallmr?page=${totalPage}">末页</a>
                    <form action="getallmr" style="display: inline" method="post">                      
                        跳到第<input type="text" id="pagenum" name="page" class="nav-number"/>页
                        <input type="submit" class="clickbutton" value="跳转"/>
                    </form>
                </div>
            </div>
        </div>
        
        <table class="listtable">
            <caption>所有教室:</caption>
            <tr class="listheader">
                <th>门牌编号</th>
                <th>教室名称</th>
                <th>容纳人数</th>
                <th>当前状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${list}" var="mr">
                <tr>
                    <td>${mr.roomnum}</td>
                    <td>${mr.roomname}</td>
                    <td>${mr.capacity}</td>
                    <td>
                        <c:choose>
                            <c:when test="${mr.status==0}">启用</c:when>
                            <c:when test="${mr.status==1}">停用</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <a class="clickbutton" href="/classCheck/roomdetails?roomid=${mr.roomid}">查看详情</a>
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
