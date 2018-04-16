<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<div class="pull-left navbar-slide" style="margin: 90px 0 0 0; display: block;">
	<div class="navbar-company" url="<%=request.getContextPath()%>/company/show">
		<div class="navbar-company-main">
			<div class="nav-top">
				<div class="navbar-right-flag invisible"></div>
				<div class="nav-title">所有公司</div>
				<div class="navbar-right-flag"></div>
			</div>
			<div class="nav-logo"></div>
			<div class="nav-buttom-flag"></div>
		</div>
	</div>
	<shiro:hasAnyRoles name="飞行员,签派员,副经理,经理,董事">
		<div class="navbar-company" url = "<%=request.getContextPath()%>/plan/center" >
			<div class="navbar-company-main">
				<div class="nav-top">
					<div class="navbar-right-flag invisible"></div>
					<div class="nav-title">任务中心</div>
					<div class="navbar-right-flag"></div>
				</div>
				<div class="nav-logo"></div>
				<div class="nav-buttom-flag"></div>
			</div>
		</div>
	</shiro:hasAnyRoles>
	<shiro:hasAnyRoles name="副经理,经理,董事">
		<div class="navbar-company" >
			<div class="navbar-company-main">
				<div class="nav-top">
					<div class="navbar-right-flag invisible"></div>
					<div class="nav-title">公司管理</div>
					<div class="navbar-right-flag"></div>
				</div>
				<div class="nav-logo"></div>
				<div class="nav-buttom-flag"></div>
			</div>
			<div>
				<ul class="navbar-sub text-center">
					<li class="active" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/company/staff_mannager'">人员管理</li>
					<li>财务管理</li>
					<li>机库管理</li>
				</ul>
			</div>
		</div>
	</shiro:hasAnyRoles>
	<script>
		$(".navbar-company").on('click', function(event) {
			var url = $(this).attr("url");
			if(url){
				location.href = url;
			}
		});
		$(".navbar-company").removeClass("active");
		$(".navbar-company:eq(${param.company_navbar_index})").addClass("active");
		$(".navbar-company:eq(${param.company_navbar_index})").find('.navbar-sub').show();
		
		 $(".navbar-company").hover(function(e) {
		        $(".navbar-slide .navbar-company").removeClass('active');
		        $(this).addClass('active');
		        $(this).find('.navbar-sub').slideDown();
		  },function(e){
			  $(this).find('.navbar-sub').slideUp();
		  });
		 
		 $(".navbar-company").on("click",function(e) {
		        $(".navbar-slide .navbar-company").removeClass('active');
		        $(this).addClass('active');
		        $(this).find('.navbar-sub').slideDown();
		  });
	</script>
</div>