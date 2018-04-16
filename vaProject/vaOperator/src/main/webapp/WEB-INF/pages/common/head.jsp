<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<style>
@media ( min-width : 1200px) {
	.container {
		width: 1200px;
	}
}

.nav-bar {
	background-color: #27a5fd;
	height: 62px;
}

.logo_wapper {
	margin-left: 15px;
	margin-top: 16px;
}

.nav-bar-list {
	height: 100%;
}

.nav-bar-list ul {
	height: 100%;
	padding: 0;
	float: left;
	margin: 0;
	padding: 15px 0 15px 0;
}

.nav-bar-list li {
	display: block;
	float: left;
	width: 80px;
	height: 35px;
	margin-right:30px;
	border-radius: 4px;
	line-height: 30px;
	font-family: "微软雅黑";
	font-size: 16px;
	color: #fff;
	text-align: center
}

.nav-bar-list a {
	text-decoration: none;
	color:#fff;
}

.nav-bar-list .button-group {
	padding: 11px 0 12px 0;
}

.nav-bar-list li.active {
	 background: #fff;
}

.nav-bar-list li.active>a {
	color:#27a5fd;
}
</style>

<div class="nav-bar">
	<div class="container" style="height: 100%">
		<div class="pull-left logo_wapper">
			<img src="<%=request.getContextPath()%>/resources/images/logo_header.png" alt="">
		</div>
		<div class="nav-bar-list pull-right ">
			<ul class="menu">
				<li >
					<a href="<%=request.getContextPath()%>/user/user_manager">用户管理</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/company/company_manager">公司管理</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/plan/plan_manager">任务管理</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/flight/flight_manager">航班管理</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/aircraft/aircraft_manager">飞机管理</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/community/type_manager">社区管理</a>
				</li>
				<li>
					<a href="#">系统参数</a>
				</li>
				<li>
					<a href="#">信息统计</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$(".menu>li").removeClass("active");
		$(".menu>li:eq(${param.index})").addClass("active");
	});
</script>
