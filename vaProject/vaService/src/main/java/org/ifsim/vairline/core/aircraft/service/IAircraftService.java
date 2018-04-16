package org.ifsim.vairline.core.aircraft.service;

import java.util.List;

import org.ifsim.vairline.core.aircraft.domain.Aircraft;

public interface IAircraftService {
	
	/**
	 * @Description: 增
	 */
	void createAircraft(Aircraft aircraft);

	/**
	 * @Description: 删
	 */
	void deleteAircraft(Aircraft aircraft);

	/**
	 * @Description: 改
	 */
	void updateAircraft(Aircraft aircraft);

	/**
	 * @Description: 根据条件查询
	 */
	List<Aircraft> getAircraft(Aircraft aircraft);

	/**
	 * @Description: 根据id查询
	 */
	Aircraft getAircraftById(Aircraft aircraft);
	
	/**
	 * @Description: 根据机模名称模糊查询
	 */
	List<Aircraft> getLikeModel(String model);
}
