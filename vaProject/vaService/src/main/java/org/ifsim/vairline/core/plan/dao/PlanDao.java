package org.ifsim.vairline.core.plan.dao;

import java.util.List;

import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.plan.domain.Plan;

/**
 * @Description: 计划Dao层
 * @author shentong
 * @date 2017年11月21日 上午10:26:02
 * @version V1.0
 */
public interface PlanDao {

	/**
	 * @Description: 增
	 */
	void createPlan(Plan plan);

	/**
	 * @Description: 删
	 */
	void deletePlan(Plan plan);

	/**
	 * @Description: 改
	 */
	void updatePlan(Plan plan);

	/**
	 * @Description: 根据条件查询
	 */
	List<Plan> getPlan(Plan plan);

	/**
	 * @Description: 根据id查询
	 */
	Plan getPlanById(Plan plan);
	
	/** 
	* @Description: 根据航班和计划联合查询 
	*/
	List<Plan> getPlanByFlightAndPlan(Flight flight,Plan plan);
}
