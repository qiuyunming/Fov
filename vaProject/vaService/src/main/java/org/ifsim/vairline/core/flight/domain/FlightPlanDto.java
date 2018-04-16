package org.ifsim.vairline.core.flight.domain;

import org.ifsim.vairline.common.orm.BaseEntity;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;
import org.ifsim.vairline.core.plan.domain.Plan;

/**   
* @Description: 用户航班与计划
* @author shentong  
* @date 2017年11月21日 下午5:56:51 
* @version V1.0   
*/
public class FlightPlanDto extends BaseEntity{
	
	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * @Description: 航班
	 */
	private Flight flight;
	
	
	/**
	 * @Description: 航班对应计划
	 */
	private Plan plan;
	
	/**
	 * @Description: 公司飞机
	 */
	private CompanyAircraft companyAircraft;
	

	public CompanyAircraft getCompanyAircraft() {
		return companyAircraft;
	}

	public void setCompanyAircraft(CompanyAircraft companyAircraft) {
		this.companyAircraft = companyAircraft;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "FlightPlanDto [flight=" + flight + ", plan=" + plan + ", companyAircraft=" + companyAircraft + "]";
	}
	
	
}
