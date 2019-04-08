<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>农大课检系统</title>
    <link rel="stylesheet" href="styles/common.css"/>
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="page-body">
    <jsp:include page="leftMenu.jsp"/>
    <div class="page-content">
        <div class="content-nav">
            登录
        </div>
        <form method="post" action="/classCheck/login" name="myform">
            <fieldset>
                <legend>登录信息</legend>
                <table class="formtable" style="width:50%">
                    <c:if test="${error!=null}">
                        <tr>
                            <td colspan="2">${error}</td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>账号名:</td>
                        <td>
                            <input id="accountname" placeholder="用户名" name="accountname" type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <td>密码:</td>
                        <td>
                            <input id="new"  placeholder="密码" type="password" name="password"/>
                        </td>
                    </tr>
                    <tr>
							<img id="c_img2" width="0px" style="height: 0px;">
							<td>动态: &nbsp;</td><td><input id="sendSmsBtn" type="button"
								name="动态验证码" value="  获取邮件动态验证码   " class="button button-secondary"
								 />
								</td>
								
						</tr>
						<tr>
							<td>验证码:</td>
							<td><input type="text"  placeholder="邮件动态验证码" id="emailcode" name="emailcode"
								maxlength="20" /></td>
						</tr>
                    <tr>
                        <td colspan="2" class="command">
                            <input type="submit" value="登录" class="clickbutton"/>
                            <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                        </td>
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
<script type="text/javascript" color="125,125,125" opacity='0.8' zIndex="-2" count="200" src="js/movetriangle.js"></script>
<script> 
$(document).ready(function()
	    {
			document.getElementById('sendSmsBtn').addEventListener("click",showtime);
			document.getElementById('sendSmsBtn').addEventListener("click",my);
	    });
	    
	    function my(){
	    	document.getElementById('c_img2').src = '/classCheck/LoginEmailServlet?accountname=' + document.getElementById('accountname').value;
	    }
    function showtime(){
    	 //
    	 //$.get("'/classCheck/LoginEmailServlet?accountname=' + document.getElementById('accountname').value");
    	 document.getElementById('sendSmsBtn').disabled=true;
        for(i=1;i<=30;i++) {
            window.setTimeout("update_p(" + i + ","+30+")", i *1000 );
        }
		
    }
    function update_p(num,t) {
        if(num == t) {
        	document.getElementById('sendSmsBtn').value ="           重新发送           ";
        	document.getElementById('sendSmsBtn').disabled=false; 
        }
        else {
            printnr = t-num;
            if(printnr<10)
            {
            	document.getElementById('sendSmsBtn').value = "    (0" + printnr +")秒后重新发送     ";
            }
            else
            	{
            	document.getElementById('sendSmsBtn').value = "    (" + printnr +")秒后重新发送     ";
            	}
            
        }
    }
</script> 
</body>
</html>
