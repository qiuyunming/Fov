package org.ifsim.vairline.operator.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.ifsim.vairline.core.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 用户登录
 * @author shentong
 * @date 2017年11月13日 上午11:13:10
 * @version V1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	/**
	 * @Description: 登录页面
	 */
	@RequestMapping("/login")
	public String login() {

		return "login";
	}

	/**
	 * @Description: 登录操作
	 */
	@RequestMapping("/doLogin")
	public String doLogin(User user, @RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			// 将用户名密码封装为UsernamePasswordToken 对象
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			// remember me
			token.setRememberMe(rememberMe);
			try {
				// 执行登录
				currentUser.login(token);
				// 所有认证时异常的父类
			} catch (AuthenticationException e) {
				System.out.println("登录异常：：" + e.getMessage());
				return "unauthorized";
			}
		}
		return "redirect:/index";
	}

	/**
	 * @Description: 未认证
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized() {

		return "unauthorized";
	}

	/**
	 * @Description: 登出
	 */
	@RequestMapping("/logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:login";
	}

}
