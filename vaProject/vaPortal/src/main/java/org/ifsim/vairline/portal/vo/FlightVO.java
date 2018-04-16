package org.ifsim.vairline.portal.vo;

import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.plan.domain.Plan;

/**   
* @Description: 飞机
* @author shentong  
* @date 2017年12月11日 下午4:35:26 
* @version V1.0   
*/
public class FlightVO {

	/**
	 * @Description: 飞行状态
	 */
	private FlightFlyingstate flightFlyingstate;

	/**
	 * @Description: 飞行计划
	 */
	private Plan plan;

	public FlightFlyingstate getFlightFlyingstate() {
		return flightFlyingstate;
	}

	public void setFlightFlyingstate(FlightFlyingstate flightFlyingstate) {
		this.flightFlyingstate = flightFlyingstate;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "FlightVO [flightFlyingstate=" + flightFlyingstate + ", plan=" + plan + "]";
	}
	
	

}
