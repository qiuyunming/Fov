package org.ifsim.vairline.core.flight.service;

import java.util.List;

import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.flight.domain.FlightFlyingstateResponse;

/**
 * @Description: 航班飞行状态Service层
 * @author shentong
 * @date 2017年11月16日 下午3:45:26
 * @version V1.0
 */
public interface IFlightFlyingstateService {

	/**
	 * @Description: 增
	 */
	void createFlightFlyingstate(FlightFlyingstate flightFlyingstate);

	/**
	 * @Description: 删
	 */
	void deleteFlightFlyingstate(FlightFlyingstate flightFlyingstate);

	/**
	 * @Description: 改
	 */
	void updateFlightFlyingstate(FlightFlyingstate flightFlyingstate);

	/**
	 * @Description: 根据条件查找
	 */
	List<FlightFlyingstate> getFlightFlyingstate(FlightFlyingstate flightFlyingstate);

	/**
	 * @Description: 根据id查招
	 */
	FlightFlyingstate getFlightFlyingstateById(FlightFlyingstate flightFlyingstate);

	/**
	 * @Description: 根据pltkey查询
	 */
	FlightFlyingstate getFlightFlyingstateByPltkey(int pltkey);

	/**
	 * @Description: 根据pltkey更改
	 */
	void updateFlightFlyingstateByPltkey(FlightFlyingstate flightFlyingstate);

	/**
	 * @Description: 获取所有飞行状态和对应的航班号
	 */
	List<FlightFlyingstateResponse> getFlightFlyingstateAndFlightNumber();
	
	/**
	 * @Description: 根据pltkey删除飞行状态
	 */
	void deleteFlightFlyingstateByPltKey(Integer pltkey);
	
	/** 
	* @Description: 得出超时飞行状态 
	*/
	Integer[] getOverTime(Integer maxTime);
	
	/**
	 * @Description: 删除多个
	 */
	void deleteMore(Integer ids[]);
}
