package org.ifsim.vairline.core.aircraft.dao;

import java.util.List;

import org.ifsim.vairline.core.aircraft.domain.Aircraft;

/**   
* @Description: 飞机dao层
* @author shentong  
* @date 2017年11月15日 下午2:37:44 
* @version V1.0   
*/
public interface AircraftDao {
	
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
