<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>农大课检系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="top.jsp" />
	<div class="page-body">
		<jsp:include page="leftMenu.jsp" />
		<div class="page-content">
			<div class="content-nav">人员管理 > 用户注册</div>
			<form method="post" action="/classCheck/doreg">
				<fieldset>
					<legend>用户信息</legend>
					<table class="formtable" style="width:50%">
						<c:if test="${error!=null}">
							<tr>
								<td colspan="2">${error}</td>
							</tr>
						</c:if>
						<tr>
							<td>姓名：</td>
							<td><input type="text" id="employeename" name="employeename"
								maxlength="20" /></td>
						</tr>
						<tr>
							<td>账户名：</td>
							<td><input type="text" id="accountname" name="accountname"
								maxlength="20" /></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><input type="password" id="password" name="password"
								maxlength="20" placeholder="请输入6位以上的密码" /></td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td><input type="password" id="confirm" maxlength="20" /></td>
						</tr>
						<tr>
							<td>联系电话：</td>
							<td><input type="text" id="phone" name="phone"
								maxlength="20" /></td>
						</tr>
						<tr>
							<td>电子邮件：</td>
							<td><input type="text" id="email" name="email"
								maxlength="20" /></td>
						</tr>
						<tr>
							<img id="c_img" width="0px" style="height: 0px;">
							<td>短信验证码: &nbsp;</td><td><input id="sendSmsBtn" type="button"
								name="获取邮件动态验证码" value="获取邮件动态验证码" class="button button-secondary"
								onclick="document.getElementById('c_img').src = '/classCheck/EmailServlet?email=' + document.getElementById('email').value;return false;" />
								</td>
								
						</tr>
						<tr>
							<td>验 证 码：</td>
							<td><input type="text" id="emailcode" name="emailcode"
								maxlength="20" /></td>
						</tr>
						<td>所在部门：</td>
						<td><select name="deptid">
								<c:forEach items="${list}" var="dep">
									<option value="${dep.departmentid}">${dep.departmentname}</option>
								</c:forEach>
						</select></td>

						</tr>

						<tr>
							<td>图形验证：</td>

							<td><img id="captcha_img" src="/classCheck/VFServlet"
								title="看不清?点击更换" alt="看不清?点击更换"
								onclick="document.getElementById('captcha_img').src = '/classCheck/VFServlet?a=' + Math.random();document.getElementById('CAPTCHA').focus();return false;" />

							</td>
						</tr>
						<tr>
							<td>输入验证：</td>
							<td><input type="text" id="check" name="check"
								maxlength="20" /></td>
						</tr>
						<tr>
							<td colspan="6" class="command"><input type="submit"
								class="clickbutton" value="注册" /> <input type="reset"
								class="clickbutton" value="重置" /></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="http://www.yiciyuan.top">我们</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>
