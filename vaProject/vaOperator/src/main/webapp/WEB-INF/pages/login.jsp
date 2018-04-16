<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="./common/res.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>管理员登录</title>
</head>
<body>
	<div>
		<div class="center-block" style="width: 300px">
			<h1>管理员登录</h1>
			<div class="">
				<form class="form-horizontal" action="<%=request.getContextPath()%>/auth/doLogin" method="post">
					<div class="form-group">
						<input class="form-control" type="text" name="username" placeholder="呼号或手机号">
					</div>
					<div class="form-group">
						<input class="form-control" type="password" name="password" placeholder="密码">
					</div>
					<div class="form-group">
						<div class="checkbox">
							<label>
								<input type="checkbox" name="rememberMe">
								记住我
							</label>
						</div>
					</div>
					<div class="form-group">
						<input class="btn btn-primary btn-block" type="submit" value="登录">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>