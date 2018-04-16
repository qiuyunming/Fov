package org.ifsim.vairline.core.company.dao;

import java.util.List;

import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;

/**
 * @Description: 公司飞机Dao层
 * @author shentong
 * @date 2017年11月15日 下午7:36:07
 * @version V1.0
 */
public interface CompanyAircraftDao {
	
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
	* @Description: 根据id获取公司飞机 
	*/
	CompanyAircraft getCompanyAircraftById(CompanyAircraft companyAircraft);
	
	/** 
	* @Description: 根据公司飞机和飞机获取公司飞机 
	*/
	List<CompanyAircraft> getByCompanyAircraftAndAircraft(CompanyAircraft companyAircraft,Aircraft aircraft);
}
