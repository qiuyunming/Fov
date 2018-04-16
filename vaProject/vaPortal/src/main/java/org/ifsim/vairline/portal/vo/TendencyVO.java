package org.ifsim.vairline.portal.vo;

/**
 * @Description: 趋向VO
 * @author shentong
 * @date 2018年1月11日 上午10:41:19
 * @version V1.0
 */
public class TendencyVO {

	/**
	 * @Description: 当前用户的趋势是否存在
	 */
	private Boolean isExisted;

	/**
	 * @Description: 此趋势的数量
	 */
	private Integer count;

	public Boolean getIsExisted() {
		return isExisted;
	}

	public void setIsExisted(Integer i) {
		if (i == 0) {
			this.isExisted = false;
		} else {
			this.isExisted = true;
		}
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "TendencyVO [isExisted=" + isExisted + ", count=" + count + "]";
	}

}
