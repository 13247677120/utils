<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title></title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://api.map.baidu.com/api?&v=1.3"></script>
</head>
<body>
	<p>
		地址：<input id="txtSearch" type="text" value="116.450262,39.934633" />
		<input type="button" value="搜索" onclick="search()" />
	</p>
	<p>
		<a href="http://api.map.baidu.com/lbsapi/getpoint/index.html"
			target="_blank">去获取坐标</a>
	</p>
	<div style="width: 800px; height: 600px; border: 1px solid gray;"
		id="container">
		<div id="r_result"></div>
	</div>
</body>
<script type="text/javascript">
	function $(id) {
		return document.getElementById(id); //定义$
	}
	var map = new BMap.Map("container"); //创建地图
	map.centerAndZoom(new BMap.Point(113.326286, 23.101593), 15); //初始化地图

	var point = new BMap.Point(113.326286, 23.101593);
	var marker = new BMap.Marker(point);
	var label = new BMap.Label('广州-爱换购', {
		"offset" : new BMap.Size(9, -15)
	});
	marker.setLabel(label);
	map.addOverlay(marker);

	map.enableScrollWheelZoom(); // 开启鼠标滚轮缩放    
	map.enableKeyboard(); // 开启键盘控制    
	map.enableContinuousZoom(); // 开启连续缩放效果    
	map.enableInertialDragging(); // 开启惯性拖拽效果  

	map.addControl(new BMap.NavigationControl()); //添加标准地图控件(左上角的放大缩小左右拖拽控件)  
	map.addControl(new BMap.ScaleControl()); //添加比例尺控件(左下角显示的比例尺控件)  
	map.addControl(new BMap.OverviewMapControl()); // 缩略图控件  
	map.addControl(new BMap.MapTypeControl());

	// 初始化带选择的下拉框的地图
	function search() {
		var text = $("txtSearch").value.split(',');
		//在地图上定位到某点
		map.clearOverlays();
		var new_point = new BMap.Point(text[0], text[1]);
		var marker = new BMap.Marker(new_point); // 创建标注 
		map.addOverlay(marker); // 将标注添加到地图中 
		map.panTo(new_point); //转到该点位置
	}
</script>
</html>