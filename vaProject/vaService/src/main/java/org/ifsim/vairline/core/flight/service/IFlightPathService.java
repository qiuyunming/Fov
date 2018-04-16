package org.ifsim.vairline.core.flight.service;

import java.util.List;

import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.flight.domain.FlightPath;

/**
 * @Description: 航班Service层
 * @author shentong
 * @date 2017年11月16日 上午10:26:22
 * @version V1.0
 */

public interface IFlightPathService {

	void create(FlightPath entity);

	void delete(FlightPath entity);

	void update(FlightPath entity);

	List<FlightPath> get(FlightPath entity);

	FlightPath getById(FlightPath entity);

	/**
	 * @Description: 通过航班号获取最后一个航点
	 */
	FlightPath getLastByFlightId(Integer flightId);
	
	void createByFlightFlyingstate(FlightFlyingstate state,Integer FlightId);
}
