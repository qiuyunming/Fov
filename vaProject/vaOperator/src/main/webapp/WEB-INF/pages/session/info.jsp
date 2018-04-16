<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>信息统计</title>
<c:import url="../common/res.jsp"></c:import>
<c:import url="../common/table.jsp"></c:import>
</head>
<body>
	<div>
		<c:import url="../common/head.jsp">
			<c:param name="index" value="7"></c:param>
		</c:import>
		<div>
			<h1>在线人数: ${sesessionCount}</h1>
			<c:forEach items="${sessions}" var="item">
				${item.host}<br>
			</c:forEach>
		</div>
	</div>
</body>
</html>