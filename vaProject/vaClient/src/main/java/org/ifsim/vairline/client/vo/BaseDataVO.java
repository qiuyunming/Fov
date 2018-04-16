package org.ifsim.vairline.client.vo;

/**
 * @Description: 飞行计划父类
 * @author shentong
 * @date 2017年11月20日 上午9:46:47
 * @version V1.0
 */
public class BaseDataVO {

	/**
	 * @Description: 命令名
	 */
	private String cmd;

	/**
	 * @Description: 当前燃油量
	 */
	private Double fuel;
	
	private Double freq;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Double getFuel() {
		return fuel;
	}

	public void setFuel(Double fuel) {
		this.fuel = fuel;
	}
	

	public Double getFreq() {
		return freq;
	}

	public void setFreq(Double freq) {
		this.freq = freq;
	}

	@Override
	public String toString() {
		return "BaseDataVO [cmd=" + cmd + ", fuel=" + fuel + ", freq=" + freq + "]";
	}

	
	
}
