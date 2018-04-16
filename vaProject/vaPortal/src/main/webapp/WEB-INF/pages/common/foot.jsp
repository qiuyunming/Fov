<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<div class="foot" style="position:relative;bottom: 0">
	<div class="foot-wapper container">
		<img src="<%=request.getContextPath()%>/resources/images/logo_foot.png" alt="">
		<p class="foot-wapper-a">
			<a href="<%=request.getContextPath()%>/">主页</a>
			<a href="<%=request.getContextPath()%>/map/show">天巡地图</a>
			<a href="<%=request.getContextPath()%>/company/show">航空公司</a>
			<a href="<%=request.getContextPath()%>/flight/all_flight/1">数据检索</a>
			<a href="##">体验中心</a>
			<a href="<%=request.getContextPath()%>/community/center">飞友社区</a>
			<a href="<%=request.getContextPath()%>/user/user_info">个人中心</a>
		</p>
		<!-- <p class="foot-wapper-b">
			<a href="#">联系我们</a>
			|
			<a href="#">人才招聘</a>
			|
			<a href="#">关于我们</a>
			|
			<a href="#">商务合作</a>
		</p> -->
		<p class="foot-wapper-c1">版权 © 所有2017-2018,iFSim.org,所有权利保留</p>
		<p class="foot-wapper-c2">沪ICP备09003094号-15</p>
	</div>
</div>
<div class="back-to-top text-center" style="position: fixed; bottom: 50px; right: 50px; width: 46px; height: 46px; border-radius: 50%; background-color: #27a5fd; cursor: pointer; padding: 13px 15px 15px 15px; color: #fff; display: none;">
	<i class="icon-chevron-up icon-large "></i>
</div>