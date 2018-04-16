package org.ifsim.vairline.client.vo;

import struct.StructClass;
import struct.StructField;

/**
 * @Description: 飞行状态通用数据VO
 * @author shentong
 * @date 2017年12月6日 下午5:05:13
 * @version V1.0
 */
@StructClass
public class FlightFlyingstateCommonVO {

	/**
	 * @Description: 交通数量
	 */
	@StructField(order = 0)
	public int count;
	/**
	 * @Description: 服务器指令
	 */
	@StructField(order = 1)
	public byte serverCmd;

	/**
	 * @Description: 服务器参数
	 */
	@StructField(order = 2)
	public float serverCmdParm;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public byte getServerCmd() {
		return serverCmd;
	}

	public void setServerCmd(byte serverCmd) {
		this.serverCmd = serverCmd;
	}

	public float getServerCmdParm() {
		return serverCmdParm;
	}

	public void setServerCmdParm(float serverCmdParm) {
		this.serverCmdParm = serverCmdParm;
	}

	@Override
	public String toString() {
		return "FlightFlyingstateCommonVO [count=" + count + ", serverCmd=" + serverCmd + ", serverCmdParm="
				+ serverCmdParm + "]";
	}

}
