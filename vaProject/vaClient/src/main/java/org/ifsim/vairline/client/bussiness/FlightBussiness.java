package org.ifsim.vairline.client.bussiness;

import org.ifsim.vairline.client.vo.FlightLandingReportVO;
import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.aircraft.service.IAircraftService;
import org.ifsim.vairline.core.company.domain.Company;
import org.ifsim.vairline.core.company.service.ICompanyService;
import org.ifsim.vairline.core.flight.domain.Flight;
import org.ifsim.vairline.core.plan.domain.Plan;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 计算航班乘客数、得分、损耗、利润等的函数
 * @author shentong
 * @date 2017年12月26日 下午3:09:32
 * @version V1.0
 */
public class FlightBussiness {
	
	
	/**
	 * @Description: 获取分数
	 *               1.0到1.099是优秀，1。1到1.19是良好，1.2-1.59是合格，1.6到1.79是轻度超限，1.8以上是严重超限,2.3以上是坠毁
	 */
	public static Integer getSore(FlightLandingReportVO reportVO) {
		Double load = reportVO.getLoad();
		if (1.0 < load && load <= 1.099) {
			return 90;
		} else if (1.1 < load && load <= 1.19) {
			return 80;
		} else if (1.2 < load && load <= 1.59) {
			return 70;
		} else if (1.6 < load && load <= 1.79) {
			return 60;
		} else if (1.8 < load && load <= 2.3) {
			return 50;
		} else {
			return 40;
		}
	}

	/**
	 * @Description: 获取损坏等级
	 */
	public static Integer getBrokenLevel(FlightLandingReportVO reportVO) {
		Double load = reportVO.getLoad();
		if (1.0 < load && load <= 1.099) {
			return 0;
		} else if (1.1 < load && load <= 1.19) {
			return 0;
		} else if (1.2 < load && load <= 1.59) {
			return 1;
		} else if (1.6 < load && load <= 1.79) {
			return 2;
		} else if (1.8 < load && load <= 2.3) {
			return 3;
		} else {
			return 4;
		}
	}

	/**
	 * @Description: 获取总积分（总收入）
	 */
	public static Integer getPoint(Flight flight, Plan plan) {
		// 若不属于公司任务，则不奖励积分
		if (plan.getCompanyIcao().equals("") && plan.getDispatcher().equals(""))
			return 0;
		Integer point = (int) (flight.getPassengerCount() * plan.getTicketPrice());
		return point;
	}

	/**
	 * @Description: 获得声誉
	 * @param score
	 *            此次航班得分
	 */
	public static Integer getReputation(Integer score) {
		// 这个1是临时写的系数，我觉得获得的声誉和自己所在的驾照等级有关
		return (score - 60) / 5;
	}

	/**
	 * @Description: 获取用户收入
	 */
	public static Integer getUserPoint(User user, Integer allPoint) {
		// 收入应该和声誉有一定的关系，待整理公式
		Integer reputation = user.getReputation();
		return (int) (allPoint * 0.05);
	}

	/**
	 * @Description: 当放弃的时候减少的声誉
	 */
	public static Integer getReputationWhenAbort() {
		return 1;
	}

	/**
	 * @Description: 当坠毁的时候减少的声誉
	 */
	public static Integer getReputationWhenCrash() {
		return 3;
	}

	/**
	 * @Description: 理赔 （坠毁）
	 */
	public static Integer getInsuranceWhenCrash(Flight flight) {
		return flight.getPassengerCount() * 1000;
	}

	/**
	 * @Description: 理赔 （坠毁）
	 */
	public static Integer getInsuranceWhenAbort(Flight flight) {
		return flight.getPassengerCount() * 100;
	}
	
}
