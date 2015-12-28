<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c-rt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${system_name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${base}/css/global.css" type="text/css" rel="Stylesheet" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
<script src="${thirdparty}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function()
    {
        var map = new BMap.Map("map_canvas"); // 创建Map实例
        var points = new Array();
        <c:forEach var="p" items="${positions}">
        points.push(new BMap.Point('${p.longitude}', '${p.latitude}'));
        </c:forEach>
        var polyline = new BMap.Polyline(points, {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});
        map.addOverlay(polyline);
        var point = new BMap.Point('${lastposition.longitude}', '${lastposition.latitude}'); //初始中心点
        map.centerAndZoom(point, 12);
        map.enableScrollWheelZoom(); // 开启滚轮
        map.enableContinuousZoom(); //  连续平滑缩放
        map.enableInertialDragging(); // 开启拖拽
    });
</script>
</head>
<body>
    <div class="list-container">
        <h2>
            <label>车辆位置</label>
            <span>
                <a href="${base}/admin/car/orbit_car.do?id=${car.car.id}">刷新</a>
            </span>
        </h2>
        <div id="map_canvas" style="width: 800px; height: 600px; margin: 50px auto;"></div>
    </div>
</body>
</html>