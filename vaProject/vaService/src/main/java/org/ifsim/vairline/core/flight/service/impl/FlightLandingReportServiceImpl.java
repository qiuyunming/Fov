package org.ifsim.vairline.core.flight.service.impl;

import java.util.List;

import org.ifsim.vairline.core.flight.dao.FlightLandingReportDao;
import org.ifsim.vairline.core.flight.domain.FlightLandingReport;
import org.ifsim.vairline.core.flight.service.IFlightLandingReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("FlightLandingReport")
public class FlightLandingReportServiceImpl implements IFlightLandingReportService {
	@Autowired
	private FlightLandingReportDao flightLandingReportDao;

	@Override
	@CacheEvict(value="FlightLandingReport",allEntries=true)
	public void createFlightLandingReport(FlightLandingReport flightLandingReport) {
		flightLandingReportDao.createFlightLandingReport(flightLandingReport);
	}

	@Override
	@CacheEvict(value="FlightLandingReport",allEntries=true)
	public void deleteFlightLandingReport(FlightLandingReport flightLandingReport) {
		flightLandingReportDao.deleteFlightLandingReport(flightLandingReport);
	}

	@Override
	@CacheEvict(value="FlightLandingReport",allEntries=true)
	public void updateFlightLandingReport(FlightLandingReport flightLandingReport) {
		flightLandingReportDao.updateFlightLandingReport(flightLandingReport);
	}

	@Override
	public List<FlightLandingReport> getFlightLandingReport(FlightLandingReport flightLandingReport) {
		return flightLandingReportDao.getFlightLandingReport(flightLandingReport);
	}

	@Override
	public FlightLandingReport getFlightLandingReportById(FlightLandingReport flightLandingReport) {
		return flightLandingReportDao.getFlightLandingReportById(flightLandingReport);

	}

	@Override
	public Integer getCountByFlightId(Integer FlightId) {
		return flightLandingReportDao.getCountByFlightId(FlightId);
	}

}
