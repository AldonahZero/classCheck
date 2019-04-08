<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>课检系统定位</title>
    <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript"
            src="https://webapi.amap.com/maps?v=1.3&key=f33e5fe81fc7c4c318edb17de339e81b"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <style type="text/css">

        html {
            height: 100%
        }

        body {
            height: 100%;
            margin: 0px;
            padding: 0px
        }

        #container {
            height: 100%
        }

    </style>
<body>
<div id='container'></div>
<div id="tip"></div>


<script type="text/javascript">
var EARTH_RADIUS = 6378137.0;    //单位M

var PI = Math.PI;
function getRad(d){
    return d*PI/180.0;
}
	 function getFlatternDistance(lat1,lng1,lat2,lng2){
    var f = getRad((lat1 + lat2)/2);

    var g = getRad((lat1 - lat2)/2);

    var l = getRad((lng1 - lng2)/2);

    var sg = Math.sin(g);

    var sl = Math.sin(l);

    var sf = Math.sin(f);
    var s,c,w,r,d,h1,h2;

    var a = EARTH_RADIUS;

    var fl = 1/298.257;
    sg = sg*sg;

    sl = sl*sl;

    sf = sf*sf;
    s = sg*(1-sl) + (1-sf)*sl;

    c = (1-sg)*(1-sl) + sf*sl;
    w = Math.atan(Math.sqrt(s/c));

    r = Math.sqrt(s*c)/w;

    d = 2*w*a;

    h1 = (3*r -1)/2/c;

    h2 = (3*r +1)/2/s;
    return d*(1 + fl*(h1*sf*(1-sg) - h2*(1-sf)*sg));
}	
	 
    /***************************************
     由于Chrome、IOS10等已不再支持非安全域的浏览器定位请求，为保证定位成功率和精度，请尽快升级您的站点到HTTPS。
     ***************************************/
    var map, geolocation;
    //加载地图，调用浏览器定位服务
    map = new AMap.Map('container', {
        resizeEnable: true
    });
    map.plugin('AMap.Geolocation', function () {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            buttonPosition: 'RB'
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
    });
    //解析定位结果
    function onComplete(data) {
        var str = ['定位成功'];
        str.push('经度：' + data.position.getLng());
        str.push('纬度：' + data.position.getLat());
        var accuracy= data.accuracy == null ? 0 : data.accuracy
        str.push('精度：' +accuracy + ' 米');
        str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
        document.getElementById('tip').innerHTML = str.join('<br>');
		var mylat=parseFloat("${position.latitude}");
		var mylng=parseFloat("${position.longitude}");
   		var D=getFlatternDistance(data.position.getLat(),data.position.getLng(),mylat,mylng);
   		//alert(D);
   		
   		if(D>300)
   		{
   			setTimeout(function(){
   				alert("你距离要签到的教室太远了！想玩虚的？不给签到！！")
   				window.location.href='/classCheck/meetingdetail?mid=${mid}';
   			},3000);
   			
   		}
   		else
   		{
   			setTimeout(function(){
   				alert("你在教室，签到已成功，要记得好好听课哦");	
   				window.location.href='/classCheck/attendcourse?mid=${mid}&userid=${loginUser.employeeid}';}
   					,3000);
   		}
    }
    //解析定位错误信息
    function onError(data) {
        document.getElementById('tip').innerHTML = '定位失败';
    }
    
    
    
    
</script>



</body>
</html>