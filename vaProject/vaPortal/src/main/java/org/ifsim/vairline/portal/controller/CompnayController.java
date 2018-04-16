package org.ifsim.vairline.portal.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.common.storage.QiniuStorage;
import org.ifsim.vairline.common.storage.ThumbModel;
import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.aircraft.service.IAircraftService;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;
import org.ifsim.vairline.core.company.domain.CompanyStaff;
import org.ifsim.vairline.core.company.service.ICompanyAircraftService;
import org.ifsim.vairline.core.company.service.ICompanyService;
import org.ifsim.vairline.core.company.service.ICompanyStaffService;
import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.role.service.IRoleService;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.domain.UserRole;
import org.ifsim.vairline.core.user.service.IUserRoleService;
import org.ifsim.vairline.core.user.service.IUserService;
import org.ifsim.vairline.portal.vo.CompanyAircraftVO;
import org.ifsim.vairline.portal.vo.CompanyStaffRoleVO;
import org.ifsim.vairline.web.auth.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 公司Controller层
 * @author shentong
 * @date 2017年12月13日 上午9:52:30
 * @version V1.0
 */
@Controller
@RequestMapping("/company")
public class CompnayController {

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private ICompanyStaffService companyStaffService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private WebState webState;

	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICompanyAircraftService companyAircraftService;

	@Autowired
	private IAircraftService aircraftService;
	

	/**
	 * @Description: 展示所有公司
	 */
	@RequestMapping("/show")
	public String show(Map<String, Object> view) {
		view.put("companyList", companyService.getCompany(null));
		return "company/show";
	}

	/**
	 * @Description: 创建公司
	 */
	@RequestMapping("/create")
	public @ResponseBody WebState create(Company company) {
		// 创建公司
		Company companyEntity = new Company();
		companyEntity.setCompanyIcao(company.getCompanyIcao());
		// 判断是否存在此公司
		if (!companyService.getCompany(companyEntity).isEmpty()) {

			webState.setDesc("已存在此ICAO");
			return webState;
		}
		// 设置默认资金和默认声誉
		company.setReputation(100);
		company.setFunds(9999999);
		companyService.createCompany(company);
		// 获取当前公司员工
		CompanyStaff currentCompanyStaff = companyStaffService
				.getCompanyStaffByUserName(CurrentUser.getUser().getUsername());
		if (currentCompanyStaff != null) {
			currentCompanyStaff.setCompanyIcao(company.getCompanyIcao());
			companyStaffService.updateCompanyStaff(currentCompanyStaff);
		} else {
			currentCompanyStaff = new CompanyStaff();
			currentCompanyStaff.setCompanyIcao(company.getCompanyIcao());
			currentCompanyStaff.setState(1);
			currentCompanyStaff.setUsername(CurrentUser.getUser().getUsername());
			companyStaffService.createCompanyStaff(currentCompanyStaff);
		}

		// 创建董事职位（毕竟是创建了一个公司）
		userRoleService.createOrUpdate(CurrentUser.getUser().getUsername(), "company_post", 15);
		webState.setDesc("创建成功！");
		return webState;
	}

	/**
	 * @Description: 公司管理界面
	 */
	@RequestMapping("/managerment")
	public String managerment(Map<String, Object> view) {
		/* =================行政管理============= */
		// 获取当前员工
		CompanyStaff currentStaff = companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername());
		CompanyStaff companyStaffEntity = new CompanyStaff();
		// 获取当前用户的ICAO号
		companyStaffEntity.setCompanyIcao(currentStaff.getCompanyIcao());
		// 获取当前公司的员工
		List<CompanyStaff> companyStafflist = companyStaffService.getCompanyStaff(companyStaffEntity);
		// 获取当前公司
		Company currentCompany = companyService.getByICAO(currentStaff.getCompanyIcao());

		List<CompanyStaffRoleVO> CompanyStaffRolelist = new ArrayList<CompanyStaffRoleVO>();
		UserRole userRoleEntity = null;
		Role roleEntity = null;
		for (CompanyStaff staff : companyStafflist) {
			CompanyStaffRoleVO companyStaffRole = new CompanyStaffRoleVO();
			// 设置公司成员
			companyStaffRole.setCompanyStaff(staff);
			userRoleEntity = new UserRole();
			roleEntity = new Role();

			userRoleEntity.setUsername(staff.getUsername());
			// 作为公司等级搜索
			roleEntity.setType("company_level");
			List<Role> roleList = roleService.getByUserRoleAndRole(userRoleEntity, roleEntity);
			if (!roleList.isEmpty()) {
				companyStaffRole.setCompanyLevel(roleList.get(0));
			}
			// 作为公司职位搜索
			roleEntity.setType("company_post");
			roleList = roleService.getByUserRoleAndRole(userRoleEntity, roleEntity);
			if (!roleList.isEmpty()) {
				companyStaffRole.setCompanyPost(roleList.get(0));
			}
			// 获取用户（主要获取用户头像）
			User userEntity = new User();
			userEntity.setUsername(staff.getUsername());
			userEntity = userService.getByUsername(userEntity);
			userEntity.setPhoto(QiniuStorage.getUrl(userEntity.getPhoto(), ThumbModel.THUMB_64));
			companyStaffRole.setUser(userEntity);
			CompanyStaffRolelist.add(companyStaffRole);
		}

		roleEntity = new Role();
		roleEntity.setType("company_level");
		// 获取所有公司等级角色
		view.put("companyLevelList", roleService.getRole(roleEntity));
		roleEntity.setType("company_post");
		// 获取所有公司职位角色
		view.put("companyPostList", roleService.getRole(roleEntity));

		view.put("CompanyStaffRolelist", CompanyStaffRolelist);
		view.put("company", currentCompany);

		/* =================机库管理============= */

		CompanyAircraft companyAircraftEntity = new CompanyAircraft();
		companyAircraftEntity.setCompanyIcao(currentStaff.getCompanyIcao());
		// 获取所有公司飞机
		List<CompanyAircraft> companyAircraftList = companyAircraftService.getCompanyAircraft(companyAircraftEntity);

		CompanyAircraftVO companyAircraftVO = null;
		List<CompanyAircraftVO> companyAircraftVOList = new ArrayList<CompanyAircraftVO>();
		for (CompanyAircraft companyAircraftItem : companyAircraftList) {
			companyAircraftVO = new CompanyAircraftVO();
			Aircraft targetAircraft = new Aircraft();
			targetAircraft.setId(companyAircraftItem.getAircraftId());
			targetAircraft = aircraftService.getAircraftById(targetAircraft);

			companyAircraftVO.setAircraft(targetAircraft);
			companyAircraftVO.setCompanyAircraft(companyAircraftItem);
			companyAircraftVOList.add(companyAircraftVO);
		}

		view.put("companyAircraftVOList", companyAircraftVOList);

		return "company/managerment";
	}

	@RequestMapping("/staff_mannager")
	public String staffMannager(Map<String, Object> view) {
		/* =================行政管理============= */
		// 获取当前员工
		CompanyStaff currentStaff = companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername());
		CompanyStaff companyStaffEntity = new CompanyStaff();
		// 获取当前用户的ICAO号
		companyStaffEntity.setCompanyIcao(currentStaff.getCompanyIcao());
		// 获取当前公司的员工
		List<CompanyStaff> companyStafflist = companyStaffService.getCompanyStaff(companyStaffEntity);
		// 获取当前公司
		Company currentCompany = companyService.getByICAO(currentStaff.getCompanyIcao());

		List<CompanyStaffRoleVO> CompanyStaffRolelist = new ArrayList<CompanyStaffRoleVO>();
		UserRole userRoleEntity = null;
		Role roleEntity = null;
		for (CompanyStaff staff : companyStafflist) {
			CompanyStaffRoleVO companyStaffRole = new CompanyStaffRoleVO();
			// 设置公司成员
			companyStaffRole.setCompanyStaff(staff);
			userRoleEntity = new UserRole();
			roleEntity = new Role();

			userRoleEntity.setUsername(staff.getUsername());
			// 作为公司等级搜索
			roleEntity.setType("company_level");
			List<Role> roleList = roleService.getByUserRoleAndRole(userRoleEntity, roleEntity);
			if (!roleList.isEmpty()) {
				companyStaffRole.setCompanyLevel(roleList.get(0));
			}
			// 作为公司职位搜索
			roleEntity.setType("company_post");
			roleList = roleService.getByUserRoleAndRole(userRoleEntity, roleEntity);
			if (!roleList.isEmpty()) {
				companyStaffRole.setCompanyPost(roleList.get(0));
			}
			// 获取用户（主要获取用户头像）
			User userEntity = new User();
			userEntity.setUsername(staff.getUsername());
			userEntity = userService.getByUsername(userEntity);
			userEntity.setPhoto(QiniuStorage.getUrl(userEntity.getPhoto(), ThumbModel.THUMB_64));
			companyStaffRole.setUser(userEntity);
			CompanyStaffRolelist.add(companyStaffRole);
		}

		roleEntity = new Role();
		roleEntity.setType("company_level");
		// 获取所有公司等级角色
		view.put("companyLevelList", roleService.getRole(roleEntity));
		roleEntity.setType("company_post");
		// 获取所有公司职位角色
		view.put("companyPostList", roleService.getRole(roleEntity));

		view.put("CompanyStaffRolelist", CompanyStaffRolelist);
		view.put("company", currentCompany);

		return "company/staff_mannager";
	}

	/**
	 * @Description: 更新成员角色
	 */
	@RequestMapping("/update_staff")
	public @ResponseBody WebState updateStaff(String username, Integer[] roleId) {
		if (username.equals(CurrentUser.getUser().getUsername())) {
			webState.setDesc("您不能修改自己的角色");
			return webState;
		}

		// 获取目标角色
		Role companyLevel = new Role();
		companyLevel.setId(roleId[0]);
		companyLevel = roleService.getRoleById(companyLevel);
		Role companyPost = new Role();
		companyPost.setId(roleId[1]);
		companyPost = roleService.getRoleById(companyPost);

		// 判断当前角色权限是否足够
		if (!CurrentUser.hasEnoughRole(companyLevel)) {
			webState.setDesc("您没有修改比您等级更高的权限");
			return webState;
		}

		if (!CurrentUser.hasEnoughRole(companyPost)) {
			webState.setDesc("您没有修改比您职位更高的权限");
			return webState;
		}

		// 判断当前用户是否有权限更改目标用户的角色
		UserRole userRoleEntity = new UserRole();
		userRoleEntity.setUsername(username);
		Role roleEntity = new Role();
		roleEntity.setType("company_level");
		List<Role> targetUserRoleList = roleService.getByUserRoleAndRole(userRoleEntity, roleEntity);
		for (Role role : targetUserRoleList) {
			if (!CurrentUser.hasEnoughRole(role)) {
				webState.setDesc("您没有修改比您等级更高的成员权限");
				return webState;
			}
		}

		roleEntity.setType("company_post");
		targetUserRoleList = roleService.getByUserRoleAndRole(userRoleEntity, roleEntity);
		for (Role role : targetUserRoleList) {
			if (!CurrentUser.hasEnoughRole(role)) {
				webState.setDesc("您没有修改比您职位更高的成员权限");
				return webState;
			}
		}

		// 修改角色
		if (username != null && roleId != null) {
			userRoleService.createOrUpdate(username, "company_level", roleId[0]);
			userRoleService.createOrUpdate(username, "company_post", roleId[1]);
			webState.setDesc("修改成功");
		} else {
			webState.setDesc("修改失败");
		}
		return webState;
	}

	/**
	 * @Description: 更新成员状态
	 */
	@RequestMapping("/update_staff_state")
	public @ResponseBody WebState updateStaffState(CompanyStaff companyStaff) {
		// 被操作的公司成员
		CompanyStaff currentCompanyStaff = companyStaffService.getCompanyStaffByUserName(companyStaff.getUsername());

		// 如果当前用户属于另外一个公司的话，则更改他的公司，并删除这个申请。否则修改这个申请
		if (currentCompanyStaff != null) {
			currentCompanyStaff.setCompanyIcao(companyStaff.getCompanyIcao());
			companyStaffService.updateCompanyStaff(currentCompanyStaff);
			companyStaffService.deleteCompanyStaff(companyStaff);
			webState.setDesc("success");
		} else {
			// 如果是主动申请的话，便更新成员状态
			if (!companyStaffService.getCompanyStaffById(companyStaff).getIsInvited()) {

				companyStaffService.updateCompanyStaff(companyStaff);
				// 添加“飞行员”角色
				userRoleService.createOrUpdate(companyStaff.getUsername(), "company_post", 19);
				// 添加“学员”角色
				userRoleService.createOrUpdate(companyStaff.getUsername(), "company_level", 9);
			}
			webState.setDesc("success");
		}
		return webState;
	}

	/**
	 * @Description: 申请加入某公司
	 */
	@RequestMapping("/join")
	public @ResponseBody WebState join(CompanyStaff companyStaff) {
		CompanyStaff companyEntity = new CompanyStaff();

		// 设置查询条件
		companyEntity.setState(0);
		companyEntity.setUsername(CurrentUser.getUser().getUsername());
		companyEntity.setIsInvited(false);

		// 查找当前公司成员
		CompanyStaff currentCompanyStaff = companyStaffService
				.getCompanyStaffByUserName(CurrentUser.getUser().getUsername());

		// 如果正在申请别的公司，则不能再次申请
		if (companyStaffService.getCompanyStaff(companyEntity).isEmpty()) {
			companyStaff.setUsername(CurrentUser.getUser().getUsername());
			companyStaff.setState(0);
			companyStaff.setIsInvited(false);

			if (currentCompanyStaff != null
					&& !currentCompanyStaff.getCompanyIcao().equals(companyStaff.getCompanyIcao())) {
				companyStaffService.createCompanyStaff(companyStaff);
				webState.setDesc("success");
			} else if (currentCompanyStaff == null) {
				companyStaffService.createCompanyStaff(companyStaff);
				webState.setDesc("success");
			} else {
				webState.setDesc("不能加入自己所在的公司");
			}
		} else {
			webState.setDesc("正在申请其他公司");
		}

		return webState;
	}

	/**
	 * @Description: 邀请
	 */
	@RequestMapping("/invite")
	public @ResponseBody WebState invite(User targetUser) {
		if (!userService.getUser(targetUser).isEmpty()) {
			CompanyStaff newStaff = new CompanyStaff();
			newStaff.setUsername(targetUser.getUsername());
			newStaff.setIsInvited(true);
			newStaff.setState(0);
			// 设置邀请人
			newStaff.setCreateUser(CurrentUser.getUser().getUsername());
			newStaff.setCompanyIcao(companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername())
					.getCompanyIcao());
			companyStaffService.createCompanyStaff(newStaff);
			webState.setDesc("邀请成功");
		} else {
			webState.setDesc("用户不存在");
		}
		return webState;
	}

	/**
	 * @Description: 删除公司成员
	 */
	@RequestMapping("/delete_staff")
	public @ResponseBody WebState deleteStaff(CompanyStaff targetStaff) {

		// 设置为退出状态
		targetStaff.setState(3);
		companyStaffService.updateCompanyStaff(targetStaff);
		webState.setDesc("已开除");
		return webState;
	}

	/**
	 * @Description: 给公司飞机改名
	 */
	@RequestMapping("/rename_company_aircraft")
	public @ResponseBody WebState renameCompanyAircraft(CompanyAircraft companyAircraft) {
		CompanyAircraft targetCompanyAircraft = companyAircraftService.getCompanyAircraftById(companyAircraft);
		targetCompanyAircraft.setAircraftName(companyAircraft.getAircraftName());
		companyAircraftService.updateCompanyAircraft(targetCompanyAircraft);
		webState.setDesc("修改成功");
		return webState;
	}

	/**
	 * @Description: 把公司飞机卖了
	 */
	@RequestMapping("/sale_company_aircraft")
	public @ResponseBody WebState sale(CompanyAircraft targetCompanyAircraft) {

		// 售卖价格
		Integer price = targetCompanyAircraft.getPrice();
		// 获取目标飞机
		targetCompanyAircraft = companyAircraftService.getCompanyAircraftById(targetCompanyAircraft);

		// 看目标飞机是否还在执飞
		if (!targetCompanyAircraft.getIsFlying()) {

			targetCompanyAircraft.setIsSale(true);
			targetCompanyAircraft.setPrice(price);
			companyAircraftService.updateCompanyAircraft(targetCompanyAircraft);
			webState.setDesc("操作成功");
		} else {

			webState.setDesc("飞机正在执飞，不能出售");
		}
		return webState;
	}
	
	@RequestMapping("/add_aircraft")
	public void addAircraft() {
		companyAircraftService.addAircraft(new Aircraft());
	}

	/**
	 * @Description: 取消卖飞机
	 */
	@RequestMapping("/cancel_sale_company_aircraft")
	public @ResponseBody WebState cancelSaleCompanyAircraft(CompanyAircraft targetCompanyAircraft) {
		// 获取目标飞机
		targetCompanyAircraft = companyAircraftService.getCompanyAircraftById(targetCompanyAircraft);

		// 获取当前公司
		Company currentCompany = companyService.getByICAO(
				companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername()).getCompanyIcao());
		if (targetCompanyAircraft.getCompanyIcao().equals(currentCompany.getCompanyIcao())) {
			targetCompanyAircraft.setIsSale(false);
			companyAircraftService.updateCompanyAircraft(targetCompanyAircraft);
			webState.setDesc("取消成功");
		} else {
			webState.setDesc("取消失败，已经被" + targetCompanyAircraft.getCompanyIcao() + "买走");
		}

		return webState;
	}
	
	@RequestMapping("/add_company")
	public void addCompany() {
		companyAircraftService.addCompany(new Company());;
	}

	/**
	 * @Description: 通过一些条件获取可以执飞的公司飞机
	 */
	@RequestMapping("/get_companyAircraft_by_condition")
	public @ResponseBody List<CompanyAircraft> getcompanyAircraftByCondition(Aircraft aircraftEntity,
			CompanyAircraft companyAircraftEntity) {
		CompanyStaff currentCompanyStaff = companyStaffService
				.getCompanyStaffByUserName(CurrentUser.getUser().getUsername());
		companyAircraftEntity.setCompanyIcao(currentCompanyStaff.getCompanyIcao());
		companyAircraftEntity.setIsFlying(false);
		companyAircraftEntity.setIsSale(false);

		List<CompanyAircraft> companyAircraftList = companyAircraftService
				.getByCompanyAircraftAndAircraft(companyAircraftEntity, aircraftEntity);
		Iterator<CompanyAircraft> it = companyAircraftList.iterator();
		// 坠毁的飞机不能飞
		while (it.hasNext()) {
			if (it.next().getBrokenLevel() == 4) {
				it.remove();
			}
		}
		return companyAircraftList;

	}

	/**
	 * @Description: 给公司飞机改地址
	 */
	@RequestMapping("/change_aircraft_address")
	public @ResponseBody WebState changeAircraftAddress(CompanyAircraft companyAircraft) {
		// 修改飞机所在地
		CompanyAircraft targetAircraft = companyAircraftService.getCompanyAircraftById(companyAircraft);
		targetAircraft.setAddress(companyAircraft.getAddress());
		companyAircraftService.updateCompanyAircraft(targetAircraft);

		Integer dicDistance = 1;
		// 消费金额
		Integer spending = 100000 * dicDistance;

		// 获取当前公司
		Company currentCompany = companyService.getByICAO(
				companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername()).getCompanyIcao());
		currentCompany.setFunds(currentCompany.getFunds() - spending);
		companyService.updateCompany(currentCompany);
		webState.setDesc("修改成功，转移耗费：" + spending);
		return webState;
	}

	/**
	 * @Description: 修复飞机
	 */
	@RequestMapping("/repair_company_aircraft")
	public @ResponseBody WebState repairCompanyAircraft(CompanyAircraft targetCompanyAircraft) {
		targetCompanyAircraft = companyAircraftService.getCompanyAircraftById(targetCompanyAircraft);
		if (targetCompanyAircraft.getBrokenLevel() == 0) {
			webState.setDesc("完好，无需修理");
			return webState;
		}
		Integer baseSpending = 20000;
		// 消费金额
		Integer spending = baseSpending * targetCompanyAircraft.getBrokenLevel();

		// 获取当前公司
		Company currentCompany = companyService.getByICAO(
				companyStaffService.getCompanyStaffByUserName(CurrentUser.getUser().getUsername()).getCompanyIcao());
		currentCompany.setFunds(currentCompany.getFunds() - spending);
		companyService.updateCompany(currentCompany);
		// 最后修理飞机
		targetCompanyAircraft.setBrokenLevel(0);
		companyAircraftService.updateCompanyAircraft(targetCompanyAircraft);
		webState.setDesc("操作成功，修理费：" + spending);
		return webState;
	}

}
