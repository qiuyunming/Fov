package org.ifsim.vairline.core.company.service.impl;

import java.util.List;

import org.ifsim.vairline.core.company.dao.CompanyStaffDao;
import org.ifsim.vairline.core.company.domain.CompanyStaff;
import org.ifsim.vairline.core.company.service.ICompanyStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("CompanyStaff")
public class CompanyStaffServiceImpl implements ICompanyStaffService {

	@Autowired
	CompanyStaffDao companyStaffDao;

	@Override
	@CacheEvict(value="CompanyStaff",allEntries=true)
	public void createCompanyStaff(CompanyStaff companyStaff) {

		companyStaffDao.createCompanyStaff(companyStaff);

	}

	@Override
	@CacheEvict(value="CompanyStaff",allEntries=true)
	public void deleteCompanyStaff(CompanyStaff companyStaff) {
		companyStaffDao.deleteCompanyStaff(companyStaff);

	}

	@Override
	@CacheEvict(value="CompanyStaff",allEntries=true)
	public void updateCompanyStaff(CompanyStaff companyStaff) {
		companyStaffDao.updateCompanyStaff(companyStaff);

	}

	@Override
	public List<CompanyStaff> getCompanyStaff(CompanyStaff companyStaff) {

		return companyStaffDao.getCompanyStaff(companyStaff);
	}

	@Override
	public CompanyStaff getCompanyStaffById(CompanyStaff companyStaff) {

		return companyStaffDao.getCompanyStaffById(companyStaff);
	}

	@Override
	public CompanyStaff getCompanyStaffByUserName(String username) {
		CompanyStaff companyStaffEntity = new CompanyStaff();
		companyStaffEntity.setState(1);
		companyStaffEntity.setUsername(username);
		List<CompanyStaff> companyStaffList = getCompanyStaff(companyStaffEntity);
		CompanyStaff targetCompanyStaff = null;
		if(!companyStaffList.isEmpty()) {
			targetCompanyStaff = companyStaffList.get(0);
		}
		return targetCompanyStaff;
	}

}
