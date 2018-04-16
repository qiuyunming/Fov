package org.ifsim.vairline.core.config.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

public class Config extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	private String key;

	private String value;

	public Config() {
	}

	public Config(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void put(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "System [key=" + key + ", value=" + value + "]";
	}

}
