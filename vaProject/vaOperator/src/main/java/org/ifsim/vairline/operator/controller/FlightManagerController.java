package org.ifsim.vairline.operator.controller;

import java.util.List;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.flight.service.IFlightService;
import org.ifsim.vairline.operator.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/flight")
public class FlightManagerController {

	@Autowired
	private IFlightService flightService;

	@Autowired
	private WebState webState;

	@RequestMapping("/flight_manager")
	public String flightManager() {
		return "flight/flight_manager";
	}

	@RequestMapping("/get_all")
	public @ResponseBody TableVO<Flight> getAll() {
		List<Flight> flightList = flightService.getFlight(null);
		TableVO<Flight> tableVO = new TableVO<Flight>(flightList);
		return tableVO;
	}
	@RequestMapping("/update")
	public @ResponseBody WebState update(Flight targetFlight) {
		flightService.updateFlight(targetFlight);
		return webState;
	}
	
	@RequestMapping("/del")
	public @ResponseBody WebState delete(Flight targerFlight) {
		flightService.deleteFlight(targerFlight);
		return webState;
	}
	
}
