package org.ifsim.vairline.common.orm;

import java.io.Serializable;

/**
 * @param <KEY>
 */
public interface Identifier<KEY extends Serializable> {

	public Integer getId(); 
	
}
