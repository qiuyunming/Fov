package org.ifsim.vairline.util;

import java.util.Date;

import org.ifsim.vairline.common.orm.BaseEntity;
import org.ifsim.vairline.web.auth.CurrentUser;

/**
 * @Description: 实例工具
 * @author shentong
 * @date 2017年12月25日 下午3:59:36
 * @version V1.0
 */
public class EntityUtil {

	/**
	 * @Description: 设置创建信息
	 */
	public static void setCreate(BaseEntity baseEntity) {
		if (CurrentUser.getUser() != null) {
			baseEntity.setCreateUser(CurrentUser.getUser().getUsername());
		} else {
			baseEntity.setCreateUser("SYSTEM");
		}
	}

	/**
	 * @Description: 设置更新信息
	 */
	public static void setUpdate(BaseEntity baseEntity) {
		if (CurrentUser.getUser() != null) {
			baseEntity.setUpdateUser(CurrentUser.getUser().getUsername());
		} else {
			baseEntity.setUpdateUser("SYSTEM");
		}
		Date date = new Date();
		baseEntity.setUpdateTime(new Date());
	}
}
