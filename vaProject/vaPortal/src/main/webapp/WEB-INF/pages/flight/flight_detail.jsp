<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>iFSim-航班详情</title>
<c:import url="../common/res.jsp"></c:import>
</head>
<body>
	<div>
		<c:import url="../common/head.jsp"></c:import>
		<h1>航班详情</h1>
		<table class="table">
			<thead>
				<tr>
					<td>接地仰角</td>
					<td>接地空速</td>
					<td>接地率</td>
					<td>接地过载</td>
					<td>接地距离</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${report.pitch}</td>
					<td>${report.airspeed}</td>
					<td>${report.vspeed}</td>
					<td>${report.load}</td>
					<td>${report.length}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>