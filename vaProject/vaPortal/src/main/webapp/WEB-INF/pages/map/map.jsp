<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>天巡</title>
<c:import url="../common/res.jsp"></c:import>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FcnShfP665TAftLvuScwHiduUoClWSSc"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/map.js/"></script>
<script type="text/javascript">
$(function() {
	var mapObject = $.Map('Bmap',{});
	var map = mapObject.getMap();

	//飞机图标
	var myIcon = new BMap.Icon("<%=request.getContextPath()%>/resources/images/plane.svg",
			new BMap.Size(32, 32));

	
	var update =  function(){
		request("<%=request.getContextPath()%>/map/data",{}).done(function(resp) {
			for(i in resp){
				var data = resp[i];
				paintPlanes(data.latitude,data.longitude,data.heading,data.pilotUsername);
			}
		});
	};
	//请求飞机数据
	update();
	var inteval = setInterval(update, 3000);
	//所有飞机
	var planes = {};
	// 把飞机信息加入图中
	function paintPlanes(latitude, longitude, heading, pilotUsername) { 
		
		//创建点
		var point = new BMap.Point(longitude, latitude);
		// 创建飞机标注
		if(typeof planes[pilotUsername]=='undefined'){
			//添加飞机
			var plane = new BMap.Marker(point, {
				icon : myIcon
			});
			plane.setRotation(heading);
			plane.pilotUsername = pilotUsername;
			console.log(pilotUsername);
			map.addOverlay(plane); 
			
			planes[pilotUsername] = {};
			planes[pilotUsername].plane = plane;
			
			plane.addEventListener('click', function(e) {
				var zoom = map.getZoom();
				if(zoom<10){
					setTimeout(function(){
						map.setZoom(12);
					},600);
				}
				map.panTo(e.target.getPosition());
				console.log(e);
				alert('机组编号:'+e.target.pilotUsername+'\n经度:' + e.point.lng + '\n纬度:' + e.point.lat + '\n航向:'
						+ e.target.getRotation());
				
			});

			var polyline = new BMap.Polyline([ point ], {
				strokeColor : '#99CC00',
				strokeWeight : 6,
				strokeOpacity : 0.8
			});
			map.addOverlay(polyline);
			planes[pilotUsername].polyline = polyline;
		}else{
			
			//更新飞机
			planes[pilotUsername].plane.setPosition(point);
			planes[pilotUsername].plane.setRotation(heading);

			var path = planes[pilotUsername].polyline.getPath();
			path.push(point);
			planes[pilotUsername].polyline.setPath(path);
		}
	}
	
	//设置地图风格
	$.getJSON('<%=request.getContextPath()%>/resources/json/styleDay.json', function(data) {

			map.setMapStyle({
				styleJson : data
			});

		});

	})
</script>
</head>
<body>
	<div class="main-wapper">
		<c:import url="../common/head.jsp">
			<c:param name="index" value="1"></c:param>
		</c:import>
		<div class="content row">
			<div class="col-xs-12" id="Bmap"></div>
		</div>
	</div>
</body>
</html>