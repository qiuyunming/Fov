package org.ifsim.vairline.core.flight.service;

import java.util.List;

import org.ifsim.vairline.common.page.Page;
import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.flight.domain.FlightPlanDto;
import org.ifsim.vairline.core.plan.domain.Plan;

/**
 * @Description: 航班Service层
 * @author shentong
 * @date 2017年11月16日 上午10:26:22
 * @version V1.0
 */

public interface IFlightService {

	/**
	 * @Description: 增
	 */
	void createFlight(Flight flight);

	/**
	 * @Description: 删
	 */
	void deleteFlight(Flight flight);

	/**
	 * @Description: 改
	 */
	void updateFlight(Flight flight);

	/**
	 * @Description: 根据条件查询
	 */
	List<Flight> getFlight(Flight flight);

	/**
	 * @Description: 根据id查询
	 */
	Flight getFlightById(Flight flight);

	/**
	 * @Description: 获取正在活动的航班
	 */
	List<Flight> getFlyingFlight(Flight flight);

	/**
	 * @Description: 根据pltkey获取航班
	 */
	Flight getFlightByPltKey(Integer pltkey);

	/**
	 * @Description: 根据航班和计划查找
	 */
	List<FlightPlanDto> getByFlightAndPlan(Flight flight, Plan plan);

	/**
	 * @Description: 根据航班和计划查找
	 */
	Page<FlightPlanDto> getByFlightAndPlanAndPage(Flight flight, Plan plan, int pageNum, int pageSize);

	/**
	 * @Description: 通过计划和飞行员名称获取乘客数
	 */
	Integer getPassengerCount(Plan plan, String pilotName);

}
