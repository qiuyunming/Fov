package org.ifsim.vairline.core.flight.dao;

import java.util.List;

import org.ifsim.vairline.core.flight.domain.FlightPath;

/**
 * @Description: 航班Dao层
 * @author shentong
 * @date 2017年11月16日 上午9:44:50
 * @version V1.0
 */
public interface FlightPathDao {

	void create(FlightPath entity);

	void delete(FlightPath entity);

	void update(FlightPath entity);

	List<FlightPath> get(FlightPath entity);

	FlightPath getById(FlightPath entity);

	/**
	 * @Description: 通过航班号获取最后一个航点
	 */
	FlightPath getLastByFlightId(Integer flightId);

}
