package org.ifsim.vairline.client.vo;

/**
 * @Description: 计划VO
 * @author shentong
 * @date 2017年11月20日 上午9:54:40
 * @version V1.0
 */
public class PlanVO extends BaseDataVO {

	/**
	 * @Description: 出发机场代码
	 */
	protected String dep;
	/**
	 * @Description: 目的机场代码
	 */
	protected String des;
	/**
	 * @Description: 计划飞行高度
	 */
	protected Integer lvl;
	/**
	 * @Description: 计划飞行速度
	 */
	protected Integer spd;
	/**
	 * @Description: 计划飞行路线
	 */
	protected String rte;

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getLvl() {
		return lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

	public Integer getSpd() {
		return spd;
	}

	public void setSpd(Integer spd) {
		this.spd = spd;
	}

	public String getRte() {
		return rte;
	}

	public void setRte(String rte) {
		this.rte = rte;
	}

	@Override
	public String toString() {
		return "PlanVO [dep=" + dep + ", des=" + des + ", lvl=" + lvl + ", spd=" + spd + ", rte=" + rte + "]";
	}

}
