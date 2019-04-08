<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.sang.bean.Department"%>
<%@ page import="org.sang.bean.Status"%>
<%@ page import="org.sang.bean.StringBean"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>农大课检系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css">
</style>
<script type="text/javascript" src="js/echarts-all.js"></script>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
</head>
<body>
	<jsp:include page="top.jsp" />
	<div class="page-body">
		<jsp:include page="leftMenu.jsp" />
		<div class="page-content">
			<!-- 为ECharts准备一个具备大小（宽高）的DOM -->
			<div id="main" style="width: 700px; height: 400px;"></div>
				<input type="button" class="clickbutton" value="返回上一页" onclick="window.history.back();"/>


		</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="http://www.yiciyuan.top">我们</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
	<!--  
	<c:forEach items="${datebase}" var="emp">
                <tr>
                    <c:forEach items="${emp.value}" var="emp2">
                    <td>${emp.key.departmentname}</td>
                    <td>${emp.key.departmentid}</td>
                    <td>${emp2.key.string}</td>
                    <td> 
                    	"${emp2.value.status}"
                    </td>
                    
                     </c:forEach>          
                </tr>
   </c:forEach>
   -->
<!-- js代码 -->

<script type="text/javascript">

var zxz=0;
var riqi2=finad();
var totalda=finadata();

 	//数据已呈上
 	window.onload=function()
	{
 		alert(totalda);
		 riqi2.sort(); 
	}
 	function finad()
    {
 		var juburiqi=[];
		<% 
		Map<Department, Map<StringBean, Status>> datebase2=(Map<Department, Map<StringBean, Status>>)request.getAttribute("datebase");
		for (Department key : datebase2.keySet()) {
			for (StringBean key2 : datebase2.get(key).keySet())
			{
				%>
				if(zxz<8)juburiqi[zxz]=<%=(String)("\""+key2.getString()+"\"") %>;zxz++;
				<%
			}
			
		}	
	 %>
	
	 return juburiqi.sort();
	}
 	
 	////////
 	function finadata()
    {
 		var totaldata=[];var k=0;
		<% 
		Map<Department, Map<StringBean, Status>> datebase=(Map<Department, Map<StringBean, Status>>)request.getAttribute("datebase");
		for (Department key : datebase.keySet()) {
			%>var j=0;var datas=[];<%
			for (StringBean key2 : datebase.get(key).keySet())
			{
				%>
				datas[j]=<%=(datebase.get(key).get(key2).getStatus()) %>;j++;
				<%
			}
			%>totaldata[k++]=	datas;<%
		}	
	 %>
	
	 return totaldata;
	}
</script>
<script type="text/javascript">

    /*基于准备好的dom，初始化echarts实例*/
    var myChart = echarts.init(document.getElementById('main'));

    var x='1609';
    var y='1610';
    var z='1611';
    var w='1711';
    var data=[
              [3, 5, 7, 6, 2, 0, 7],
              [11, 11, 15, 13, 12, 13, 10],
              [1, 2, 2, 5, 3, 2, 0],
              [9, 8, 15, 13, 10, 13, 10],
              [2, 4, 5, 6, 8, 13, 10],
              [21, 12, 12, 23, 22, 26, 20],
              [15, 17, 11, 13, 22, 9, 10],
              [14, 18, 15, 16, 22, 8, 7],
              [13, 19, 14, 19, 12, 9, 9],
              [15, 17, 17, 23, 12, 7, 11],
              [12, 15, 15, 21, 10, 8, 11],
              ];
   var i=0;
   var s=0;
    option = {
        title : {
            text: '课检情况',
            subtext: 'EMM'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:[x,y,z,w]
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
               data :riqi2,
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value}人'
                }
            }
        ],
        
        series : [     
            <c:forEach items="${datebase}" var="emp">
            {
                name:"${emp.key.departmentname}",
                type:'line',
                data:totalda[s++],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            },               
			</c:forEach>
            
        ]
    };
                        

    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
</script>

	
</body>
</html>
