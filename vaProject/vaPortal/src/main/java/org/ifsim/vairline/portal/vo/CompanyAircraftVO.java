package org.ifsim.vairline.portal.vo;

import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;

/**
 * @Description: 公司飞机VO
 * @author shentong
 * @date 2017年12月20日 上午11:13:20
 * @version V1.0
 */
public class CompanyAircraftVO {

	/**
	 * @Description: 公司飞机
	 */
	private CompanyAircraft companyAircraft;

	/**
	 * @Description: 飞机
	 */
	private Aircraft aircraft;

	public CompanyAircraft getCompanyAircraft() {
		return companyAircraft;
	}

	public void setCompanyAircraft(CompanyAircraft companyAircraft) {
		this.companyAircraft = companyAircraft;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	@Override
	public String toString() {
		return "CompanyAircraftVO [companyAircraft=" + companyAircraft + ", aircraft=" + aircraft + "]";
	}

}
