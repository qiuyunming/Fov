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
<title>iFSim-社区</title>
<c:import url="../common/res.jsp"></c:import>
<style type="text/css">
.user-rank-list>.user-rank-list-item {
	height: 50px;
	margin-bottom: 6px;
	border-radius: 4px;
	background-color: #f6f6f6;
	list-style: none;
	padding-top: 10px
}

.content-simple {
	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 3;
	overflow: hidden;
	padding-top: 25px;
	max-height: 140px;
	text-overflow: ellipsis;
}

.content-simple.no-photo {
	padding-top: 0px;
}

.img-cover-wapper {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 150px;
	height: 120px;
	background-color: #fff;
	margin-bottom: 5px;
	overflow: hidden;
}

.img-article-cover {
	border-radius: 4px;
	border: 1px solid #f0f0f0;
}

.article-title-list {
	font-size: 18px;
	font-weight: 500;
}
</style>
</head>
<body>
	<div class="main-wapper">
		<c:import url="../common/head.jsp"></c:import>
		<div>
			<div class="panel-search">
				<div class="container" style="height: 100%">
					<div class="wapper-search pull-right">
						<form class="wapper-search pull-right" id="form_search_article" action="<%=request.getContextPath()%>/search" method="GET">
							<input class="input-search" type="text" placeholder="在此输入关键字搜索文章" name="keyStr" value="${keyStr}">
							<div>
								<i class="icon-search" style="color: #666; margin-left: 8px;cursor: pointer;" onclick="form_search_article.submit()"></i>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="left col-md-8">
				<div id="myCarousel" class="carousel slide center-block form-group" style="width: 625px">

					<!-- 轮播（Carousel）指标 -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<!-- 轮播（Carousel）项目 -->
					<div class="carousel-inner" style="height: 270px">
						<div class="item active">
							<img src="<%=request.getContextPath()%>/resources/images/c.jpg">
						</div>
						<div class="item">
							<img src="<%=request.getContextPath()%>/resources/images/a.jpg">
						</div>
						<div class="item">
							<img src="<%=request.getContextPath()%>/resources/images/b.jpg">
						</div>
					</div>
					<!-- 轮播（Carousel）导航 -->
					<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
				</div>
				<div class="">
					<div class="text-center">
						<div class="button-group form-group">
							<c:forEach items="${typeList}" var="item">
								<button class="button button-blue" onclick="location.href='<%=request.getContextPath()%>/community/${item.id}/1'">${item.title}</button>
							</c:forEach>
						</div>
					</div>
					<div>
						<c:forEach items="${articleList}" var="item">
							<div class="panel panel-default" onclick="location.href='<%=request.getContextPath()%>/community/article/${item.id}'" style="cursor: pointer;">
								<div class="panel-heading">
									<div>
										<h2 class="panel-title pull-left" style="line-height: 33px">
											<a class="article-title-list" href="#">${item.title}</a>
										</h2>
										<div class="panel-title pull-right" style="line-height: 33px">
											<div class="img-head-wapper tiny pull-left" style="margin-right: 10px">
												<img class="img-head" alt="img" src="${item.head}">
											</div>
											<span class="" style="width: 40px">${item.author}</span>
											<span class="">
												<small style="color: #666">
													<fmt:formatDate value="${item.createTime}" pattern="MM.dd HH:mm" />
												</small>
											</span>
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="panel-body">
									<c:if test="${item.photo!=''}">
										<div class="content-simple col-md-9">${item.contentText}</div>
										<div>
											<div class="img-cover-wapper pull-right">
												<img class="img-article-cover" alt="photo" src="${item.photo}">
											</div>
										</div>
									</c:if>
									<c:if test="${item.photo==''}">
										<div class="content-simple no-photo col-md-12">${item.contentText}</div>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>

				</div>
			</div>

			<div class="col-md-4">
				<!-- <iframe src="https://www.ventusky.com/?p=36;102;3&amp;l=wind&amp;m=icon" frameborder="" name="" width="80%" height="900px" scrolling="yes"></iframe> -->
				<div class="user-rank-list">
					<blockquote>
						TOP 5
						<small>飞行员</small>
					</blockquote>
					<c:forEach items="${userRankList}" var="item" varStatus="status">
						<div class="user-rank-list-item">
							<h4 class="col-md-1">${status.index+1}</h4>
							<div class="col-md-5" style="padding-top: 0px; line-height: 33px">
								<div class="img-head-wapper tiny col-xs-5">
									<img class="img-head" alt="${item.username}" src="${item.photo}">
								</div>
								<span class="col-xs-5">
									<a href="javascript:void(0)">${item.username}</a>
								</span>
							</div>
							<h5 class="col-md-4">
								<fmt:formatNumber type="number" value="${item.sumTime/3600000}" pattern="0.0小时" maxFractionDigits="2" />
							</h5>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<c:import url="../common/foot.jsp"></c:import>
	</div>
	<script type="text/javascript">
		$.each($(".img-article-cover"), function(i, obj) {
			var w = obj.offsetWidth, h = obj.offsetHeight;
			w < h ? obj.style.width = '100%' : obj.style.height = '100%';
		});
	</script>
</body>
</html>