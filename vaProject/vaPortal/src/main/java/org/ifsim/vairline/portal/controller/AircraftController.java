package org.ifsim.vairline.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.aircraft.service.IAircraftService;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;
import org.ifsim.vairline.core.company.domain.CompanyStaff;
import org.ifsim.vairline.core.company.service.ICompanyAircraftService;
import org.ifsim.vairline.core.company.service.ICompanyService;
import org.ifsim.vairline.core.company.service.ICompanyStaffService;
import org.ifsim.vairline.portal.vo.CompanyAircraftVO;
import org.ifsim.vairline.web.auth.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aircraft")
public class AircraftController {

	@Autowired
	IAircraftService aircraftService;

	@Autowired
	ICompanyAircraftService companyAircraftService;

	@Autowired
	ICompanyStaffService companyStaffService;

	@Autowired
	ICompanyService companyService;

	@Autowired
	WebState webState;

	/**
	 * @Description: 飞机交易中心
	 */
	@RequestMapping("/mall")
	public String mall(Map<String, Object> view) {
		// 获取全新飞机
		view.put("aircraftList", aircraftService.getAircraft(null));

		// 获取二手飞机
		CompanyAircraft companyAircraftEntity = new CompanyAircraft();
		companyAircraftEntity.setIsSale(true);
		List<CompanyAircraft> secondHandList = companyAircraftService.getCompanyAircraft(companyAircraftEntity);
		List<CompanyAircraftVO> companyAircraftVOList = new ArrayList<CompanyAircraftVO>();
		CompanyAircraftVO companyAircraftVO = null;
		Aircraft aircraft = null;
		for (CompanyAircraft companyAircraftItem : secondHandList) {
			companyAircraftVO = new CompanyAircraftVO();
			aircraft = new Aircraft();
			aircraft.setId(companyAircraftItem.getAircraftId());

			companyAircraftVO.setAircraft(aircraftService.getAircraftById(aircraft));
			companyAircraftVO.setCompanyAircraft(companyAircraftItem);

			companyAircraftVOList.add(companyAircraftVO);
		}
		view.put("companyAircraftVOList", companyAircraftVOList);
		return "aircraft/mall";
	}

	/**
	 * @Description: 购买全新飞机
	 */
	@RequestMapping("/buy_aircraft")
	public @ResponseBody WebState buy(Aircraft targetAircraft) {

		// 获取目标飞机
		targetAircraft = aircraftService.getAircraftById(targetAircraft);
		// 获取当前公司员工
		CompanyStaff currentStaff = companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername());

		Company currentCompany = companyService.getByICAO(currentStaff.getCompanyIcao());
		if (currentCompany.getFunds() - targetAircraft.getPrice() >= 0) {

			// 先付款后拿货
			currentCompany.setFunds(currentCompany.getFunds() - targetAircraft.getPrice());
			companyService.updateCompany(currentCompany);

			// 一架新飞机 ^_^
			CompanyAircraft newCompanyAircraft = new CompanyAircraft();
			newCompanyAircraft.setAircraftId(targetAircraft.getId());
			newCompanyAircraft.setCompanyIcao(currentCompany.getCompanyIcao());
			newCompanyAircraft.setAddress(currentCompany.getHeadquarters());
			newCompanyAircraft.setPatterns("全额");

			companyAircraftService.createCompanyAircraft(newCompanyAircraft);

			webState.setDesc("购买成功");
		} else {
			webState.setDesc("余额不足");
		}
		return webState;
	}

	/**
	 * @Description: 买二手飞机
	 */
	@RequestMapping("/buy_second_hand_aircraft")
	public @ResponseBody WebState buySecondAircraft(CompanyAircraft targetCompanyAircraft) {

		// 获取目标公司飞机
		targetCompanyAircraft = companyAircraftService.getCompanyAircraftById(targetCompanyAircraft);

		if (!targetCompanyAircraft.getIsSale()) {
			webState.setDesc("对方已取消出售");
			return webState;
		}

		// 当前公司成员
		CompanyStaff currentCompanyStaff = companyStaffService
				.getCompanyStaffByUserName(CurrentUser.getUser().getUsername());
		// 当前公司
		Company currentCompany = companyService.getByICAO(currentCompanyStaff.getCompanyIcao());
		// 目标公司
		Company targetCompany = companyService.getByICAO(targetCompanyAircraft.getCompanyIcao());

		if (!currentCompany.getCompanyIcao().equals(targetCompanyAircraft.getCompanyIcao())) {
			// 判断是否买得起
			if (currentCompany.getFunds() - targetCompanyAircraft.getPrice() >= 0) {

				// 同样先付款
				currentCompany.setFunds(currentCompany.getFunds() - targetCompanyAircraft.getPrice());
				companyService.updateCompany(currentCompany);
				// 将钱打到对方账户
				targetCompany.setFunds(targetCompany.getFunds() + targetCompanyAircraft.getPrice());
				companyService.updateCompany(targetCompany);

				// 后交货
				targetCompanyAircraft.setCompanyIcao(currentCompany.getCompanyIcao());
				targetCompanyAircraft.setIsSale(false);
				companyAircraftService.updateCompanyAircraft(targetCompanyAircraft);
				webState.setDesc("购买成功");
			} else {
				webState.setDesc("余额不足");
			}
		} else {
			webState.setDesc("不能购买自己公司的飞机");
		}

		return webState;
	}

	/**
	 * @Description: 通过icao号码，模糊查找
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/like")
	public @ResponseBody List<Aircraft> getLike(@RequestParam String model) {
		return aircraftService.getLikeModel(model);
	}

}
