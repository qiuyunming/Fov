package org.ifsim.vairline.client.web.listener;

import org.ifsim.vairline.common.util.CommonUtil;
import org.ifsim.vairline.core.flight.service.IFlightFlyingstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 飞行状态删除工具
 * @author shentong
 * @date 2017年12月27日 下午7:55:47
 * @version V1.0
 */
@Component
public class FlightFlyingClearner extends Thread {

	@Autowired
	private IFlightFlyingstateService flightFlyingstateService;

	/**
	 * @Description: 用于控制是否开始循环
	 */
	private static Boolean flag = true;

	public FlightFlyingClearner() {
		this.start();
	}

	/**
	 * @Description: 删除超时掉线的记录
	 */
	@Override
	public void run() {
		CommonUtil.sleep(180 * 1000);
		while (flag) {
			Integer ids[] = flightFlyingstateService.getOverTime(60);
			if (ids.length > 0) {
				flightFlyingstateService.deleteMore(ids);
			}
			CommonUtil.sleep(60 * 1000);
		}
	}

}
