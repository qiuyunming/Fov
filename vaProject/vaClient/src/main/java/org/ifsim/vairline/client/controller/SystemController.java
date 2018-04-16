package org.ifsim.vairline.client.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.ifsim.vairline.client.bussiness.FlightBussiness;
import org.ifsim.vairline.client.vo.BaseDataVO;
import org.ifsim.vairline.client.vo.FlightFlyingstateCommonVO;
import org.ifsim.vairline.client.vo.FlightLandingReportVO;
import org.ifsim.vairline.client.vo.PlanQueryVO;
import org.ifsim.vairline.client.vo.PlanResponseVO;
import org.ifsim.vairline.client.vo.PlanVO;
import org.ifsim.vairline.common.util.ByteTranserUtil;
import org.ifsim.vairline.common.util.CommonUtil;
import org.ifsim.vairline.common.util.ModelUtil;
import org.ifsim.vairline.common.util.UnpackUtil;
import org.ifsim.vairline.common.util.WebUtil;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.domain.CompanyAircraft;
import org.ifsim.vairline.core.company.service.ICompanyAircraftService;
import org.ifsim.vairline.core.company.service.ICompanyService;
import org.ifsim.vairline.core.company.service.ICompanyStaffService;
import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.flight.domain.FlightFlyingstateResponse;
import org.ifsim.vairline.core.flight.domain.FlightLandingReport;
import org.ifsim.vairline.core.flight.domain.FlightPath;
import org.ifsim.vairline.core.flight.domain.FlightPlanDto;
import org.ifsim.vairline.core.flight.service.IFlightFlyingstateService;
import org.ifsim.vairline.core.flight.service.IFlightLandingReportService;
import org.ifsim.vairline.core.flight.service.IFlightPathService;
import org.ifsim.vairline.core.flight.service.IFlightService;
import org.ifsim.vairline.core.plan.domain.Plan;
import org.ifsim.vairline.core.plan.service.IPlanService;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import struct.JavaStruct;
import struct.StructException;

/**
 * @Description: 客户端Controller层
 * @author shentong
 * @date 2017年11月19日 下午3:41:05
 * @version V1.0
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IFlightService flightService;

	@Autowired
	private IFlightFlyingstateService flightFlyingstateService;

	@Autowired
	private IPlanService planService;

	@Autowired
	private IFlightLandingReportService flightLandingReportService;

	@Autowired
	private ICompanyAircraftService companyAircraftService;

	@Autowired
	private ICompanyStaffService companyStaffService;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IFlightPathService flightPathService;

	/**
	 * @Description: 用户登录登出
	 */
	@RequestMapping("/user")
	public void userloginOrLogout(String call, String pass, String cmd, String pver,
			@CookieValue(required = false) Integer PltKey, HttpServletResponse response) {
		Subject currentUser = SecurityUtils.getSubject();
		if (cmd.equals("plogin")) {

			// 将用户名密码封装为UsernamePasswordToken 对象
			UsernamePasswordToken token = new UsernamePasswordToken(call, pass);
			try {
				// 执行登录
				currentUser.login(token);
				// 所有认证时异常的父类
			} catch (AuthenticationException e) {
				System.out.println("登录异常：：" + e.getMessage());
				WebUtil.sendMessage(response, "err_pass");
			}

			// 随机设置一个识别码
			Integer newPltkey = CommonUtil.getRandom(1000, 100000);
			// 如果识别码不唯一，则重新获取一个识别码
			while (flightFlyingstateService.getFlightFlyingstateByPltkey(newPltkey) != null) {
				newPltkey = CommonUtil.getRandom(1000, 100000);
			}

			// 新建一个航班飞行状态
			FlightFlyingstate flightFlyingstate = new FlightFlyingstate();
			flightFlyingstate.setPilotUsername(call);
			List<FlightFlyingstate> flightstateList = flightFlyingstateService.getFlightFlyingstate(flightFlyingstate);
			// 若已存在飞行状态（掉线，未及时清理），则获取这个飞行状态的pltkey
			if (flightstateList.size() > 0) {
				flightFlyingstate = flightstateList.get(0);
				WebUtil.addCookie(response, "PltKey", flightFlyingstate.getPltkey());
			} else {
				// 若不存在此状态，则新建飞行状态
				flightFlyingstate.setPltkey(newPltkey);
				// 在登录时首先让其隐身
				flightFlyingstate.setDel(1);
				flightFlyingstateService.createFlightFlyingstate(flightFlyingstate);
				WebUtil.addCookie(response, "PltKey", newPltkey);
			}

			WebUtil.sendMessage(response, "success");

		} else if (cmd.equals("plogout")) {
			System.err.println("登出");
			flightFlyingstateService.deleteFlightFlyingstateByPltKey(PltKey);
			currentUser.logout();
		}
	}

	/**
	 * @Description: 数据同步接口
	 */
	@RequestMapping(value = "/ident", method = { RequestMethod.POST })
	public void ident(@CookieValue(required = true) Integer PltKey, HttpServletRequest request,
			HttpServletResponse response) {
		byte dataArr[] = new byte[169];
		try {
			// 获取请求中的字节数组
			InputStream is = request.getInputStream();
			is.read(dataArr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FlightFlyingstate currentFlightflyingstate = new FlightFlyingstate();
		// 解包
		UnpackUtil unpackUtil = new UnpackUtil();
		unpackUtil.unpack(currentFlightflyingstate, dataArr, 6);

		currentFlightflyingstate.setPltkey(PltKey);
		// 让其显示
		currentFlightflyingstate.setDel(0);
		// 更新航班飞行状态
		flightFlyingstateService.updateFlightFlyingstateByPltkey(currentFlightflyingstate);

		/* ==================获取所有飞行状态，并获取当前飞行状态==================== */

		List<FlightFlyingstateResponse> FlightFlyingstateResponseList = flightFlyingstateService
				.getFlightFlyingstateAndFlightNumber();

		if (!FlightFlyingstateResponseList.isEmpty()) {
			currentFlightflyingstate = flightFlyingstateService.getFlightFlyingstateByPltkey(PltKey);
			if (currentFlightflyingstate == null) {
				return;
			}

			Iterator<FlightFlyingstateResponse> itertor = FlightFlyingstateResponseList.iterator();
			while (itertor.hasNext()) {
				FlightFlyingstateResponse item = itertor.next();
				item.setModel(ModelUtil.getGentitle(ByteTranserUtil.getString(item.getModel()), ""));
				// 不返回自己的数据
				// 如果超出一定的距离则删除此飞行状态
				if (CommonUtil.getDistance(item.getLongitude(), item.getLatitude(),
						currentFlightflyingstate.getLongitude(), currentFlightflyingstate.getLatitude()) > 700 * 1000
						|| ByteTranserUtil.getString(item.getPilotUsername()).trim()
								.equals(currentFlightflyingstate.getPilotUsername())) {
					itertor.remove();
				}
			}

			// 创建飞行公共数据
			FlightFlyingstateCommonVO flightFlyingCommonVO = new FlightFlyingstateCommonVO();
			flightFlyingCommonVO.setCount(FlightFlyingstateResponseList.size());
			flightFlyingCommonVO.setServerCmd(currentFlightflyingstate.getServerCmd());
			flightFlyingCommonVO.setServerCmdParm(currentFlightflyingstate.getServerCmdParm());
			OutputStream os = null;
			try {
				// 输出公共数据
				os = response.getOutputStream();
				os.write(JavaStruct.pack(flightFlyingCommonVO, ByteOrder.LITTLE_ENDIAN));
				for (FlightFlyingstateResponse item : FlightFlyingstateResponseList) {
					// 输出所有飞机状态
					os.write(JavaStruct.pack(item, ByteOrder.LITTLE_ENDIAN));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (StructException e) {
				e.printStackTrace();
			}

		}

		// 获取当前航班
		Flight currentFlight = flightService.getFlightByPltKey(PltKey);
		if (currentFlight != null && currentFlight.getFlightState() == 9) {
			// 记录落地数据
			if (flightLandingReportService.getCountByFlightId(currentFlight.getId()) <= 300) {
				FlightLandingReport landingReport = new FlightLandingReport();
				landingReport.setFlightId(currentFlight.getId());
				landingReport.setPitch((double) (float) currentFlightflyingstate.getPitch());
				landingReport.setAirspeed((double) (float) currentFlightflyingstate.getAirspeed());
				landingReport.setVspeed((double) (float) currentFlightflyingstate.getVSpeed());
				landingReport.setLoad((double) (float) currentFlightflyingstate.getLoad());
				landingReport.setOnGround(0);
				flightLandingReportService.createFlightLandingReport(landingReport);
			}
		}
		// 创建轨迹
		if (currentFlight != null) {
			FlightPath lastPath = flightPathService.getLastByFlightId(currentFlight.getId());
			// 记录频率
			if (lastPath != null && CommonUtil.compareTime(lastPath.getCreateTime(), new Date()) > 250) {
				flightPathService.createByFlightFlyingstate(currentFlightflyingstate, currentFlight.getId());
			} else if (lastPath == null) {
				flightPathService.createByFlightFlyingstate(currentFlightflyingstate, currentFlight.getId());
			}
		}

	}

	/**
	 * @Description: 计划接口
	 */
	@RequestMapping("/plan")
	public void plan(@CookieValue(required = false) Integer PltKey, BaseDataVO baseData, PlanQueryVO planQueryVO,
			PlanVO planVO, FlightLandingReportVO flightLandingReportVO, HttpServletResponse response) {
		// 获取当前飞行状态
		FlightFlyingstate currentFlightflyingstate = flightFlyingstateService.getFlightFlyingstateByPltkey(PltKey);
		// 若当前飞行状态为空那么什么都不做
		if (currentFlightflyingstate == null) {
			return;
		}
		Plan planEntity = null;
		// 当前航班
		Flight currentFlight = flightService.getFlightByPltKey(PltKey);

		Plan currentPlan = null;
		if (currentFlightflyingstate != null && currentFlightflyingstate.getFlightId() != 0) {

			if (currentFlight != null && currentFlight.getPlanId() != 0) {
				currentPlan = new Plan();
				currentPlan.setId(currentFlight.getPlanId());
				currentPlan = planService.getPlanById(currentPlan);
			}
		}

		// 获取当前用户
		User currentUser = new User();
		currentUser.setUsername(currentFlightflyingstate.getPilotUsername());
		currentUser = userService.getByUsername(currentUser);

		switch (baseData.getCmd()) {
		// 查询已领取的航班
		case "pquery":
			Flight flightEntity = new Flight();
			// 设置查询条件为用户名
			flightEntity.setPilotUsername(currentFlightflyingstate.getPilotUsername());
			planEntity = new Plan();
			// 设置查询条件为起飞机场
			planEntity.setDepartureAirport(planQueryVO.getDep().equals("") ? null : planQueryVO.getDep());
			// 设置查询条件为目的机场
			planEntity.setArrivalAirport(planQueryVO.getDes().equals("") ? null : planQueryVO.getDes());
			// 设置查询条件为机型
			planEntity.setModel(planQueryVO.getMdl().equals("") ? null : planQueryVO.getMdl());
			// 获取当前未开始且符合条件的计划
			List<Plan> planList = null;

			// 只要不是完成、坠毁、放弃就查下去（防止掉线后找不到刚才飞的任务）
			for (int i = 0; i < 12; i++) {
				flightEntity.setFlightState(i);
				planList = planService.getPlanByFlightAndPlan(flightEntity, planEntity);
				if (!planList.isEmpty()) {
					Plan targetPlan = planList.get(0);
					// 创建条件查询的响应实体
					PlanResponseVO planResponseVO = new PlanResponseVO(targetPlan.getDepartureAirport(),
							targetPlan.getArrivalAirport(), targetPlan.getPath(), targetPlan.getPlanAltitude(),
							targetPlan.getPlanSpeed(), targetPlan.getPlanTime(), targetPlan.getPlanFuel());

					WebUtil.sendMessage(response, planResponseVO);
					return;
				}
			}

			// 若没有查询到则发送没有响应计划
			WebUtil.sendMessage(response, "err_plan");
			break;
		case "plan":
			System.out.println("planVO: " + planVO);
			flightEntity = new Flight();
			// 设置查询条件为飞行员名字
			flightEntity.setPilotUsername(currentFlightflyingstate.getPilotUsername());
			// 设置一些查询条件
			planEntity = new Plan();
			planEntity.setDepartureAirport(planVO.getDep());
			planEntity.setArrivalAirport(planVO.getDes());
			planEntity.setPlanAltitude(planVO.getLvl());
			planEntity.setPlanSpeed(planVO.getSpd());
			planEntity.setPath(planVO.getRte());

			List<FlightPlanDto> flightPlanDtoList = null;
			// 只要不是完成、坠毁、放弃就查下去（防止掉线后找不到刚才飞的任务）
			for (int i = 0; i < 12; i++) {
				flightEntity.setFlightState(i);
				flightPlanDtoList = flightService.getByFlightAndPlan(flightEntity, planEntity);
				if (!flightPlanDtoList.isEmpty()) {
					Flight targetFlight = flightPlanDtoList.get(0).getFlight();
					currentFlightflyingstate.setFlightId(targetFlight.getId());
					// 绑定飞行状态与任务
					flightFlyingstateService.updateFlightFlyingstate(currentFlightflyingstate);

					// 如果当前航班为已完成、已放弃、已坠毁则不更新
					if (targetFlight.getFlightState() != 12 && targetFlight.getFlightState() != 13
							&& targetFlight.getFlightState() != 14) {

						// 返回航班号
						planEntity.setId(targetFlight.getPlanId());
						Plan targetPlan = planService.getPlanById(planEntity);

						// 设置航班为准备中
						targetFlight.setFlightState(1);
						targetFlight.setPassengerCount(flightService.getPassengerCount(targetPlan,
								currentFlightflyingstate.getPilotUsername()));
						flightService.updateFlight(targetFlight);
						WebUtil.sendMessage(response, targetPlan.getFlightNumber());
						return;
					} else {
						// 若是状态为放弃、完成、坠毁的航班，则直接跳出循环，新建航班和任务。
						break;
					}
				}
			}

			/* 若查不到，便新建航班和任务 */
			// 设置航班号为公司名+随机数
			planEntity.setFlightNumber(
					companyStaffService.getCompanyStaffByUserName(currentFlightflyingstate.getPilotUsername())
							.getCompanyIcao() + CommonUtil.getRandom(1000, 9999));
			planEntity.setModel(currentFlightflyingstate.getModel());
			planService.createPlan(planEntity);
			// 设置航班状态为准备中
			flightEntity.setFlightState(1);
			// 设置航班的计划id
			flightEntity.setPlanId(planEntity.getId());
			// 设置当前机型
			flightEntity.setModel(currentFlightflyingstate.getModel());
			flightService.createFlight(flightEntity);
			currentFlightflyingstate.setFlightId(flightEntity.getId());
			// 绑定飞行状态与任务
			flightFlyingstateService.updateFlightFlyingstate(currentFlightflyingstate);
			WebUtil.sendMessage(response, planEntity.getFlightNumber());
			break;
		case "start":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(2);
				// 设置当前油量
				currentFlight.setActualFuel(baseData.getFuel());
				currentFlight.setStartTime(new Date());
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "taxi1":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(3);
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "takeoff":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(4);
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "retakeoff":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {
				currentFlight.setFlightState(5);
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "climb":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(6);
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "cruise":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(7);
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "descend":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(8);
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "land":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(9);
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "goaround":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(10);
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);
				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "taxi2":
			System.out.println("这是taxi2" + flightLandingReportVO);
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {
				// 更新航班状态为-开始滑行至停机坪
				currentFlight.setFlightState(11);
				currentFlight.setScore(FlightBussiness.getSore(flightLandingReportVO));
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);

				// 创建落地报告
				FlightLandingReport flightLandingReportEntity = new FlightLandingReport();
				flightLandingReportEntity.setFlightId(currentFlight.getId());
				flightLandingReportEntity.setPitch(flightLandingReportVO.getPich());
				flightLandingReportEntity.setAirspeed(flightLandingReportVO.getAspd());
				flightLandingReportEntity.setVspeed(flightLandingReportVO.getVspd());
				flightLandingReportEntity.setLoad(flightLandingReportVO.getLoad());
				flightLandingReportEntity.setLength(flightLandingReportVO.getLeng());
				flightLandingReportEntity.setOnGround(1);
				// 存入报告
				flightLandingReportService.createFlightLandingReport(flightLandingReportEntity);

				// 更新公司飞机
				CompanyAircraft currentCompanyAircraft = null;
				if (currentFlight.getCompanyAircraftId() != 0) {
					currentCompanyAircraft = new CompanyAircraft();
					currentCompanyAircraft.setId(currentFlight.getCompanyAircraftId());
					currentCompanyAircraft = companyAircraftService.getCompanyAircraftById(currentCompanyAircraft);
					// 设置损坏等级
					currentCompanyAircraft.setBrokenLevel(FlightBussiness.getBrokenLevel(flightLandingReportVO));
					companyAircraftService.updateCompanyAircraft(currentCompanyAircraft);
				}

				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "finish":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {
				currentFlight.setFlightState(12);
				// 更新实际消耗的油量
				Double usedFuel = currentFlight.getActualFuel() - baseData.getFuel();
				currentFlight.setActualFuel(usedFuel);
				currentFlight.setEndTime(new Date());
				currentFlight.setPoint(FlightBussiness.getPoint(currentFlight, currentPlan));
				currentFlight.setReputation(FlightBussiness.getReputation(currentFlight.getScore()));
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);

				// 更新公司飞机
				CompanyAircraft currentCompanyAircraft = null;
				if (currentFlight.getCompanyAircraftId() != 0) {
					currentCompanyAircraft = new CompanyAircraft();
					currentCompanyAircraft.setId(currentFlight.getCompanyAircraftId());
					currentCompanyAircraft = companyAircraftService.getCompanyAircraftById(currentCompanyAircraft);
					// 设置已使用的时间
					currentCompanyAircraft
							.setUsedLife(currentCompanyAircraft.getUsedLife() + currentFlight.getActualTime());
					currentCompanyAircraft.setAddress(currentPlan.getArrivalAirport());
					currentCompanyAircraft.setIsFlying(false);
					companyAircraftService.updateCompanyAircraft(currentCompanyAircraft);
				}

				// 更新用户（声誉和积分）
				currentUser.setReputation(currentUser.getReputation() + currentFlight.getReputation());
				currentUser.setPoint(
						currentUser.getPoint() + FlightBussiness.getUserPoint(currentUser, currentFlight.getPoint()));
				userService.updateUser(currentUser);

				// 更新公司（声誉和积分）
				if (!currentPlan.getCompanyIcao().equals("")) {
					Company currentCompany = companyService.getByICAO(currentPlan.getCompanyIcao());
					currentCompany.setReputation(currentCompany.getReputation() + currentFlight.getReputation());
					currentCompany.setFunds(currentCompany.getFunds() + (currentFlight.getPoint()
							- FlightBussiness.getUserPoint(currentUser, currentFlight.getPoint())));
					companyService.updateCompany(currentCompany);
				}

				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "abort":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {

				currentFlight.setFlightState(13);
				System.out.println(currentFlight);
				// 更新实际消耗的油量
				Double a = currentFlight.getActualFuel();
				Double usedFuel1 = a - baseData.getFuel();
				currentFlight.setActualFuel(usedFuel1);
				currentFlight.setEndTime(new Date());
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);

				// 更新公司飞机
				CompanyAircraft currentCompanyAircraft = null;
				if (currentFlight.getCompanyAircraftId() != 0) {
					currentCompanyAircraft = new CompanyAircraft();
					currentCompanyAircraft.setId(currentFlight.getCompanyAircraftId());
					currentCompanyAircraft = companyAircraftService.getCompanyAircraftById(currentCompanyAircraft);
					// 设置已使用的时间
					currentCompanyAircraft
							.setUsedLife(currentCompanyAircraft.getUsedLife() + currentFlight.getActualTime());
					currentCompanyAircraft.setIsFlying(false);
					companyAircraftService.updateCompanyAircraft(currentCompanyAircraft);
				}

				// 需要理赔的所有钱
				Integer insurance = FlightBussiness.getInsuranceWhenAbort(currentFlight);
				Integer userInsurance = (int) (insurance * 0.05);
				Integer companyInsurance = insurance - userInsurance;

				// 更新用户（声誉和积分）
				currentUser.setReputation(currentUser.getReputation() - FlightBussiness.getReputationWhenAbort());
				// 如果已经收到航班的收入，则退回收入，否则只理赔
				if (currentFlight.getPoint() != 0) {
					currentUser
							.setPoint((int) (currentUser.getPoint() - userInsurance - currentFlight.getPoint() * 0.05));
				} else {

					currentUser.setPoint(currentUser.getPoint() - userInsurance);
				}
				userService.updateUser(currentUser);

				// 更新公司（声誉和积分）
				if (!currentPlan.getCompanyIcao().equals("")) {
					Company currentCompany = companyService.getByICAO(currentPlan.getCompanyIcao());
					currentCompany
							.setReputation(currentCompany.getReputation() - FlightBussiness.getReputationWhenAbort());

					// 如果已经收到航班的收入，则退回收入，否则只理赔
					if (currentFlight.getPoint() != 0) {
						currentCompany.setFunds(
								(int) (currentCompany.getFunds() - companyInsurance - currentFlight.getPoint() * 0.95));
					} else {

						currentCompany.setFunds(currentCompany.getFunds() - companyInsurance);
					}
					companyService.updateCompany(currentCompany);
				}

				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		case "crash":
			// 如果当前航班为已完成、已放弃、已坠毁则不更新状态
			if (currentFlight != null && currentFlight.getFlightState() != 12 && currentFlight.getFlightState() != 13
					&& currentFlight.getFlightState() != 14) {
				currentFlight.setFlightState(14);
				// 更新实际消耗的油量
				Double usedFuel2 = currentFlight.getActualFuel() - baseData.getFuel();
				currentFlight.setActualFuel(usedFuel2);
				currentFlight.setEndTime(new Date());
				currentFlight.setActualTime((long) (new Date().getTime() - currentFlight.getStartTime().getTime()));
				flightService.updateFlight(currentFlight);

				// 更新公司飞机
				CompanyAircraft currentCompanyAircraft = null;
				if (currentFlight.getCompanyAircraftId() != 0) {
					currentCompanyAircraft = new CompanyAircraft();
					currentCompanyAircraft.setId(currentFlight.getCompanyAircraftId());
					currentCompanyAircraft = companyAircraftService.getCompanyAircraftById(currentCompanyAircraft);
					// 设置已使用的时间
					currentCompanyAircraft
							.setUsedLife(currentCompanyAircraft.getUsedLife() + currentFlight.getActualTime());
					currentCompanyAircraft.setIsFlying(false);
					// 设置为坠毁
					currentCompanyAircraft.setBrokenLevel(4);
					companyAircraftService.updateCompanyAircraft(currentCompanyAircraft);
				}

				// 需要理赔的所有钱
				Integer insurance = FlightBussiness.getInsuranceWhenCrash(currentFlight);
				Integer userInsurance = (int) (insurance * 0.05);
				Integer companyInsurance = insurance - userInsurance;

				// 更新用户（声誉和积分）
				currentUser.setReputation(currentUser.getReputation() - FlightBussiness.getReputationWhenCrash());
				// 如果已经收到航班的收入，则退回收入，否则只理赔
				if (currentFlight.getPoint() != 0) {
					currentUser
							.setPoint((int) (currentUser.getPoint() - userInsurance - currentFlight.getPoint() * 0.05));
				} else {

					currentUser.setPoint(currentUser.getPoint() - userInsurance);
				}
				userService.updateUser(currentUser);

				// 更新公司（声誉和积分）
				if (!currentPlan.getCompanyIcao().equals("")) {
					Company currentCompany = companyService.getByICAO(currentPlan.getCompanyIcao());
					currentCompany
							.setReputation(currentCompany.getReputation() - FlightBussiness.getReputationWhenCrash());
					// 如果已经收到航班的收入，则退回收入，否则只理赔
					if (currentFlight.getPoint() != 0) {
						currentCompany.setFunds(
								(int) (currentCompany.getFunds() - companyInsurance - currentFlight.getPoint() * 0.95));
					} else {

						currentCompany.setFunds(currentCompany.getFunds() - companyInsurance);
					}
					companyService.updateCompany(currentCompany);
				}

				WebUtil.sendMessage(response, "success");
			} else {
				WebUtil.sendMessage(response, "err_call");
			}
			break;
		default:
			System.err.println("default: " + baseData.getCmd());
		}
	}

	@RequestMapping("/freq")
	public void COM1(String freq, HttpServletResponse response) {
		// COM1调频
		WebUtil.sendMessage(response, freq);
	}
}
