package org.ifsim.vairline.core.plan.service.impl;

import java.util.List;

import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.plan.dao.PlanDao;
import org.ifsim.vairline.core.plan.domain.Plan;
import org.ifsim.vairline.core.plan.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("Plan")
public class PlanServiceImpl implements IPlanService {

	@Autowired
	private PlanDao planDao;

	@Override
	@CacheEvict(value= {"Plan","Flight"},allEntries=true)
	public void createPlan(Plan plan) {
		planDao.createPlan(plan);
	}

	@Override
	@CacheEvict(value= {"Plan","Flight"},allEntries=true)
	public void deletePlan(Plan plan) {
		planDao.deletePlan(plan);
	}

	@Override
	@CacheEvict(value= {"Plan","Flight"},allEntries=true)
	public void updatePlan(Plan plan) {
		planDao.updatePlan(plan);
	}

	@Override
	public List<Plan> getPlan(Plan plan) {
		return planDao.getPlan(plan);
	}

	@Override
	public Plan getPlanById(Plan plan) {
		return planDao.getPlanById(plan);
	}

	@Override
	public List<Plan> getPlanByFlightAndPlan(Flight flight, Plan plan) {
		return planDao.getPlanByFlightAndPlan(flight, plan);
	}

}
