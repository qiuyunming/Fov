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
<title>iFSim-我的收藏</title>
<c:import url="../common/res.jsp"></c:import>
<style type="text/css">
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

button {
	margin: 0 5px 0 5px !important;
}
</style>

</head>
<body>
	<div class="main-wapper">
		<c:import url="../common/head.jsp">
			<c:param name="index" value="10"></c:param>
		</c:import>
		<div>
			<div class="panel-search">
				<div class="container" style="height: 100%">
					<div class="wapper-search pull-right">
						<form class="wapper-search pull-right" id="form_search_article" action="<%=request.getContextPath()%>/search" method="GET">
							<input class="input-search" type="text" placeholder="在此输入关键字搜索文章" name="keyStr" value="${keyStr}">
							<div>
								<i class="icon-search" style="color: #666; margin-left: 8px; cursor: pointer;" onclick="form_search_article.submit()"></i>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<c:import url="../common/user_navbar.jsp">
				<c:param name="index" value="2"></c:param>
			</c:import>
			<div id="myTabContent" class="col-md-9 tab-content">
				<div class="tab-pane fade in active" id="user-article">
					<div>
						<c:if test="${empty collections }">暂无收藏</c:if>
						<c:forEach items="${collections}" var="item">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div>
										<h2 class="panel-title pull-left">
											<a href="<%=request.getContextPath()%>/community/article/${item.article.id}">${item.article.title}</a>
										</h2>
										<button class="button button-tiny button-box pull-right" onclick="delete_tendency(${item.articleTendency.id})">
											<i class="icon icon-trash"></i>
										</button>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="panel-body" onclick="location.href='<%=request.getContextPath()%>/community/article/${item.article.id}'" style="cursor: pointer">
									<c:if test="${item.article.photo!=''}">
										<div class="content-simple col-md-10">${item.article.contentText}</div>
										<div>
											<div class="img-cover-wapper pull-right">
												<img class="img-article-cover" alt="photo" src="${item.article.photo}">
											</div>
										</div>
									</c:if>
									<c:if test="${item.article.photo==''}">
										<div class="content-simple no-photo col-md-12">${item.article.contentText}</div>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<c:import url="../common/foot.jsp"></c:import>
	</div>
	<script type="text/javascript">

	var delete_tendency = function(id){
		$.ajax({
			url:"<%=request.getContextPath()%>/user/delete_tendency",
			type:"POST",
			data:{"id":id},
			success:function(resp){
				alert(resp.desc);
			}
		});
	}
	
	$.each($(".img-article-cover"), function(i, obj) {
		var w = obj.offsetWidth, h = obj.offsetHeight;
		w < h ? obj.style.width = '100%' : obj.style.height = '100%';
	});
</script>
</body>
</html>