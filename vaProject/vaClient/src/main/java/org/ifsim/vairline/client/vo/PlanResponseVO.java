package org.ifsim.vairline.client.vo;

/**
 * @Description: 查询计划后的字符串
 * @author shentong
 * @date 2017年11月22日 下午9:52:25
 * @version V1.0
 */
public class PlanResponseVO extends PlanVO {

	/**
	 * @Description: 参考时间
	 */
	private Integer planTime;
	/**
	 * @Description: 参考油耗
	 */
	private Integer planFuel;

	public PlanResponseVO(String dep, String des, String rte, Integer lvl, Integer spd, Integer planTime,
			Integer planFuel) {
		this.dep = dep;
		this.des = des;
		this.rte = rte;
		this.lvl = lvl;
		this.spd = spd;
		this.planTime = planTime;
		this.planFuel = planFuel;
	}

	@Override
	public String toString() {
		return "["+dep+"]["+des+"]["+rte+"]["+lvl+"]["+spd+"]["+planTime+"]["+planFuel+"]";
	}
	
	
}
