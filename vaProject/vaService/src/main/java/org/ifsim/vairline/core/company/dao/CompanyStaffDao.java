package org.ifsim.vairline.core.company.dao;

import java.util.List;

import org.ifsim.vairline.core.company.domain.CompanyStaff;

/**   
* @Description: 公司员工Dao层
* @author shentong  
* @date 2017年11月14日 上午11:33:17 
* @version V1.0   
*/
public interface CompanyStaffDao {
	
	/** 
	* @Description: 创建公司员工 
	*/
	void createCompanyStaff(CompanyStaff companyStaff);
	
	/** 
	* @Description: 删除公司员工 
	*/
	void deleteCompanyStaff(CompanyStaff companyStaff);
	
	
	/** 
	* @Description: 更新公司员工 
	*/
	void updateCompanyStaff(CompanyStaff companyStaff);
	
	/** 
	* @Description: 获取条件公司员工 
	*/
	List<CompanyStaff> getCompanyStaff(CompanyStaff companyStaff);
	
	/** 
	* @Description: 根据id获取公司员工 
	*/
	CompanyStaff getCompanyStaffById(CompanyStaff companyStaff);
}
