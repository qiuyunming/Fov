package org.ifsim.vairline.portal.controller;

import org.ifsim.vairline.common.consts.WebState;
import org.ifsim.vairline.common.util.CommonUtil;
import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.flight.service.IFlightFlyingstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/radar")
public class RadarController {

	@Autowired
	private WebState webState;

	@Autowired
	private IFlightFlyingstateService flightFlyingstateService;

	/**
	 * @Description: 设置服务器命令
	 * @param pltkey
	 *            识别码
	 * @param cmd
	 *            管制命令
	 * @param param
	 *            管制命令参数
	 */
	@RequestMapping("/setCMD")
	public @ResponseBody WebState setServerCMD(@RequestParam(required = true) Integer pltkey,
			@RequestParam(required = true) Byte cmd, @RequestParam(required = false) Float param) {
		FlightFlyingstate targetState = new FlightFlyingstate();
		targetState.setPltkey(pltkey);
		targetState.setServerCmd(cmd);
		targetState.setServerCmdParm(param);

		flightFlyingstateService.updateFlightFlyingstateByPltkey(targetState);
		new Reset(targetState).start();
		webState.setDesc("success");
		return webState;
	}

	/**
	 * @Description: 重置命令
	 */
	class Reset extends Thread {

		private FlightFlyingstate targetState;

		private Reset(FlightFlyingstate targetState) {
			this.targetState = targetState;
		}

		@Override
		public void run() {
			// 暂停5秒
			CommonUtil.sleep(5000);
			targetState.setServerCmd((byte) 0);
			flightFlyingstateService.updateFlightFlyingstateByPltkey(targetState);
		}
	}
}
