<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>iFSim-${type.title}</title>
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
</style>
</head>
<body>
	<div class="main-wapper">
		<c:import url="../common/head.jsp">
			<c:param name="index" value="6"></c:param>
		</c:import>
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
		<div>

			<div class="container">
				<h2>${type.title}</h2>
				<div class="row form-group">
					<a class="button pull-right" href="<%=request.getContextPath()%>/community/writer/${type.id}" style="margin-right: 15px">写文章</a>
					<div class="clearfix"></div>
				</div>
				<c:forEach items="${page.list}" var="item">
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
											<fmt:formatDate value="${item.createTime}" pattern="YY.MM.dd HH:mm" />
										</small>
									</span>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="panel-body">
							<c:if test="${item.photo!=''}">
								<div class="content-simple col-md-10">${item.contentText}</div>
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
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<c:if test="${page.pageNum-1<=0}">
							<li class="disabled">
								<a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:if test="${page.pageNum-1>0}">
							<li>
								<a href="<%=request.getContextPath()%>/community/${type.id}/${page.pageNum-1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:forEach begin="${page.start}" end="${page.end}" var="i">
							<c:if test="${i == page.pageNum}">
								<li class="active">
									<a href="#">${i}</a>
								</li>
							</c:if>
							<c:if test="${i != page.pageNum}">
								<li>
									<a href="<%=request.getContextPath()%>/community/${type.id}/${i}">${i}</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${page.totalPage==0}">
							<li class="active">
								<a href="#">1</a>
							</li>
						</c:if>
						<c:if test="${page.pageNum+1 > page.totalPage}">
							<li class="disabled">
								<a href="#" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<c:if test="${page.pageNum+1 <= page.totalPage}">
							<li>
								<a href="<%=request.getContextPath()%>/community/${type.id}/${page.pageNum+1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
					</ul>
				</nav>
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