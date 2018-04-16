<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>iFSim连飞系统</title>
<c:import url="./common/res.jsp"></c:import>
<style type="text/css">
.top-a {
	position: relative;
	height: 100vh;
	background: -moz-linear-gradient(top, #27a5fd, #1789fc);
	background: -webkit-linear-gradient(top, #27a5fd, #1789fc);
	background: -o-linear-gradient(top, #27a5fd, #1789fc);
}

.check {
	height: 62px;
}

.check>a {
	font-family: "微软雅黑";
	font-size: 16px;
	color: #fff;
	margin-right: 30px;
	line-height: 78px;
	text-decoration: none;
}

.check>button {
	width: 100px;
	height: 38px;
	font-family: "微软雅黑";
	font-size: 16px;
	color: #27a5fd;
	border-radius: 4px;
	border: 0px;
	margin-left: 22px;
	background: #fff;
}

.check-button {
	margin-right: 15px
}

.check>a:hover {
	text-decoration-line: none;
}

.banner-img-wapper {
	display: flex;
	justify-content: center;
	align-items: center;
}

.top-middle-m {
	margin-left: 146px;
	color: #fff;
}

.top-middle-m-p {
	font-family: 华文中宋;
	letter-spacing: 40;
	font-size: 50px;
	line-height: 75px;
}

.banner-content {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.banner-content:after {
	content: normal;
}

.top-foot-no {
	height: 178px;
}

.top-col {
	width: 33.2%;
	float: left;
	text-align: center;
	font-family: "微软雅黑";
	margin-top: 60px;
}

.top-col-no {
	font-size: 48px;
	color: #1873cf;
}

.top-col-text {
	font-size: 14px;
	color: #666;
	margin-top: -10px;
}

#top-col-border {
	border-right: 2px solid #45B0FC;
	border-left: 2px solid #45B0FC;
}

.top-foot-img {
	
}

.medium-nav-title {
	font-family: "微软雅黑";
	font-size: 32px;
	color: #1789fc;
}

.medium-nav-a>a {
	font-family: "微软雅黑";
	font-size: 18px;
	color: #333;
	margin-left: 22px;
	text-decoration: none;
}

.medium-nav-a>a:hover {
	text-decoration-line: none;
	color: #27a5fd;
}

.con {
	position: relative;
	height: 200px;
	overflow: hidden;
	background-repeat: no-repeat;
	background-size: 100% 100%;
	border-radius: 4px;
	margin-bottom: 30px;
}

.medium-img-all .first-col {
	padding-left: 0;
}

.medium-img-all .last-col {
	padding-right: 0;
}

.subscript {
	color: #fff;
	height: 100px;
	width: 100px;
	top: -60px;
	right: -60px;
	position: absolute;
	text-align: center;
	font-family: "微软雅黑";
	-moz-transform: rotate(45deg);
	-webkit-transform: rotate(45deg);
	-o-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	transform: rotate(45deg);
}

.subscriptspan {
	margin-left: 3px;
	margin-top: 80px;
	font-size: 12px;
}

.foot-banner {
	background: #249ffd;
	margin-top: 5px;
	padding-bottom: 60px;
}


.foot-banner-right {
	display: inline-block;
	margin-left: 48px;
	color: #fff;
	font-family: "微软雅黑";
}

.foot-banner-right-button-l {
	height: 42px;
	width: 128px;
	color: #666;
	font-size: 16px;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	border: 0px;
	background: linear-gradient(#d2e9fa, #fff);
}

.foot-banner-right-button-r {
	height: 42px;
	width: 128px;
	color: #666;
	font-size: 16px;
	margin-left: 20px;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	border: 0px;
	background: linear-gradient(#d2e9fa, #fff);
}

.foot {
	padding-bottom: 30px;
	background: #3F3F3F;
	text-align: center;
}

.foot-wapper>img {
	margin-top: 50px;
	margin-bottom: 30px;
}

.foot-wapper-a>a {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #fff;
	text-decoration-line: none;
	margin-bottom: 20px;
	margin-left: 24px;
}

.foot-wapper-b {
	color: #bababa;
}

.foot-wapper-b>a {
	font-family: "微软雅黑";
	font-size: 12px;
	margin-right: 12px;
	margin-left: 12px;
	color: #bababa;
	text-decoration-line: none;
}

.foot-wapper-c1 {
	margin-top: 50px;
	font-family: "微软雅黑";
	font-size: 14px;
	color: #fff;
}

.foot-wapper-c2 {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #fff;
}
</style>
</head>
<body>
	<div class="main-wapper">
		<div class="top">
			<div class="top-a">
				<c:import url="common/head.jsp"></c:import>
				<div class="banner-img-wapper" style="height: 100vh">
					<div>
						<img class="" src="<%=request.getContextPath()%>/resources/images/map_banner.png">
					</div>
				</div>
				<div class="container banner-content" style="">
					<div class="top-middle-m">
						<p class="top-middle-m-p">
							为梦想插上翅膀
							<br>
							与我们一起展翅高飞。
						</p>
						<div style="margin-top: 30px; font-size: 24px; font-family: 微软雅黑">
							100%自主连飞系统
							<br>
							为您提供最完美的模拟飞行体验
						</div>
						<a href="<%=request.getContextPath()%>/auth/login">
							<img src="<%=request.getContextPath()%>/resources/images/btn_banner.png" alt="" style="margin-top: 46px;">
						</a>
					</div>
					<a class="btn-start-flying" style="align-self: center; margin-bottom: 5px;" href="#">
						<img src="<%=request.getContextPath()%>/resources/images/btn_nextpage.png" alt="next">
					</a>
				</div>
			</div>
		</div>
		<div class="medium">
			<div class="container">
				<div class="top-foot-no">
					<div class="top-col">
						<p class="top-col-no">9</p>
						<p class="top-col-text">在线管制员</p>
					</div>
					<div class="top-col" id="top-col-border">
						<p class="top-col-no">136</p>
						<p class="top-col-text">在线飞行员</p>
					</div>
					<div class="top-col">
						<p class="top-col-no">244</p>
						<p class="top-col-text">24小时在线人数</p>
					</div>
				</div>
				<div class="top-foot-img">
					<div class="pull-left pointer" onclick="location.href='<%=request.getContextPath()%>/community/5/1'" style="position:relative;background-color: #27a5fd; height: 200px;width:585px;">
						<div class="text-center" style="margin-top:64px;">
							<div class="white" style="font-size: 36px">我是新手</div>
							<div class="white" style="margin-top:18px;font-size: 18px">基础理论、连飞知识一应俱全</div>
						</div>
						<img alt="" src="<%=request.getContextPath()%>/resources/images/cainiao.png" style="position: absolute;bottom: 0;right: 0"/>
					</div>
					<div class="pull-left pointer" onclick="location.href='<%=request.getContextPath()%>/auth/login'" style="position:relative;background-color: #acddff; height: 200px;width:585px;">
						<div class="text-center" style="margin-top:64px;">
							<div class="white" style="font-size: 36px">我是老手</div>
							<div class="white" style="margin-top:18px;font-size: 18px">注册登录、直接起飞</div>
						</div>
						<img alt="" src="<%=request.getContextPath()%>/resources/images/laoshou.png" style="position: absolute;bottom: 0;right: 0"/>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="medium-nav" style="display: flex; justify-content: space-between; align-items: flex-end; padding: 28px 0 28px 0;">
					<div class="medium-nav-a invisible">
						<a class="" href="#">全部</a>
						<a class="" href="#">活动</a>
						<a class="" href="#">新闻</a>
						<a class="" href="#">公告</a>
					</div>
					<a class="invisible" href="#">查看更多</a>
					<div class="medium-nav-title">活动公告</div>
					<div class="medium-nav-a" style="">
						<a class="" href="#active-all" data-toggle="tab">全部</a>
						<a class="" href="#active-actives" data-toggle="tab">活动</a>
						<a class="" href="#active-news" data-toggle="tab">新闻</a>
						<a class="" href="#active-notices" style="" data-toggle="tab">公告</a>
					</div>
					<a class="grey" href="<%=request.getContextPath()%>/community/center" style="font-size: 16px; text-decoration: none">查看更多</a>
				</div>
				<div class="clearfix"></div>
				<div class="medium-img-all tab-content">
					<div class="tab-pane fade in active" id="active-all">
						<div class="row form-group" style="">
							<c:forEach items="${allArticleList}" var="item" begin="0" end="5">
								<c:choose>
									<c:when test="${item.typeId == 3}">
										<div class="col-md-4">
											<div class="con pointer" style="background-image: url('${item.photo}');" onclick="location.href='<%=request.getContextPath()%>/community/article/${item.id}'">
												<div class="subscript" style="background-color: #27a5fd;">
													<p class="subscriptspan">活动</p>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test="${item.typeId == 2}">
										<div class="col-md-4">
											<div class="con pointer" style="background-image: url('${item.photo}');" onclick="location.href='<%=request.getContextPath()%>/community/article/${item.id}'">
												<div class="subscript" style="background-color: #16d324;">
													<p class="subscriptspan">新闻</p>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test="${item.typeId == 17}">
										<div class="col-md-4">
											<div class="con pointer" style="background-image: url('${item.photo}');" onclick="location.href='<%=request.getContextPath()%>/community/article/${item.id}'">
												<div class="subscript" style="background-color: #fd7827;">
													<p class="subscriptspan">公告</p>
												</div>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</div>
					</div>
					<div class="tab-pane fade in" id="active-actives">
						<div class="row form-group" style="">
							<c:forEach items="${activeList}" var="item"  end="5">
								<div class="col-md-4">
									<div class="con pointer" style="background-image: url('${item.photo}');" onclick="location.href='<%=request.getContextPath()%>/community/article/${item.id}'">
										<div class="subscript" style="background-color: #27a5fd;">
											<p class="subscriptspan">活动</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="tab-pane fade" id="active-news">
						<div class="row form-group" style="">
							<c:forEach end="5" items="${newsList}" var="item">
								<div class="col-md-4">
									<div class="con pointer" style="background-image: url('${item.photo}');" onclick="location.href='<%=request.getContextPath()%>/community/article/${item.id}'">
										<div class="subscript" style="background-color: #16d324;">
											<p class="subscriptspan">新闻</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="tab-pane fade" id="active-notices">
						<div class="row form-group" style="">
							<c:forEach end="5" items="${noticeList}" var="item">
								<div class="col-md-4">
									<div class="con pointer" style="background-image: url('${item.photo}');" onclick="location.href='<%=request.getContextPath()%>/community/article/${item.id}'">
										<div class="subscript" style="background-color: #fd7827;">
											<p class="subscriptspan">公告</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="foot-banner">
			<div class="foot-banner-son container">
				<div class="center-block" style="margin-top: 60px; width: 700px">
					<div class="foot-banner-left pull-left">
						<img src="<%=request.getContextPath()%>/resources/images/dl_art.png" alt="">
					</div>
					<div class="foot-banner-right">
						<p style="font-size: 24px; font-weight: bold;">iFSim客户端下载</p>
						<p style="font-size: 16px; margin-top: 14px;">连飞细节完美同步，包含近60种飞行状态</p>
						<p style="font-size: 16px; margin-top: 10px;">提供落地曲线记录功能</p>
						<p style="font-size: 14px; margin-top: 20px;">
							大小13.7MB
							<span style="margin-left: 12px">版本V1.031</span>
						</p>
						<p style="font-size: 14px; margin-top: 10px;">适应系统：XP/vista/win7/win8/win10</p>
						<p style="font-size: 14px; margin-top: 10px;">更新时间：2017.12.26</p>
						<p>
							<button class="foot-banner-right-button-l">飞行员客户端</button>
							<button class="foot-banner-right-button-r">管制员客户端</button>
						</p>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<c:import url="common/foot.jsp"></c:import>
	</div>
</body>
</html>