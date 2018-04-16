package org.ifsim.vairline.core.flight.service.impl;

import java.util.List;

import org.ifsim.vairline.common.page.Page;
import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.aircraft.service.IAircraftService;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.service.ICompanyService;
import org.ifsim.vairline.core.flight.dao.FlightDao;
import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.flight.domain.FlightPlanDto;
import org.ifsim.vairline.core.flight.service.IFlightService;
import org.ifsim.vairline.core.plan.domain.Plan;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description: 航班实现类
 * @author shentong
 * @date 2017年11月16日 上午10:28:54
 * @version V1.0
 */
@Service
@Cacheable("Flight")
public class FlightServiceImpl implements IFlightService {

	@Autowired
	private FlightDao flightDao;

	@Autowired
	private IAircraftService aircraftService;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICompanyService companyService;

	@Override
	@CacheEvict(value= {"Plan","Flight"},allEntries=true)
	public void createFlight(Flight flight) {
		flightDao.createFlight(flight);
	}

	@Override
	@CacheEvict(value= {"Plan","Flight"},allEntries=true)
	public void deleteFlight(Flight flight) {
		flightDao.deleteFlight(flight);
	}

	@Override
	@CacheEvict(value= {"Plan","Flight"},allEntries=true)
	public void updateFlight(Flight flight) {
		flightDao.updateFlight(flight);
	}

	@Override
	public List<Flight> getFlight(Flight flight) {
		return flightDao.getFlight(flight);
	}

	@Override
	public Flight getFlightById(Flight flight) {
		return flightDao.getFlightById(flight);
	}

	@Override
	public List<Flight> getFlyingFlight(Flight flight) {
		return flightDao.getFlyingFlight(flight);
	}

	@Override
	public Flight getFlightByPltKey(Integer pltkey) {
		return flightDao.getFlightByPltKey(pltkey);
	}

	@Override
	public List<FlightPlanDto> getByFlightAndPlan(Flight flight, Plan plan) {
		return flightDao.getByFlightAndPlan(flight, plan);
	}

	@Override
	public Integer getPassengerCount(Plan plan, String pilotName) {
		Aircraft targetAircraft = new Aircraft();
		targetAircraft.setModel(plan.getModel());
		targetAircraft = aircraftService.getAircraft(targetAircraft).get(0);

		User targetUser = new User();
		targetUser.setUsername(pilotName);
		targetUser = userService.getUser(targetUser).get(0);

		Company targetCompany = companyService.getByICAO(plan.getCompanyIcao());

		Integer maxPassenger = targetAircraft.getMaxPassenger();
		Integer userReputation = targetUser.getReputation();
		Integer companyReputation = targetCompany.getReputation();
		Float ticketPrice = plan.getTicketPrice();
		Float dctDistance = plan.getDctDistance();

		Integer passengerCount = maxPassenger * 1;

		return passengerCount;
	}

	@Override
	public Page<FlightPlanDto> getByFlightAndPlanAndPage(Flight flight, Plan plan, int pageNum, int pageSize) {
		Page<FlightPlanDto> page = new Page<FlightPlanDto>(pageNum, pageSize, getByFlightAndPlan(flight, plan).size());
		page.setList(flightDao.getByFlightAndPlanAndPage(flight, plan, page));
		return page;
	}

}
