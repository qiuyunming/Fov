package org.ifsim.vairline.core.company.service;

import java.util.List;

import org.ifsim.vairline.core.company.domain.Company;

/**
 * @Description: 公司service层
 * @author shentong
 * @date 2017年11月14日 上午10:50:08
 * @version V1.0
 */
public interface ICompanyService {

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
	 * @Description: 根据条件获取公司
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
