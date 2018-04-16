package org.ifsim.vairline.common.consts;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description: 网页返回值
 * @author shentong
 * @date 2017年11月17日 下午4:55:50
 * @version V1.0
 */
@Component
@Scope("prototype")
public class WebState {

	/**
	 * @Description: 状态码
	 */
	private int code;

	/**
	 * @Description: 描述词
	 */
	private String desc;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
