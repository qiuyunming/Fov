package org.ifsim.vairline.operator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OperatorController {
	@RequestMapping("/index")
	public String index() {
		return "redirect:/user/user_manager";
	}
}
