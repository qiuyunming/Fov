package org.ifsim.vairline.operator.controller;

import java.util.List;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.service.IUserService;
import org.ifsim.vairline.operator.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserManagerController {

	@Autowired
	private IUserService userService;

	@Autowired
	private WebState webState;

	@RequestMapping("/user_manager")
	public String userManager() {
		return "user/user_manager";
	}

	@RequestMapping("/get_all")
	public @ResponseBody TableVO<User> getAll() {
		List<User> userList = userService.getUser(null);
		TableVO<User> tableVO = new TableVO<User>(userList);
		return tableVO;
	}
	@RequestMapping("/update")
	public @ResponseBody WebState update(User targetUser) {
		userService.updateUser(targetUser);
		return webState;
	}
	
	@RequestMapping("/del")
	public @ResponseBody WebState delete(User targerUser) {
		userService.deleteUser(targerUser);
		return webState;
	}
	
}
