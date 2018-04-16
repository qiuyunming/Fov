package org.ifsim.vairline.core.flight.service;

import java.util.List;

import org.ifsim.vairline.core.flight.domain.FlightLandingReport;

/**   
* @Description: 落地报告service层
* @author shentong  
* @date 2017年11月23日 下午5:08:00 
* @version V1.0   
*/
public interface IFlightLandingReportService {
	/**
	 * @Description: 增
	 */
	void createFlightLandingReport(FlightLandingReport flightLandingReport);

	/**
	 * @Description: 删
	 */
	void deleteFlightLandingReport(FlightLandingReport flightLandingReport);

	/**
	 * @Description: 改
	 */
	void updateFlightLandingReport(FlightLandingReport flightLandingReport);

	/**
	 * @Description: 根据条件查询
	 */
	List<FlightLandingReport> getFlightLandingReport(FlightLandingReport flightLandingReport);

	/**
	 * @Description: 根据id查询
	 */
	FlightLandingReport getFlightLandingReportById(FlightLandingReport flightLandingReport);
	
	/** 
	* @Description: 根据 
	*/
	Integer getCountByFlightId(Integer FlightId);
}
