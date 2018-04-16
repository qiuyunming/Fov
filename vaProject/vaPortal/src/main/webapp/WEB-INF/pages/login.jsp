<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<c:import url="./common/res.jsp"></c:import>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/cloud/ThreeWebGL.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/cloud/ThreeExtras.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/cloud/Detector.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/cloud/RequestAnimationFrame.js"></script>
<title>iFSim-登录</title>
<style type="text/css">
body {
	background-color: #326696;
	margin: 0px;
	overflow: hidden;
	font-size: 16px;
}

.wrapper-login {
	width: 300px;
}

.wrapper-logo {
	margin-top: 60px;
}

.line {
	display: block;
	width: 100px;
	height: 1px;
	background-color: #fff;
}

.title-login {
	margin-top: 60px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	color: #fff;
	font-size: 16px;
	font-family: "微软雅黑"
}

.input-group {
	width: 100%;
}

.input-group .input-sub-group {
	width: 100%;
	height: 49px;
	background-color: #fff;
	padding: 12px 15px 15px 15px;
}

.input-group .input-sub-group input {
	outline: none;
	border: 0;
	float: left;
	width: 237px;
	color: #666;
	font-family: "微软雅黑";
	font-size: 16px;
}

.input-group .input-sub-group:first-child {
	width: 100%;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.input-group .input-sub-group:last-child {
	width: 100%;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
	border-top: 1px solid #c9c9c9;
}

.input-group .input-sub-group:not (:first-child ):not (:last-child ){
	border-top: 1px solid #c9c9c9;
}

.tooltip-circle {
	float: right;
	display: block;
	width: 18px;
	height: 18px;
	border-radius: 50%;
	color: #fff;
	line-height: 20px;
	text-align: center;
}

.tooltip-circle.green {
	background-color: #45be89;
}

.tooltip-circle.red {
	background-color: #d81e06;
}

input::-webkit-input-placeholder {
	color: #c9c9c9;
}

::-moz-placeholder { /* Mozilla Firefox 19+ */
	color: #c9c9c9;
}

input:-ms-input-placeholder {
	color: #c9c9c9;
}

.wrapper-checkbox {
	position: relative;
	width: 14px;
	height: 14px;
	border: 1px solid #bebebe;
	border-radius: 2px;
	cursor: pointer;
	color: #27a5fd;
}

input[type="checkbox"] {
	position: absolute;
	opacity: 0;
	width: 100%;
	height: 100%;
	margin: 0;
	cursor: pointer;
	z-index: 1999;
}

.wrapper-checkbox span {
	position: absolute;
	top: -2px;
}

input[type="checkbox"]:checked+[class*=" icon-"] {
	visibility: visible;
}

.button-large {
	display: block;
	width: 100%;
	height: 42px;
	background-color: #27a5fd;
	color: #fff;
	border: 0;
	border-radius: 6px;
	width: 100%;
	text-align: center;
	text-decoration: none;
	line-height: 42px;
}

.button-large:hover, .button-large:visited {
	text-decoration: none;
}

.small {
	font-size: 12px;
}

</style>
<script id="vs" type="x-shader/x-vertex">

	varying vec2 vUv;

	void main() {

		vUv = uv;
		gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );

	}

</script>
<script id="fs" type="x-shader/x-fragment">

	uniform sampler2D map;

	uniform vec3 fogColor;
	uniform float fogNear;
	uniform float fogFar;

	varying vec2 vUv;

	void main() {

		float depth = gl_FragCoord.z / gl_FragCoord.w;
		float fogFactor = smoothstep( fogNear, fogFar, depth );

		gl_FragColor = texture2D( map, vUv );
		gl_FragColor.w *= pow( gl_FragCoord.z, 20.0 );
		gl_FragColor = mix( gl_FragColor, vec4( fogColor, gl_FragColor.w ), fogFactor );

	}
</script>
</head>
<body>
	<div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/cloud/cloud.js"></script>

		<!-- 美化后 -->
		<div style="position: absolute; left: 0; top: 0; width: 100%">
			<div class="wrapper-login center-block">
				<div class="wrapper-logo text-center">
					<img src="<%=request.getContextPath()%>/resources/images/logo_header.png" alt="">
				</div>
				<div class="title-login">
					<span class="line"></span>
					<span>用户登录</span>
					<span class="line"></span>
				</div>
				<form class="form-login" action="<%=request.getContextPath()%>/auth/doLogin" method="post" style="width: 100%; margin-top: 20px;">
					<div class="input-group">
						<div class="input-sub-group">
							<input class="input-login input-username" type="text" placeholder="呼号/手机号" name="username">
						</div>
						<div class="input-sub-group">
							<input class="input-login input-password" type="password" placeholder="密码" name="password">
						</div>
					</div>
					<div style="margin-top: 8px">
						<div class="wrapper-checkbox pull-left">
							<input type="checkbox" name="rememberMe" checked="checked">
							<span class="icon-ok icon-ok invisible"></span>
						</div>
						<div class="pull-left small" style="color: #fff; margin-left: 8px;">记住我</div>
					</div>
					<div class="clearfix" style="margin-top: 10px;"></div>
					<div class="tooltip-text text-danger text-center small">info</div>
					<a class="button-large btn-submit" href="#" style="margin-top: 10px;">登录</a>
					<div class="text-center" style="color: #fff; margin-top: 15px;">
						还没有有账户？立即
						<a href="<%=request.getContextPath()%>/auth/regist">注册</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(".tooltip-text").hide();
		$(".btn-submit").on("click", function(e) {
			$(".tooltip-text").hide();
			var username = $(".input-username").val();
			var reg = /^\d{11}$/;
			var reg1 = /^\d{4}$/;
			
			if ((!reg1.test(username)&&!reg.test(username)) && username!="root") {
				$(".tooltip-text").fadeIn();
				$(".tooltip-text").text("请输入正确的呼号或手机");
				return;
			}
			
			if(reg1.test(username)||reg.test(username) || username=="root"){
				if ($(".input-username").val() && $(".input-password").val()) {
					dologin();
				} else {
					$(".tooltip-text").fadeIn();
					$(".tooltip-text").text("请输入完整登录信息");
				}
			}
			
		});
		
		var dologin = function(){
			$(".form-login").ajaxSubmit({
				url : "<%=request.getContextPath()%>/auth/doLogin",
				type : "post",
				success : function(resp) {
					if (resp.code == "401") {
						$(".tooltip-text").fadeIn();
						$(".tooltip-text").text(resp.desc);
					} else if(resp.code == "200"){
						location.href='<%=request.getContextPath()%>/';
					}
				}
			});
		}
	</script>
</body>
</html>