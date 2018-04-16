package org.ifsim.vairline.core.company.service.impl;

import java.util.List;

import org.ifsim.vairline.core.company.dao.CompanyDao;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description: 公司Service层
 * @author shentong
 * @date 2017年11月14日 下午3:04:47
 * @version V1.0
 */
@Service
@Cacheable("Company")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	CompanyDao companyDao;

	@Override
	@CacheEvict(value="Company",allEntries=true)
	public void createCompany(Company company) {

		companyDao.createCompany(company);

	}

	@Override
	@CacheEvict(value="Company",allEntries=true)
	public void deleteCompany(Company company) {

		companyDao.deleteCompany(company);

	}

	@Override
	@CacheEvict(value="Company",allEntries=true)
	public void updateCompany(Company company) {

		companyDao.updateCompany(company);

	}

	@Override
	public List<Company> getCompany(Company company) {
		return companyDao.getCompany(company);
	}

	@Override
	public Company getCompanyById(Company company) {
		return companyDao.getCompanyById(company);
	}

	@Override
	public Company getByICAO(String ICAO) {
		return companyDao.getByICAO(ICAO);
	}

}
