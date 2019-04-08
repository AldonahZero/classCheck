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
            课程 > 取消课程
        </div>
        <form action="/classCheck/docanceled" method="post">
            <fieldset>
                <legend>撤销预定</legend>
                <table class="formtable">
                    <tr>
                        <input type="hidden" name="mid" value="${m.meetingid}"/>
                        <td>课程名称：</td>
                        <td>${m.meetingname}</td>
                    </tr>
                    <tr>
                        <td>撤销理由：</td>
                        <td><textarea id="description" rows="5" name="canceledreason"></textarea></td>
                    </tr>
                    <tr>
                        <td class="command" colspan="2">
                            <input type="submit" class="clickbutton" value="确认撤销"></input>
                            <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
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
</body>
</html>
