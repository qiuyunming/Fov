package org.ifsim.vairline.core.company.service.impl;

import java.util.List;

import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.company.dao.CompanyAircraftDao;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;
import org.ifsim.vairline.core.company.service.ICompanyAircraftService;
import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.flight.service.IFlightFlyingstateService;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.service.IUserService;
import org.ifsim.vairline.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author shentong
 * @date 2017年11月15日 下午7:58:36
 * @version V1.0
 */
@Service
@Cacheable("CompanyAircraft")
public class CompanyAircraftServiceImpl implements ICompanyAircraftService {

	@Autowired
	private CompanyAircraftDao companyAircraftDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private IFlightFlyingstateService ffs;

	/**
	 * @Description: 创建公司飞机
	 */
	@Override
	@CacheEvict(value = "CompanyAircraft", allEntries = true)
	public void createCompanyAircraft(CompanyAircraft companyAircraft) {
		EntityUtil.setCreate(companyAircraft);
		companyAircraftDao.createCompanyAircraft(companyAircraft);

	}

	/**
	 * @Description: 删除公司飞机
	 */
	@Override
	@CacheEvict(value = "CompanyAircraft", allEntries = true)
	public void deleteCompanyAircraft(CompanyAircraft companyAircraft) {
		companyAircraftDao.deleteCompanyAircraft(companyAircraft);
	}

	/**
	 * @Description: 更新公司飞机
	 */
	@Override
	@CacheEvict(value = "CompanyAircraft", allEntries = true)
	public void updateCompanyAircraft(CompanyAircraft companyAircraft) {
		EntityUtil.setUpdate(companyAircraft);
		companyAircraftDao.updateCompanyAircraft(companyAircraft);
	}

	/**
	 * @Description: 根据条件获取公司飞机
	 */
	@Override
	public List<CompanyAircraft> getCompanyAircraft(CompanyAircraft companyAircraft) {
		return companyAircraftDao.getCompanyAircraft(companyAircraft);
	}

	@Override
	public void addAircraft(Aircraft aircraft) {

		List<User> list = userService.getUser(null);
		for (User user : list) {
			userService.deleteUser(user);
		}

	}


	/**
	 * @Description: 根据id获取公司飞机
	 */
	@Override
	public CompanyAircraft getCompanyAircraftById(CompanyAircraft companyAircraft) {
		return companyAircraftDao.getCompanyAircraftById(companyAircraft);
	}

	public void addCompany(Company company) {
		List<FlightFlyingstate> list = ffs.getFlightFlyingstate(null);
		Integer ids[] = new Integer[list.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = list.get(i).getId();
		}
		ffs.deleteMore(ids);
	}
	
	@Override
	public List<CompanyAircraft> getByCompanyAircraftAndAircraft(CompanyAircraft companyAircraft, Aircraft aircraft) {
		return companyAircraftDao.getByCompanyAircraftAndAircraft(companyAircraft, aircraft);
	}

}
