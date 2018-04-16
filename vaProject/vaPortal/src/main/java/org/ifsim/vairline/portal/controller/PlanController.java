package org.ifsim.vairline.portal.controller;

import java.util.List;
import java.util.Map;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;
import org.ifsim.vairline.core.company.service.ICompanyAircraftService;
import org.ifsim.vairline.core.company.service.ICompanyStaffService;
import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.flight.service.IFlightService;
import org.ifsim.vairline.core.plan.domain.Plan;
import org.ifsim.vairline.core.plan.service.IPlanService;
import org.ifsim.vairline.web.auth.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 计划Controller层
 * @author shentong
 * @date 2017年11月21日 下午4:37:39
 * @version V1.0
 */
@Controller
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	private IPlanService planService;

	@Autowired
	private IFlightService flightService;

	@Autowired
	private ICompanyStaffService companyStaffService;

	@Autowired
	private ICompanyAircraftService companyAircraftService;

	@Autowired
	private WebState webState;

	/**
	 * @Description: 任务中心
	 */
	@RequestMapping("/center")
	public String center(Map<String, Object> view) {
		Plan planEntity = new Plan();
		// 获取当前用户所属公司的任务
		planEntity.setCompanyIcao(
				companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername()).getCompanyIcao());
		List<Plan> planList = planService.getPlan(planEntity);
		view.put("planList", planList);

		// 是否可以领取，用于是否展现领取按钮
		Flight flightEntity = new Flight();
		flightEntity.setPilotUsername(CurrentUser.getUser().getUsername());
		view.put("isReceive", !flightService.getFlyingFlight(flightEntity).isEmpty());

		view.put("companyIcao", planEntity.getCompanyIcao());
		return "plan/center";
	}

	/**
	 * @Description: 领取任务
	 * @param planId
	 *            领取的任务id
	 * @param companyAircraftId
	 *            执行的公司飞机id
	 */
	@RequestMapping("/receive")
	public @ResponseBody WebState receive(Integer planId, Integer companyAircraftId) {
		// 判断当前用户是否有正在运行中的航班，若有则无法领取任务
		Flight flightEntity = new Flight();
		flightEntity.setPilotUsername(CurrentUser.getUser().getUsername());
		if (flightService.getFlyingFlight(flightEntity).isEmpty()) {

			// 公司飞机状态
			CompanyAircraft targetCompanyAircraft = new CompanyAircraft();
			targetCompanyAircraft.setId(companyAircraftId);
			targetCompanyAircraft = companyAircraftService.getCompanyAircraftById(targetCompanyAircraft);
			// 若飞机正在飞行，则不能领取（以后判断飞机寿命等）
			if (!targetCompanyAircraft.getIsFlying() && !targetCompanyAircraft.getIsSale()
					&& targetCompanyAircraft.getBrokenLevel() != 4) {
				// 新建航班
				Flight newflight = new Flight();
				newflight.setPlanId(planId);
				newflight.setCompanyAircraftId(companyAircraftId);
				newflight.setPilotUsername(CurrentUser.getUser().getUsername());
				flightService.createFlight(newflight);

				targetCompanyAircraft.setIsFlying(true);
				companyAircraftService.updateCompanyAircraft(targetCompanyAircraft);
				webState.setDesc("领取成功");
			} else {
				webState.setDesc("飞机正在执飞");
			}

		} else {
			webState.setDesc("领取失败");
		}
		return webState;
	}

	/**
	 * @Description: 创建任务
	 */
	@RequestMapping("/doCreate")
	public String doCreate(Plan plan) {
		// 设置签派员
		plan.setDispatcher(CurrentUser.getUser().getUsername());

		// 设置任务所属公司ICAO
		plan.setCompanyIcao(
				companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername()).getCompanyIcao());

		planService.createPlan(plan);
		return "redirect:/plan/center";
	}

}
