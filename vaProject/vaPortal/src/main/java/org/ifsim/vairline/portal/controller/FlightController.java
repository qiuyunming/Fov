package org.ifsim.vairline.portal.controller;

import java.util.List;
import java.util.Map;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.common.page.Page;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;
import org.ifsim.vairline.core.company.service.ICompanyAircraftService;
import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.flight.domain.FlightLandingReport;
import org.ifsim.vairline.core.flight.domain.FlightPlanDto;
import org.ifsim.vairline.core.flight.service.IFlightLandingReportService;
import org.ifsim.vairline.core.flight.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private IFlightService flightService;

	@Autowired
	private IFlightLandingReportService flightLandingReportService;

	@Autowired
	private ICompanyAircraftService companyAircraftService;

	@Autowired
	WebState webState;

	/**
	 * @Description: 放弃航班
	 */
	@RequestMapping("/abandon")
	public @ResponseBody WebState abandon(Flight flight) {

		// 获取对应航班
		Flight targetFlight = flightService.getFlightById(flight);
		// 如果航班为已完成、坠毁、放弃则不改变状态
		if (targetFlight.getFlightState() != 12 && targetFlight.getFlightState() != 13
				&& targetFlight.getFlightState() != 14) {
			targetFlight.setFlightState(13);
			flightService.updateFlight(targetFlight);

			// 设置飞机状态（以后还要考虑飞机损耗）
			CompanyAircraft targetCompanyAircraft = new CompanyAircraft();
			targetCompanyAircraft.setId(targetFlight.getCompanyAircraftId());
			targetCompanyAircraft.setIsFlying(false);
			companyAircraftService.updateCompanyAircraft(targetCompanyAircraft);

			webState.setDesc("放弃成功");
		} else {
			webState.setDesc("放弃失败");
		}
		return webState;
	}

	/**
	 * @Description: 获取航班详细信息
	 */
	@RequestMapping("/flight_detail/{flightId}")
	public String FlightDetail(@PathVariable(value = "flightId") Integer flightId, Map<String, Object> view) {
		FlightLandingReport flightLandingReportEntity = new FlightLandingReport();
		flightLandingReportEntity.setFlightId(flightId);
		flightLandingReportEntity.setOnGround(1);
		List<FlightLandingReport> flightLandingReportList = flightLandingReportService
				.getFlightLandingReport(flightLandingReportEntity);
		if (!flightLandingReportList.isEmpty()) {
			view.put("report", flightLandingReportList.get(0));
		}

		return "flight/flight_detail";
	}

	/**
	 * @Description: 所有航班
	 */
	@RequestMapping("/all_flight/{pageNum}")
	public String allFlight(@PathVariable Integer pageNum, Map<String, Object> view) {

		Page<FlightPlanDto> page = flightService.getByFlightAndPlanAndPage(null, null, pageNum, 15);
		view.put("page", page);
		return "flight/all_flight";
	}

}
