package org.ifsim.vairline.operator.controller;

import java.util.List;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.aircraft.service.IAircraftService;
import org.ifsim.vairline.operator.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aircraft")
public class AircraftManagerController {

	@Autowired
	private IAircraftService aircraftService;

	@Autowired
	private WebState webState;

	@RequestMapping("/aircraft_manager")
	public String aircraftManager() {
		return "aircraft/aircraft_manager";
	}

	@RequestMapping("/get_all")
	public @ResponseBody TableVO<Aircraft> getAll() {
		List<Aircraft> aircraftList = aircraftService.getAircraft(null);
		TableVO<Aircraft> tableVO = new TableVO<Aircraft>(aircraftList);
		return tableVO;
	}

	@RequestMapping("/update")
	public @ResponseBody WebState update(Aircraft targetAircraft) {
		aircraftService.updateAircraft(targetAircraft);
		return webState;
	}

	@RequestMapping("/del")
	public @ResponseBody WebState delete(Aircraft targetAircraft) {
		aircraftService.deleteAircraft(targetAircraft);
		return webState;
	}

}
