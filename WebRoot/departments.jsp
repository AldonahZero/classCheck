<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>农大课检系统</title>
<link rel="stylesheet" href="styles/common.css" />
<script src="js/jquery-3.2.1.js"></script>
<script src="./My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<jsp:include page="top.jsp" />
	<div class="page-body">
		<jsp:include page="leftMenu.jsp" />
		<div class="page-content">
			<div class="content-nav">人员管理 > 班级管理</div>
			<form method="post" action="/classCheck/adddep">
				<fieldset>
					<legend>添加班级</legend>
					${error} 班级名称: <input type="text" id="departmentname"
						name="departmentname" maxlength="20" /> <input type="submit"
						class="clickbutton" value="添加" />
				</fieldset>
			</form>
			<form method="post" action="/classCheck/AllClassCondition">
				<fieldset>
				<legend>课检图表</legend>
					<table class="formtable">
						<tr>
							<td>开始时间：</td>
							<td><input class="Wdate" type="text" id="starttime"
								name="starttime"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							</td>
						</tr>
						<tr>
							<td>结束时间：</td>
							<td><input class="Wdate" type="text" id="endtime"
								name="endtime"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							</td>
						</tr>
						<tr>
							<td class="command" colspan="2"><input type="submit"
								class="clickbutton" value="查看" /> <input type="reset"
								class="clickbutton" value="重置" /></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<table class="listtable">

				<caption>所有班级:</caption>
				<tr class="listheader">
					<th>班级编号</th>
					<th>班级名称</th>
					<th>操作</th>
				</tr>
				<c:if test="${error!=null}">
					<tr>
						<td colspan="3">${error}</td>
					</tr>
				</c:if>
				<c:forEach items="${list}" var="dep">
					<tr>
						<td>${dep.departmentid}</td>
						<td id="depname${dep.departmentid}">${dep.departmentname}</td>
						<td><a class="clickbutton" href="#"
							id="edit${dep.departmentid}"
							onclick="editDep(${dep.departmentid})">编辑</a> <a
							class="clickbutton" href="#" style="display: none"
							id="cancel${dep.departmentid}"
							onclick="cancelEdit(${dep.departmentid},'${dep.departmentname}')">取消</a>
							<a class="clickbutton"
							href="/classCheck/deletedep?depid=${dep.departmentid}">删除</a> <a
							class="clickbutton"
							href="/classCheck/findClass?depid=${dep.departmentid}&depname=${dep.departmentname}">查看班级考勤情况</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="http://www.yiciyuan.top">我们</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
	<script>
    function cancelEdit(depid, depName) {
        var cancelBtn = $("#cancel" + depid);
        var editBtn = $("#edit" + depid);
        var ele = $("#depname" + depid);
        cancelBtn.css("display", "none");
        editBtn.html("编辑");
        ele.html(depName);
    }
    function editDep(depid) {
        var cancelBtn = $("#cancel" + depid);
        var editBtn = $("#edit" + depid);
        var ele = $("#depname" + depid);//<td id="13">1610</td>
        if (cancelBtn.css("display") == 'none') 
        {
            cancelBtn.css("display", "inline");
            editBtn.html("确定");
            var depName = ele.text();
            ele.html("<input type='text' value='" + depName + "'/>");
        }
        else 
        {
            //提交修改
            var children = ele.children("input");
            var val = children.val();
            $.post("/classCheck/updateDep", {id: depid, depName: val}, function (msg) {
                alert(msg);
                cancelBtn.css("display", "none");
                editBtn.html("编辑");
                ele.html(val);
            });
        }
    }
</script>
</body>
</html>
