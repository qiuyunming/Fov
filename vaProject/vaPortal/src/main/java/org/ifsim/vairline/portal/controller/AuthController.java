package org.ifsim.vairline.portal.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.common.util.ShiroUtil;
import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.role.service.IRoleService;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.domain.UserRole;
import org.ifsim.vairline.core.user.service.IUserRoleService;
import org.ifsim.vairline.core.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 用户登录注册、角色控制
 * @author shentong
 * @date 2017年11月13日 上午11:13:10
 * @version V1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IRoleService roleSerivce;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private WebState webState;

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
	public @ResponseBody WebState doLogin(User user, @RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			// 将用户名密码封装为UsernamePasswordToken 对象
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			// remember me
			token.setRememberMe(rememberMe);
			try {
				// 执行登录
				currentUser.login(token);
				webState.setCode(200);
				webState.setDesc("登录成功");
			} catch (AuthenticationException e) {
				webState.setCode(401);
				webState.setDesc("用户或密码错误");
			}
		}else {
			webState.setCode(200);
			webState.setDesc("登录成功");
		}
		
		return webState;
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

	/**
	 * @Description: 注册页面
	 */
	@RequestMapping("/regist")
	public String regist(Map<String, Object> map) {

		Role role = new Role();
		// role.setType("website");
		// 获取网站类型的角色
		List<Role> roles = roleSerivce.getRole(role);
		map.put("roles", roles);
		return "regist";
	}

	/**
	 * @Description: 注册操作
	 */
	@RequestMapping("/doRegist")
	public @ResponseBody WebState doRegist(User user, @RequestParam(required = false) String[] roleList) {

		User userEntity = new User();
		userEntity.setUsername(user.getUsername());
		// 查询电话和呼号是否重名
		if (userService.getUser(userEntity).isEmpty()) {
			userEntity = new User();
			userEntity.setPhoneNumber(user.getPhoneNumber());
			if (userService.getUser(userEntity).isEmpty()) {
				// 设置用户密码（经过MD5加密）
				user.setPassword(ShiroUtil.getMD5BySalt(user.getPassword(), user.getUsername()));
				// 设置默认头像
				user.setPhoto("/default/all/0/2d4c132122574921840bc3f58b9cf23e.jpeg");
				
				//设置默认积分和声誉
				user.setReputation(100);
				user.setPoint(100);
				user.setPilotState(true);
				user.setRealName("用户"+user.getUsername());
				
				
				// 创建用户
				userService.createUser(user);
				// 赋予用户角色
				UserRole userRole = new UserRole();
				userRole.setUsername(user.getUsername());
				userRole.setRoleId(7);
				userRoleService.createUserRole(userRole);
				if (roleList != null) {
					for (String roleId : roleList) {
						userRole.setRoleId(Integer.valueOf(roleId));
						userRoleService.createUserRole(userRole);
					}
				}
				webState.setCode(0);
				webState.setDesc("注册成功");
			} else {
				//
				webState.setCode(1);
				webState.setDesc("该号码已存在");
			}
		} else {
			//用户名重复
			webState.setCode(2);
			webState.setDesc("呼号已存在");
		}
		return webState;
	}
}
