package org.ifsim.vairline.operator.controller;

import java.util.List;
import java.util.Map;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;
import org.ifsim.vairline.core.company.domain.CompanyStaff;
import org.ifsim.vairline.core.company.service.ICompanyAircraftService;
import org.ifsim.vairline.core.company.service.ICompanyService;
import org.ifsim.vairline.core.company.service.ICompanyStaffService;
import org.ifsim.vairline.operator.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/company")
public class CompanyManagerController {

	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyStaffService companyStaffService;
	
	@Autowired
	private ICompanyAircraftService companyAircraftService;
	

	@Autowired
	private WebState webState;

	@RequestMapping("/company_manager")
	public String companyManager() {
		return "company/company_manager";
	}

	@RequestMapping("/get_all")
	public @ResponseBody TableVO<Company> getAll() {
		List<Company> companyList = companyService.getCompany(null);
		TableVO<Company> tableVO = new TableVO<Company>(companyList);
		return tableVO;
	}

	@RequestMapping("/update")
	public @ResponseBody WebState update(Company targetCompany) {
		companyService.updateCompany(targetCompany);
		return webState;
	}

	@RequestMapping("/del")
	public @ResponseBody WebState delete(Company targetCompany) {
		companyService.deleteCompany(targetCompany);
		return webState;
	}
	
	
	@RequestMapping("/company_detail_manager/{companyIcao}")
	public String companyDetailManager(@PathVariable String companyIcao,Map<String,Object> view) {
		view.put("companyIcao", companyIcao);
		return "company/company_detail_manager";
	}
	/*
	 * 成员管理
	 * */
	
	/** 
	* @Description: 获取公司成员 
	*/
	@RequestMapping("/get_staff")
	public @ResponseBody TableVO<CompanyStaff> getStaff(CompanyStaff staff) {
		List<CompanyStaff> companyStaffList = companyStaffService.getCompanyStaff(staff);
		TableVO<CompanyStaff> tableVO = new TableVO<CompanyStaff>(companyStaffList);
		return tableVO;
	}
	/** 
	* @Description: 更新公司成员 
	*/
	@RequestMapping("/update_staff")
	public @ResponseBody WebState updateStaff(CompanyStaff staff) {
		companyStaffService.updateCompanyStaff(staff);
		return webState;
	}
	/** 
	* @Description: 删除公司成员 
	*/
	@RequestMapping("/del_staff")
	public @ResponseBody WebState deleteStaff(CompanyStaff staff) {
		companyStaffService.deleteCompanyStaff(staff);
		return webState;
	}
	
	/*
	 * 飞机管理
	 * */
	/** 
	* @Description: 获取公司成员 
	*/
	@RequestMapping("/get_aircraft")
	public @ResponseBody TableVO<CompanyAircraft> getStaff(CompanyAircraft aircraft) {
		List<CompanyAircraft> companyAircraftList = companyAircraftService.getCompanyAircraft(aircraft);
		TableVO<CompanyAircraft> tableVO = new TableVO<CompanyAircraft>(companyAircraftList);
		return tableVO;
	}
	/** 
	* @Description: 更新公司成员 
	*/
	@RequestMapping("/update_aircraft")
	public @ResponseBody WebState updateStaff(CompanyAircraft aircraft) {
		companyAircraftService.updateCompanyAircraft(aircraft);
		return webState;
	}
	/** 
	* @Description: 删除公司成员 
	*/
	@RequestMapping("/del_aircraft")
	public @ResponseBody WebState deleteStaff(CompanyAircraft staff) {
		companyAircraftService.deleteCompanyAircraft(staff);
		return webState;
	}

}
