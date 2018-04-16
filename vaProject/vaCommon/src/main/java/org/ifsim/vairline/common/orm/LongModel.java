package org.ifsim.vairline.common.orm;

import java.io.Serializable;

public class LongModel implements Identifier<Long> ,Serializable{
	private static final long serialVersionUID = 7978917143723588623L;
	
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
}
