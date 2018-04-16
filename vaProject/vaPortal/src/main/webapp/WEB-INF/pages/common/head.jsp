<%@page import="org.ifsim.vairline.core.article.service.impl.ArticleTendencyServiceImpl"%>
<%@page import="org.ifsim.vairline.core.article.service.IArticleTendencyService"%>
<%@page import="org.ifsim.vairline.core.article.domain.ArticleTendency"%>
<%@page import="org.ifsim.vairline.web.auth.CurrentUser"%>
<%@page import="org.ifsim.vairline.core.article.domain.ArticleComment"%>
<%@page import="org.ifsim.vairline.core.article.service.impl.ArticleCommentServiceImpl"%>
<%@page import="org.ifsim.vairline.core.article.service.IArticleCommentService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<jsp:directive.page import="org.springframework.web.context.WebApplicationContext" />
<%
	Integer allOfCount = 0;
	if (CurrentUser.getUser() != null) {
		WebApplicationContext context = (WebApplicationContext) this.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		IArticleCommentService articleCommentService = (IArticleCommentService) context
				.getBean("articleCommentServiceImpl");
		IArticleTendencyService articleTendencyService = (IArticleTendencyService) context
				.getBean("articleTendencyServiceImpl");
		ArticleComment articleComment = new ArticleComment();
		articleComment.setTargetUsername(CurrentUser.getUser().getUsername());
		articleComment.setIsViewed(false);
		Integer countOfComment = articleCommentService.getCount(articleComment);

		ArticleTendency tendencyEntity = new ArticleTendency();
		tendencyEntity.setTargetUsername(CurrentUser.getUser().getUsername());
		tendencyEntity.setType(0);
		tendencyEntity.setIsViewed(false);
		Integer couneOfLiking = articleTendencyService.getCount(tendencyEntity);

		allOfCount = countOfComment + couneOfLiking;
	}
%>
<div class="head-nav-bar">
	<div class="container" style="height: 100%">
		<div class="pull-left logo_wapper">
			<img src="<%=request.getContextPath()%>/resources/images/logo_header.png" alt="">
		</div>
		<div class="nav-bar-list pull-right">
			<ul>
				<li>
					<a href="<%=request.getContextPath()%>/">主页</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/map/show">天巡地图</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/company/show">航空公司</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/flight/all_flight/1">数据检索</a>
				</li>
				<li>
					<a href="#">体验中心</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/community/center">飞友社区</a>
				</li>
			</ul>
			<div class="pull-right" style="height: 100%">
				<shiro:guest>
					<div class="pull-right button-group">
						<a href="<%=request.getContextPath()%>/auth/login" type="button" class="head-button">登录</a>
						<a href="<%=request.getContextPath()%>/auth/regist" type="button" class="head-button active">注册</a>
					</div>
				</shiro:guest>
				<shiro:user>
					<div class="pull-left" style="margin: 20px 22px 0 0;">
						<a class="icon-bell-alt icon-large white" href="<%=request.getContextPath()%>/user/message" style="position: relative;">
							<%
								if (allOfCount > 0) {
							%>
							<i class="msg_remind"></i>
							<%
								}
							%>
						</a>
					</div>
					<div class="pull-right wrapper-dropdown-panel">
						<div class="img-head-wapper small" style="margin: 6px 0 0 0; cursor: pointer;">
							<img class="img-head" alt='<shiro:principal property="username"></shiro:principal>' src="<shiro:principal property="photo"></shiro:principal>" onclick="location.href='<%=request.getContextPath()%>/user/user_info'">
						</div>
						<div class="dropdown-panel">
							<div class="">
								<div class="img-head-wapper small pull-left" style="margin: 6px 0 0 0; cursor: pointer;">
									<img class="img-head" alt='<shiro:principal property="username"></shiro:principal>' src="<shiro:principal property="photo"></shiro:principal>" onclick="location.href='<%=request.getContextPath()%>/user/user_info'">
								</div>
								<div class="pull-left" style="line-height: 60px; margin-left: 15px; font-size: 16px">
									<a class="black" href="<%=request.getContextPath()%>/user/user_info">
										<shiro:principal property="username"></shiro:principal>
									</a>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="divider"></div>
							<a class="small grey" href="<%=request.getContextPath()%>/auth/logout" style="position: absolute; bottom: 15px;">安全退出</a>
						</div>
					</div>
				</shiro:user>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$.each($(".img-head"), function(i, obj) {
			var w = obj.offsetWidth, h = obj.offsetHeight;
			w < h ? obj.style.width = '100%' : obj.style.height = '100%';
		});
	});
</script>
<script>

	$(document).ready(function(){
		$(".back-to-top ").on('click', function(event) {
			scrollTo('.main-wapper');
		});

		$(".btn-start-flying").on("click", function(e) {
			scrollTo('.medium');
		});

		function scrollTo(ele) {
			$('html, body').animate({
				scrollTop : $(ele).offset().top
			}, 800);
		}

		$(window).scroll(function() {
			if ($(document).scrollTop() >= 300) {
				$(".back-to-top").fadeIn("fast");
			} else {
				$(".back-to-top").fadeOut("fast");
			}
		})
		
		//$(".head-nav-bar").pin();
	});
</script>