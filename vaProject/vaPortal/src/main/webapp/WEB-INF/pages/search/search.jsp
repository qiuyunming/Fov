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
<title>iFSim-搜索结果</title>
<c:import url="../common/res.jsp"></c:import>
<style type="text/css">
.content-simple {
	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 3;
	overflow: hidden;
	padding-top: 25px;
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

.key-word {
	color: #ea6f5a;
	font-style: normal;
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
								<i class="icon-search" style="color: #666; margin-left: 8px; cursor: pointer;" onclick="form_search_article.submit()"></i>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div>
			<div class="container">
				<div class="form-group">
					<h2 class="text-danger pull-left">${keyStr}</h2>
					<h2 class="pull-left">&nbsp;的搜索结果</h2>
					<div class="clearfix"></div>
				</div>
				<div>
					<c:if test="${empty articleResult}">
						<div class="text-center">
							<h3 style="color: #666">未找到相关内容</h3>
						</div>
					</c:if>
					<c:forEach items="${articleResult}" var="item">
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
										<span class="article-author" style="width: 40px">${item.author}</span>
										<span class="">
											<small style="color: #666">
												<fmt:formatDate value="${item.createTime}" pattern="YY.MM.dd HH:mm" />
											</small>
										</span>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="panel-body">
								<c:if test="${item.photo!=''}">
									<div class="content-simple col-md-10">
										<c:out value="${item.contentText}"></c:out>
									</div>
									<div>
										<div class="img-cover-wapper pull-right">
											<img class="img-article-cover" alt="photo" src="${item.photo}">
										</div>
										<div class="clearfix"></div>
									</div>
								</c:if>
								<c:if test="${item.photo==''}">
									<div class="content-simple no-photo col-md-12">
										<c:out value="${item.contentText}"></c:out>
									</div>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$.each($(".img-article-cover"), function(i, obj) {
				var w = obj.offsetWidth, h = obj.offsetHeight;
				w < h ? obj.style.width = '100%' : obj.style.height = '100%';
			});

			var keyStrArray = "${keyStr}".split(" ");

			$.each($('.content-simple,.article-title-list,.article-author'), function(j, ele) {
				var content = ele.innerHTML;
				content = highLight(content, keyStrArray);
				ele.innerHTML = content;
			});

			function highLight(str, keywordArray) {
				var array = keywordArray;
				for (var i = 0; i < array.length; i++) {
					if (str.indexOf(array[i]) > -1) {
						var a = new RegExp('(' + array[i] + ')', "gi");
						str = str.replace(a, ("<em class='key-word'>$1</em>"));
					}
				}
				return str;
			}
		});
	</script>
</body>
</html>