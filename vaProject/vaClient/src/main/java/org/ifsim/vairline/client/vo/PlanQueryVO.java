package org.ifsim.vairline.client.vo;

/**
 * @Description: 查询航路VO
 * @author shentong
 * @date 2017年11月20日 上午9:41:31
 * @version V1.0
 */
public class PlanQueryVO extends PlanVO{

	/**
	 * @Description:是否领取公司计划
	 */
	private Integer type;

	/**
	 * @Description:查询机型
	 */
	private String mdl;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMdl() {
		return mdl;
	}

	public void setMdl(String mdl) {
		this.mdl = mdl;
	}

	@Override
	public String toString() {
		return "PlanQueryVO [type=" + type + ", mdl=" + mdl + "]";
	}

	
	
}
