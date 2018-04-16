package org.ifsim.vairline.core.company.service;

import java.util.List;

import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;

public interface ICompanyAircraftService {

	/**
	 * @Description: 创建公司飞机
	 */
	void createCompanyAircraft(CompanyAircraft companyAircraft);

	/**
	 * @Description: 删除公司飞机
	 */
	void deleteCompanyAircraft(CompanyAircraft companyAircraft);

	/**
	 * @Description: 更新公司飞机
	 */
	void updateCompanyAircraft(CompanyAircraft companyAircraft);

	/**
	 * @Description: 根据条件获取公司飞机
	 */
	List<CompanyAircraft> getCompanyAircraft(CompanyAircraft companyAircraft);
	
	/**
	 * @Description: 添加飞机
	 */
	void addAircraft(Aircraft aircraft);

	/**
	 * @Description: 根据id获取公司飞机
	 */
	CompanyAircraft getCompanyAircraftById(CompanyAircraft companyAircraft);
	
	/** 
	* @Description: 添加公司 
	*/
	void addCompany(Company company);

	/**
	 * @Description: 根据公司飞机和飞机获取公司飞机
	 */
	List<CompanyAircraft> getByCompanyAircraftAndAircraft(CompanyAircraft companyAircraft, Aircraft aircraft);
	
}
