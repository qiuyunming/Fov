package org.ifsim.vairline.operator.controller;

import java.util.List;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.core.plan.domain.Plan;
import org.ifsim.vairline.core.plan.service.IPlanService;
import org.ifsim.vairline.operator.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/plan")
public class PlanManagerController {

	@Autowired
	private IPlanService planService;

	@Autowired
	private WebState webState;

	@RequestMapping("/plan_manager")
	public String planManager() {
		return "plan/plan_manager";
	}

	@RequestMapping("/get_all")
	public @ResponseBody TableVO<Plan> getAll() {
		List<Plan> planList = planService.getPlan(null);
		TableVO<Plan> tableVO = new TableVO<Plan>(planList);
		return tableVO;
	}
	@RequestMapping("/update")
	public @ResponseBody WebState update(Plan targetPlan) {
		planService.updatePlan(targetPlan);
		return webState;
	}
	
	@RequestMapping("/del")
	public @ResponseBody WebState delete(Plan targerPlan) {
		planService.deletePlan(targerPlan);
		return webState;
	}
	
}
