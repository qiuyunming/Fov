package org.ifsim.vairline.core.company.dao;

import java.util.List;

import org.ifsim.vairline.core.company.domain.Company;

/**
 * @Description: 公司Dao层
 * @author shentong
 * @date 2017年11月13日 上午11:34:48
 * @version V1.0
 */
public interface CompanyDao {
	
	/**
	 * @Description: 创建公司
	 */
	void createCompany(Company company);

	/**
	 * @Description: 删除公司
	 */
	void deleteCompany(Company company);

	/**
	 * @Description: 更改状态
	 */
	void updateCompany(Company company);
	
	/**
	 * @Description: 获取公司
	 */
	List<Company> getCompany(Company company);
	
	/** 
	* @Description: 根据id获取公司 
	*/
	Company getCompanyById(Company company);
	
	/**
	 * @Description: 根据ICAO获取公司
	 */
	Company getByICAO(String ICAO);
}
